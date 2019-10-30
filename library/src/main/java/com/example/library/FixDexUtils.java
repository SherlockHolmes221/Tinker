package com.example.library;

import android.content.Context;

import com.example.library.utils.ArrayUtils;
import com.example.library.utils.Constant;
import com.example.library.utils.ReflectUtils;

import java.io.File;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class FixDexUtils {
    private static HashSet<File> loadedDex = new HashSet<>();
    static {
        loadedDex.clear();
    }
    public static void loadFixDex(Context context){
        File fileDir = context.getDir(Constant.DEX_DIR, Context.MODE_PRIVATE);
        File[] files = fileDir.listFiles();

        for(File file : files){
            if(file.getName().endsWith(Constant.DEX_SUFFIX) &&
            ! "classes.dex".equalsIgnoreCase(file.getName())){
                loadedDex.add(file);
            }
        }
        createDexClassLoader(context, fileDir);
    }

    private static void createDexClassLoader(Context context, File fileDir) {
        String optimizerDir = fileDir.getAbsolutePath() + File.separator +
                "opt_dex";

        File fo=  new File(optimizerDir);
        if(!fo.exists()){
            fo.mkdirs();
        }

        for(File dex : loadedDex){
            DexClassLoader classLoader = new DexClassLoader(dex.getAbsolutePath(),
                    optimizerDir, null, context.getClassLoader());

            //fix
            hotfix(classLoader, context);
        }

    }

    private static void hotfix(DexClassLoader classLoader, Context context) {
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        try {
            //获取自有的
            Object systemElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathClassLoader));
            //获取系统的
            Object myElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(classLoader));
            //合并
            Object dexElements = ArrayUtils.combineArray(myElements, systemElements);
            //获取系统的pathlist属性
            Object systemPathlist = ReflectUtils.getPathList(pathClassLoader);
            //重新赋值
            ReflectUtils.setField(systemPathlist,systemPathlist.getClass(), dexElements);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

}
