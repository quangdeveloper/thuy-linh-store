package vn.free.register.configuration.transformers;

import vn.free.register.configuration.PropertyTransformer;
import vn.free.register.configuration.TransformationException;

import java.lang.reflect.Field;

public class ByteTransformer implements PropertyTransformer<Byte> {

    public static final ByteTransformer SHARED_INSTANCE = new ByteTransformer();

    @Override
    public Byte transform(String value, Field field) throws TransformationException {
        try {
            return Byte.decode(value);
        } catch (NumberFormatException e) {
            throw new TransformationException(e);
        }
    }
}
