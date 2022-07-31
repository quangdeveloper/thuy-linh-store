package vn.free.register.configuration.transformers;

import vn.free.register.configuration.PropertyTransformer;
import vn.free.register.configuration.TransformationException;

import java.lang.reflect.Field;

public class BooleanTransformer implements PropertyTransformer<Boolean> {

    public static final BooleanTransformer SHARED_INSTANCE = new BooleanTransformer();

    @Override
    public Boolean transform(String value, Field field) throws TransformationException {

        if ("true".equalsIgnoreCase(value) || "1".equals(value)) {
            return true;
        } else if ("false".equalsIgnoreCase(value) || "0".equals(value)) {
            return false;
        } else {
            throw new TransformationException("Invalid boolean string: " + value);
        }
    }
}
