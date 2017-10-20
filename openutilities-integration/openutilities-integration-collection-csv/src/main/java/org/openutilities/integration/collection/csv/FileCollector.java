package org.openutilities.integration.collection.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * Folder watcher class reads and stores CSV files in Kafka topics.
 */
@SpringBootApplication
@EnableBinding(Source.class)
@Component
public class FileCollector
{
    private static Logger logger = LoggerFactory.getLogger(FileCollector.class);

    @Autowired
    private Source channels;

    @Value("${hotfolder.path}")
    private String path;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        ConfigurableApplicationContext context  = SpringApplication.run(FileCollector.class, args);
        FileCollector fc = context.getBean(FileCollector.class);
        fc.watch();
    }

    /**
     * Watch entry method.
     * @throws IOException
     * @throws InterruptedException
     */
    public void watch() throws IOException, InterruptedException
    {
        Path hotFolderPath = Paths.get(this.path);

        try (WatchService hotFolderWs = hotFolderPath.getFileSystem().newWatchService())
        {
            hotFolderPath.register(hotFolderWs, StandardWatchEventKinds.ENTRY_CREATE);

            while (true)
            {
                WatchKey watchKey = hotFolderWs.take();

                if (watchKey != null) {
                    // Poll for file system events on the WatchKey
                    for (final WatchEvent<?> event : watchKey.pollEvents())
                    {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
                        {
                            logger.info("New file detected '%s'", event.context().toString());

                            sendMessage(new String(Files.readAllBytes(Paths.get(this.path + "\\" + event.context().toString()))));

                            logger.info("File '%s' has been collected", event.context().toString());
                        }
                    }
                }

                boolean valid = watchKey.reset();
                if (!valid)
                {
                    break;
                }
            }
        }
    }

    /**
     * Send message to Kafka.
     * @param body
     */
    private void sendMessage(String body)
    {
        this.channels.output().send(MessageBuilder.createMessage(body.getBytes(StandardCharsets.UTF_8), new MessageHeaders(null)));
    }
}
