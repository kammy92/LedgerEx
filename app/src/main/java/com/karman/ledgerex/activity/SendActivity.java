package com.karman.ledgerex.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.karman.ledgerex.R;
import com.karman.ledgerex.utils.SetTypeFace;
import com.karman.ledgerex.utils.Utils;

import java.util.Locale;


public class SendActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rlBack;
    CoordinatorLayout clMain;
    
    TextView tvAmount, tvAmountUSD;
    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt0, btPeriod;
    ImageView ivDelete, ivNext;
    
    String amount = "";
    String amountUSD;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_send);
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
        tvAmount = (TextView) findViewById (R.id.tvAmount);
        tvAmountUSD = (TextView) findViewById (R.id.tvAmountUSD);
    }
    
    private void initListener () {
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
        btPeriod.setOnClickListener (this);
        rlBack.setOnClickListener (this);
        ivNext.setOnClickListener (this);
    }
    
    
    @Override
    public void onClick (View v) {
        switch (v.getId ()) {
            case R.id.bt0:
                amount = amount + "0";
                break;
            case R.id.bt1:
                amount = amount + "1";
                break;
            case R.id.bt2:
                amount = amount + "2";
                break;
            case R.id.bt3:
                amount = amount + "3";
                break;
            case R.id.bt4:
                amount = amount + "4";
                break;
            case R.id.bt5:
                amount = amount + "5";
                break;
            case R.id.bt6:
                amount = amount + "6";
                break;
            case R.id.bt7:
                amount = amount + "7";
                break;
            case R.id.bt8:
                amount = amount + "8";
                break;
            case R.id.bt9:
                amount = amount + "9";
                break;
            case R.id.btPeriod:
                amount = amount + ".";
                break;
            case R.id.ivDelete:
                if (amount.length () > 0) {
                    amount = amount.substring (0, amount.length () - 1);
                }
                break;
            case R.id.rlBack:
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.ivNext:
                MaterialDialog dialog = new MaterialDialog.Builder (this)
                        .limitIconToDefaultSize ()
                        .content ("Send " + amount + " ETH  to XYZ?")
                        .positiveText ("Yes")
                        .negativeText ("No")
                        .typeface (SetTypeFace.getTypeface (SendActivity.this), SetTypeFace.getTypeface (SendActivity.this))
                        .onPositive (new MaterialDialog.SingleButtonCallback () {
                            @Override
                            public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                finish ();
                                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                            }
                        }).build ();
                dialog.show ();
                break;
        }
        if (amount.length () > 0) {
            tvAmount.setText (amount + " ETH");
            tvAmountUSD.setText ("= " + String.format (Locale.US, "%.2f", (Double.parseDouble (amount) * 249.264485691)) + " USD");
        } else {
            tvAmountUSD.setText ("= 0.0 USD");
            tvAmount.setText (" 0.0 ETH");
        }
    }
    
    @Override
    public void onBackPressed () {
        super.onBackPressed ();
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
