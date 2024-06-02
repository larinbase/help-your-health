package ru.itis.healthserviceimpl.util;

import org.bson.types.ObjectId;
import java.nio.ByteBuffer;
import java.util.UUID;

public class UuidToObjectIdConverter {

    public static ObjectId toObjectId(UUID uuid) {
        byte[] bytes = new byte[12];
        ByteBuffer.wrap(bytes)
                .putLong(uuid.getMostSignificantBits())
                .putLong(uuid.getLeastSignificantBits());
        return new ObjectId(bytes);
    }

    public static UUID toUUID(ObjectId objectId) {
        byte[] bytes = objectId.toByteArray();
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long mostSignificantBits = byteBuffer.getLong();
        long leastSignificantBits = byteBuffer.getLong();
        return new UUID(mostSignificantBits, leastSignificantBits);
    }
}