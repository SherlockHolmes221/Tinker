package com.example.tinker;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.FixDexUtils;
import com.example.library.utils.Constant;
import com.example.library.utils.FileUtils;
import com.example.tinker.utils.ParamsSort;

import java.io.File;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void count(View view) {
        ParamsSort sort = new ParamsSort();
        sort.math(this);
    }

    public void fix(View view) {
        //复制下载的修复包到私有目录
        File sourceFile = new File(Environment.getExternalStorageState(), Constant.DEX_NAME);
        File targetFile = new File(getDir(Constant.DEX_DIR, Context.MODE_PRIVATE).getAbsolutePath()
        + File.separator + Constant.DEX_NAME);

        if(targetFile.exists()){
            targetFile.delete();
            Toast.makeText(this, "delete",Toast.LENGTH_SHORT).show();
        }

        try {
            FileUtils.copyFile(sourceFile,targetFile);
            Toast.makeText(this, "copy",Toast.LENGTH_SHORT).show();
            FixDexUtils.loadFixDex(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
