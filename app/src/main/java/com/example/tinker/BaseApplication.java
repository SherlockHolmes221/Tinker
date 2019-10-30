package com.example.tinker;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.example.library.FixDexUtils;
import com.example.library.utils.FileUtils;

public class BaseApplication extends MultiDexApplication {

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base){
        super.attachBaseContext(base);
//        MultiDex.install(this);
        FixDexUtils.loadFixDex(this);

    }
}
