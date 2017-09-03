package com.karman.ledgerex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.karman.ledgerex.R;
import com.karman.ledgerex.utils.Utils;

public class MainActivity extends AppCompatActivity {
    public static boolean login = false;
    ImageView ivNavigation;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        initView ();
        initData ();
        isLogin ();
    }
    
    private void initView () {
        ivNavigation = (ImageView) findViewById (R.id.ivNavigation);
    }
    
    private void initData () {
        Utils.setTypefaceToAllViews (this, ivNavigation);
    }
    
    private void isLogin () {
        if (! login) {
            Intent intent = new Intent (MainActivity.this, LoginActivity.class);
            startActivity (intent);
            finish ();
        }
    }
}