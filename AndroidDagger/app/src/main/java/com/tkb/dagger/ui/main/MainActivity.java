package com.tkb.dagger.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import com.tkb.dagger.BaseActivity;
import com.tkb.dagger.R;


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"Successfully logged in",Toast.LENGTH_LONG).show();
    }
}
