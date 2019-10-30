package com.example.library.utils;

import java.lang.reflect.Array;

public class ArrayUtils {
    public static Object combineArray(Object myElements, Object systemElements) {
        Class<?> loadClass = myElements.getClass().getComponentType();
        int i = Array.getLength(myElements);
        int j = i +  Array.getLength(systemElements);

        Object result = Array.newInstance(loadClass, j);
        for(int k = 0; k < j;k++){
            if(k < i){
                Array.set(result, k,Array.get(myElements,k));
            }else {
                Array.set(result, k,Array.get(systemElements,k-i));
            }
        }
        return result;
    }
}
