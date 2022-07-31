package vn.free.register.configuration.transformers;

import vn.free.register.configuration.PropertyTransformer;
import vn.free.register.configuration.TransformationException;

import java.lang.reflect.Field;

public class EnumTransformer implements PropertyTransformer<Enum<?>> {

    public static final EnumTransformer SHARED_INSTANCE = new EnumTransformer();

    @SuppressWarnings("unchecked")
    @Override
    public Enum<?> transform(String value, Field field) throws TransformationException {
        @SuppressWarnings({"rawtypes"})
        Class<? extends Enum> clazz = (Class<? extends Enum>) field.getType();

        try {
            return Enum.valueOf(clazz, value);
        } catch (Exception e) {
            throw new TransformationException(e);
        }
    }
}
