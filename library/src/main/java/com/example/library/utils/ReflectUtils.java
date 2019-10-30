package com.example.library.utils;

import java.lang.reflect.Field;

public class ReflectUtils {

    private static Object getField(Object obj, Class<?> clazz, String filed)
            throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException{
        Field localField = clazz.getDeclaredField(filed);
        localField.setAccessible(true);
        return localField.get(obj);
    }

    public static void setField(Object obj, Class<?> clazz, Object value)
            throws NoSuchFieldException, IllegalAccessException, IllegalArgumentException{
        Field localField = clazz.getDeclaredField("dexElements");
        localField.setAccessible(true);
        localField.set(obj, value);
    }

    public static Object getPathList(Object o) throws NoSuchFieldException,
            IllegalAccessException, IllegalArgumentException, ClassNotFoundException{
        return getField(o, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    public static Object getDexElements(Object paramObject)
            throws NoSuchFieldException, IllegalAccessException,IllegalArgumentException{
        return getField(paramObject, paramObject.getClass(), "dexElements");
    }
}
