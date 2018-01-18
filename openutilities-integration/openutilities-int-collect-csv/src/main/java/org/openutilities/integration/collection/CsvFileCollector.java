package org.openutilities.integration.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

/**
 * Folder watcher class reads and stores CSV files in Kafka topics.
 */
@SpringBootApplication
@Component
public class CsvFileCollector
{
    private static Logger logger = LoggerFactory.getLogger(CsvFileCollector.class);
    private static final String INPUT = "/input/";
    private static final String OUTPUT = "/output/";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${application.hotfolder.path}")
    private String path;

    @Value("${application.kafka.topic}")
    private String topic;

    public static void main(String[] args) throws IOException, InterruptedException
    {
        ConfigurableApplicationContext context  = SpringApplication.run(CsvFileCollector.class, args);
        CsvFileCollector fc = context.getBean(CsvFileCollector.class);
        fc.watch();
    }

    /**
     * Watch entry method.
     * @throws IOException
     * @throws InterruptedException
     */
    public void watch() throws IOException, InterruptedException
    {
        Path hotFolderPathInput = Paths.get(this.path + INPUT);
        try (WatchService hotFolderWs = hotFolderPathInput.getFileSystem().newWatchService())
        {
            hotFolderPathInput.register(hotFolderWs, StandardWatchEventKinds.ENTRY_CREATE);
            logger.info("Watching folder " + hotFolderPathInput.toString());

            while (true)
            {
                WatchKey watchKey = hotFolderWs.take();

                if (watchKey != null)
                {
                    // Poll for file system events on the WatchKey
                    for (final WatchEvent<?> event : watchKey.pollEvents())
                    {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
                        {
                            logger.info("New file detected {}", event.context().toString());
                            Path inputFile = Paths.get(this.path + INPUT + event.context().toString());
                            Path outputFile = Paths.get(this.path + OUTPUT + event.context().toString());

                            logger.info("Sending file {} to input queue", event.context().toString());
                            sendMessage(Files.readAllBytes(inputFile));

                            logger.info("Moving file {} to output folder", event.context().toString());
                            Files.move(inputFile, outputFile);

                            logger.info("File {} has been collected", event.context().toString());
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
    private void sendMessage(byte[] body)
    {
        this.kafkaTemplate.send(this.topic, body);
    }
}
