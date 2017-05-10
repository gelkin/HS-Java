package space.harbour.hw8;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;


/**
 * Class for converting objects to json's.
 * Supported types are:
 * - Primitive types
 * - Strings
 * - Arrays
 * - Iterables
 * - Classes with fields with type mentioned above
 */
class MyGSON {

    static String toJSON(Object obj) {
        if (obj == null) {
            return "";
        }
        Class<?> objClass = obj.getClass();
        if (ClassUtils.isPrimitiveOrWrapper(objClass)) {
            return primitive2String(obj);
        }
        if (obj instanceof String) {
            return string2String((String) obj);
        }
        if (objClass.isArray()) {
            return handleArbitraryArray(obj);
        }
        if (obj instanceof Iterable<?>) {
            return handleIterable((Iterable<?>) obj);
        }
        if (obj instanceof Map<?,?>) {
            throw new IllegalArgumentException("Cannot work with Map's");
        }
        return handleClass(obj);
    }

    private static String handleClass(Object obj) {
        Map<String, String> map = new LinkedHashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean isAccessible = true;
            try {
                if (Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                isAccessible = field.isAccessible();
                field.setAccessible(true);
                Object value = field.get(obj);
                if (value != null) {
                    String valueString = toJSON(value);
                    map.put(field.getName(), valueString);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                if (field != null && !isAccessible) {
                    field.setAccessible(false);
                }
            }
        }
        return map2String(map);
    }

    private static String map2String(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, String> entry: map.entrySet()) {
            sb.append(string2String(entry.getKey()));
            sb.append(":");
            sb.append(entry.getValue());
            sb.append(",");
        }
        if (!map.isEmpty()) {
            sb.setLength(sb.length() - 1); // remove last ','
        }
        sb.append("}");
        return sb.toString();
    }

    private static String primitive2String(Object obj) {
        return String.valueOf(obj);
    }

    private static String string2String(String str) {
        return String.format("\"%s\"", str);
    }

    private static String handleArrayOfObjects(Object[] arr) {
        return handleIterable(Arrays.asList(arr));
    }

    private static String handleIterable(Iterable<?> it) {
        List<String> resList = new ArrayList<>();
        for (Object elem : it) {
            String elemJSON = toJSON(elem);
            resList.add(elemJSON);
        }
        return stringList2JSON(resList);
    }

    private static String stringList2JSON(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String elem : list) {
            sb.append(elem);
            sb.append(",");
        }
        if (!list.isEmpty()) {
            sb.setLength(sb.length() - 1); // remove last ','
        }
        sb.append("]");
        return sb.toString();
    }

    private static String handleArbitraryArray(Object obj) {
        if (obj instanceof byte[]) {
            return handleArray((byte[]) obj);
        }
        if (obj instanceof short[]) {
            return handleArray((short[]) obj);
        }
        if (obj instanceof int[]) {
            return handleArray((int[]) obj);
        }
        if (obj instanceof long[]) {
            return handleArray((long[]) obj);
        }
        if (obj instanceof boolean[]) {
            return handleArray((boolean[]) obj);
        }
        if (obj instanceof char[]) {
            return handleArray((char[]) obj);
        }
        if (obj instanceof float[]) {
            return handleArray((float[]) obj);
        }
        if (obj instanceof double[]) {
            return handleArray((double[]) obj);
        }

        return handleArrayOfObjects((Object[]) obj);
    }

    private static String handleArray(byte[] arr) {
        return handleIterable(Arrays.asList(arr));
    }

    private static String handleArray(short[] arr) {
        return handleIterable(Arrays.asList(ArrayUtils.toObject(arr)));
    }

    private static String handleArray(int[] arr) {
        return handleIterable(Arrays.asList(ArrayUtils.toObject(arr)));
    }

    private static String handleArray(long[] arr) {
        return handleIterable(Arrays.asList(ArrayUtils.toObject(arr)));
    }

    private static String handleArray(boolean[] arr) {
        return handleIterable(Arrays.asList(ArrayUtils.toObject(arr)));
    }

    private static String handleArray(char[] arr) {
        return handleIterable(Arrays.asList(ArrayUtils.toObject(arr)));
    }

    private static String handleArray(float[] arr) {
        return handleIterable(Arrays.asList(ArrayUtils.toObject(arr)));
    }

    private static String handleArray(double[] arr) {
        return handleIterable(Arrays.asList(ArrayUtils.toObject(arr)));
    }
}

