package com.karman.ledgerex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karman.ledgerex.R;
import com.karman.ledgerex.utils.Constants;
import com.karman.ledgerex.utils.Utils;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rlBack;
    CoordinatorLayout clMain;
    
    TextView tvPin1, tvPin2, tvPin3, tvPin4;
    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btPeriod;
    ImageView ivDelete, ivNext;
    EditText etPIN;
    
    LinearLayout llPinButtons;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        initView ();
        initData ();
        initListener ();
    }
    
    private void initData () {
        Utils.setTypefaceToAllViews (this, rlBack);
    }
    
    private void initView () {
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
        bt1 = (Button) findViewById (R.id.bt1);
        bt2 = (Button) findViewById (R.id.bt2);
        bt3 = (Button) findViewById (R.id.bt3);
        bt4 = (Button) findViewById (R.id.bt4);
        bt5 = (Button) findViewById (R.id.bt5);
        bt6 = (Button) findViewById (R.id.bt6);
        bt7 = (Button) findViewById (R.id.bt7);
        bt8 = (Button) findViewById (R.id.bt8);
        bt9 = (Button) findViewById (R.id.bt9);
        bt0 = (Button) findViewById (R.id.bt0);
        btPeriod = (Button) findViewById (R.id.btPeriod);
        rlBack = (RelativeLayout) findViewById (R.id.rlBack);
        ivDelete = (ImageView) findViewById (R.id.ivDelete);
        ivNext = (ImageView) findViewById (R.id.ivNext);
        etPIN = (EditText) findViewById (R.id.etPIN);
        tvPin1 = (TextView) findViewById (R.id.tvPin1);
        tvPin2 = (TextView) findViewById (R.id.tvPin2);
        tvPin3 = (TextView) findViewById (R.id.tvPin3);
        tvPin4 = (TextView) findViewById (R.id.tvPin4);
        llPinButtons = (LinearLayout) findViewById (R.id.llPinButton);
    }
    
    private void initListener () {
        etPIN.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
            }
        
            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                if (s.toString ().trim ().length () == 4) {
                    if (checkPIN (Integer.parseInt (s.toString ().trim ()))) {
                        Utils.hideSoftKeyboard (LoginActivity.this);
                        Constants.login = true;
                        Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity (intent);
                        overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                    } else {
                        tvPin1.setBackgroundResource (R.drawable.pin_empty);
                        tvPin2.setBackgroundResource (R.drawable.pin_empty);
                        tvPin3.setBackgroundResource (R.drawable.pin_empty);
                        tvPin4.setBackgroundResource (R.drawable.pin_empty);
                        etPIN.setText (null);
                        Utils.shakeView (LoginActivity.this, llPinButtons);
                        Utils.showSnackBar (LoginActivity.this, clMain, getResources ().getString (R.string.snackbar_text_invalid_pin), Snackbar.LENGTH_LONG, null, null);
                    }
                }
            }
        
            @Override
            public void afterTextChanged (Editable s) {
                switch (s.toString ().trim ().length ()) {
                    case 1:
                        tvPin1.setBackgroundResource (R.drawable.pin_filled);
                        tvPin2.setBackgroundResource (R.drawable.pin_empty);
                        tvPin3.setBackgroundResource (R.drawable.pin_empty);
                        tvPin4.setBackgroundResource (R.drawable.pin_empty);
                        break;
                    case 2:
                        tvPin1.setBackgroundResource (R.drawable.pin_filled);
                        tvPin2.setBackgroundResource (R.drawable.pin_filled);
                        tvPin3.setBackgroundResource (R.drawable.pin_empty);
                        tvPin4.setBackgroundResource (R.drawable.pin_empty);
                        break;
                    case 3:
                        tvPin1.setBackgroundResource (R.drawable.pin_filled);
                        tvPin2.setBackgroundResource (R.drawable.pin_filled);
                        tvPin3.setBackgroundResource (R.drawable.pin_filled);
                        tvPin4.setBackgroundResource (R.drawable.pin_empty);
                        break;
                    default:
                        tvPin1.setBackgroundResource (R.drawable.pin_empty);
                        tvPin2.setBackgroundResource (R.drawable.pin_empty);
                        tvPin3.setBackgroundResource (R.drawable.pin_empty);
                        tvPin4.setBackgroundResource (R.drawable.pin_empty);
                        break;
                }
            }
        });
        ivDelete.setOnClickListener (this);
        bt0.setOnClickListener (this);
        bt1.setOnClickListener (this);
        bt2.setOnClickListener (this);
        bt3.setOnClickListener (this);
        bt4.setOnClickListener (this);
        bt5.setOnClickListener (this);
        bt6.setOnClickListener (this);
        bt7.setOnClickListener (this);
        bt8.setOnClickListener (this);
        bt9.setOnClickListener (this);
        rlBack.setOnClickListener (this);
    }
    
    private boolean checkPIN (int pin) {
        // Check the PIN entered. We use 1234 as default PIN
        return pin == 1234;
    }
    
    @Override
    public void onClick (View v) {
        String pin = "";
        switch (v.getId ()) {
            case R.id.bt0:
                pin = etPIN.getText ().toString () + "0";
                break;
            case R.id.bt1:
                pin = etPIN.getText ().toString () + "1";
                break;
            case R.id.bt2:
                pin = etPIN.getText ().toString () + "2";
                break;
            case R.id.bt3:
                pin = etPIN.getText ().toString () + "3";
                break;
            case R.id.bt4:
                pin = etPIN.getText ().toString () + "4";
                break;
            case R.id.bt5:
                pin = etPIN.getText ().toString () + "5";
                break;
            case R.id.bt6:
                pin = etPIN.getText ().toString () + "6";
                break;
            case R.id.bt7:
                pin = etPIN.getText ().toString () + "7";
                break;
            case R.id.bt8:
                pin = etPIN.getText ().toString () + "8";
                break;
            case R.id.bt9:
                pin = etPIN.getText ().toString () + "9";
                break;
            case R.id.ivDelete:
                if (etPIN.getText ().toString ().length () > 0) {
                    pin = etPIN.getText ().toString ().substring (0, etPIN.getText ().toString ().length () - 1);
                }
                break;
            case R.id.rlBack:
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                break;
    
        }
        etPIN.setText (pin);
    }
    
    @Override
    public void onBackPressed () {
        super.onBackPressed ();
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
