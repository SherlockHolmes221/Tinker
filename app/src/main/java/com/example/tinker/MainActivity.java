package com.example.tinker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String[] parms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if(checkSelfPermission(parms[0]) == PackageManager.PERMISSION_DENIED){
                requestPermissions(parms, 200);
            }
        }
    }

    public void jump(View view){
        startActivity(new Intent(this, SecondActivity.class));
    }

}
