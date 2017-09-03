package com.karman.ledgerex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.karman.ledgerex.R;
import com.karman.ledgerex.utils.Constants;
import com.karman.ledgerex.utils.Utils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivNavigation;
    View bottomSheet;
    ImageView ivSwipe;
    RelativeLayout rlBottomSheet;
    Button btSend, btReceive, btTransfer;
    
    private BottomSheetBehavior mBottomSheetBehavior;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        initView ();
        initData ();
        initListener ();
        isLogin ();
    }
    
    
    private void initView () {
        bottomSheet = findViewById (R.id.bottom_sheet);
        ivNavigation = (ImageView) findViewById (R.id.ivNavigation);
        ivSwipe = (ImageView) findViewById (R.id.ivSwipe);
        rlBottomSheet = (RelativeLayout) findViewById (R.id.rlBottomSheet);
        btSend = (Button) findViewById (R.id.btSend);
        btReceive = (Button) findViewById (R.id.btReceive);
        btTransfer = (Button) findViewById (R.id.btTransfer);
    }
    
    private void initData () {
        mBottomSheetBehavior = BottomSheetBehavior.from (bottomSheet);
        mBottomSheetBehavior.setPeekHeight ((int) Utils.pxFromDp (this, 76));
        mBottomSheetBehavior.setState (BottomSheetBehavior.STATE_COLLAPSED);
        Utils.setTypefaceToAllViews (this, ivNavigation);
    }
    
    private void initListener () {
        btSend.setOnClickListener (this);
        btReceive.setOnClickListener (this);
        btTransfer.setOnClickListener (this);
        rlBottomSheet.setOnClickListener (this);
        mBottomSheetBehavior.setBottomSheetCallback (new BottomSheetBehavior.BottomSheetCallback () {
            @Override
            public void onStateChanged (@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        ivSwipe.setImageResource (R.drawable.ic_swipe_down);
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        ivSwipe.setImageResource (R.drawable.ic_swipe_up);
                        break;
                }
            }
            
            @Override
            public void onSlide (@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });
    }
   
    private void isLogin () {
        if (! Constants.login) {
            Intent intent = new Intent (MainActivity.this, LoginActivity.class);
            startActivity (intent);
            finish ();
        }
    }
    
    @Override
    public void onClick (View v) {
        switch (v.getId ()) {
            case R.id.rlBottomSheet:
                if (mBottomSheetBehavior.getState () == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState (BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    mBottomSheetBehavior.setState (BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.btSend:
                Intent intent = new Intent (MainActivity.this, SendActivity.class);
                startActivity (intent);
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.btReceive:
                Intent intent2 = new Intent (MainActivity.this, ReceiveActivity.class);
                startActivity (intent2);
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.btTransfer:
                break;
            
        }
    }
    
    
    @Override
    public void onBackPressed () {
        MaterialDialog dialog = new MaterialDialog.Builder (this)
                .limitIconToDefaultSize ()
                .content ("Close the application?")
                .positiveText ("Yes")
                .negativeText ("No")
                .typeface (Utils.getTypeface (MainActivity.this), Utils.getTypeface (MainActivity.this))
                .onPositive (new MaterialDialog.SingleButtonCallback () {
                    @Override
                    public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish ();
                        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }).build ();
        dialog.show ();
    }
}