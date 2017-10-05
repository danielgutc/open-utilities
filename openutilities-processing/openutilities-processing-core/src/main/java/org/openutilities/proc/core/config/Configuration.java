package org.openutilities.proc.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class.
 */
public class Configuration
{
    private static Logger logger = LoggerFactory.getLogger(Configuration.class);

    private static HashMap<String, Object> properties;

    static
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try (InputStream is = Configuration.class.getResourceAsStream("/application.yml"))
        {
            Map<String, Object> config = mapper.readValue(is,
                    TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, Object.class));
            flatProperties(null, config, properties);
        }
        catch (Exception e)
        {
            logger.error("Error reading application properties", e);
        }
    }

    /**
     * Recursive method to flat the yaml format to convenient properties one.
     * @param propertyName that defines the property
     * @param value represents the map value to flat
     * @param result stores the map with flat properties
     */
    private static void flatProperties(String propertyName, Object value, Map<String, Object> result)
    {
        if (value instanceof HashMap)
        {
            Map<String, Object> valueMap = (Map<String, Object>) value;
            for (Map.Entry<String, Object> entry: valueMap.entrySet())
            {
                String propName = StringUtils.isEmpty(propertyName) ? entry.getKey() : propertyName + "." + entry.getKey();
                flatProperties(propName, entry.getValue(), result);
            }
        }
        else
        {
            result.put(propertyName, value);
        }
    }

    public static Object getPropertyValue(String propertyName)
    {
        return properties.get(propertyName);
    }

    public static <T> T getTypedProperty(String propertyName)
    {
        return (T) properties.get(propertyName);
    }

    public static String getPropertyAsString(String propertyName)
    {
        return Configuration.<String>getTypedProperty(propertyName);
    }
}
