package org.openutilities.core.appservices.util;

import org.openutilities.core.appservices.exception.AppServicesException;

import java.io.*;

/**
 * Serialization utils
 */
public class SerializationUtils
{
    public static byte[] toByteArray(Object o)
    {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos);)
        {
            out.writeObject(o);

            return bos.toByteArray();
        }
        catch(Exception ex)
        {
            throw new AppServicesException("Unable to serialize object", ex);
        }
    }

    public static <T> T fromByteArray(byte[] bytes)
    {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInput in = new ObjectInputStream(bis);)
        {
            return (T) in.readObject();
        }
        catch (Exception ex)
        {
            throw new AppServicesException("Unable to deserialize object", ex);
        }
    }
}
