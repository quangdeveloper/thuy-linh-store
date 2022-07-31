package vn.free.register.configuration.transformers;

import vn.free.register.configuration.PropertyTransformer;
import vn.free.register.configuration.TransformationException;

import java.lang.reflect.Field;

public class ClassTransformer implements PropertyTransformer<Class<?>> {

    public static final ClassTransformer SHARED_INSTANCE = new ClassTransformer();

    @Override
    public Class<?> transform(String value, Field field) throws TransformationException {
        try {
            return Class.forName(value, false, getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new TransformationException("Cannot find class with name '" + value + "'");
        }
    }
}
