package set.record;

import java.io.*;
import java.util.Base64;

/**
 * Utility class for reading and writing Base64 encoded objects.
 */
public class SerializeUtils {

    /**
     * Deserializes a Base64 string into a Java object.
     *
     * @param string Object encoded in Base64.
     * @param <T> Generic type of object
     * @return Deocded object of type T
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T deserializeObject(String string) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(string);
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));

        Object object = objectInputStream.readObject();
        objectInputStream.close();

        //noinspection unchecked
        return (T) object;
    }

    /**
     * Serializes a Java object into a Base64 string.
     *
     * @param object Serializable object to serialize.
     * @return Base64 encoded string of object.
     * @throws IOException
     */
    public static String serializeObject(Serializable object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(object);
        objectOutputStream.close();

        byte[] encoded = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(encoded);
    }

}
