package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    public static final String NOT_NULL_ERROR_MESSAGE = "can not be null";
    public static final String MIN_LENGTH_ERROR_MESSAGE = "length less than 4";

    public static List<String> validate(Object obj) {

        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        List<String> nonValidatedFields = new ArrayList<>();

        try {
            for (Field field: declaredFields) {

                field.setAccessible(true);
                var value = field.get(obj);

                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    nonValidatedFields.add(field.getName());
                }
            }

        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        }

        return nonValidatedFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {

        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        Map<String, List<String>> nonValidatedFields = new LinkedHashMap<>();

        try {
            for (Field field: declaredFields) {
                field.setAccessible(true);
                var value = field.get(obj);

                List<String> errors = new ArrayList<>();

                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    errors.add(NOT_NULL_ERROR_MESSAGE);
                }

                if (field.isAnnotationPresent(MinLength.class) && value != null && value.toString().length() < 4) {
                    errors.add(MIN_LENGTH_ERROR_MESSAGE);
                }

                if (!errors.isEmpty()) {
                    nonValidatedFields.put(field.getName(), errors);
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return nonValidatedFields;
    }
}
// END
