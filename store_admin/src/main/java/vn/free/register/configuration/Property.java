package vn.free.register.configuration;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {

    public static final String DEFAULT_VALUE = "DO_NOT_OVERWRITE_INITIALIAZION_VALUE";

    public String key();

    @SuppressWarnings("rawtypes")
    public Class<? extends PropertyTransformer> propertyTransformer() default PropertyTransformer.class;

    public String defaultValue() default DEFAULT_VALUE;
}
