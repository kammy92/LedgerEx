package com.karman.ledgerex.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.karman.ledgerex.R;
import com.karman.ledgerex.utils.Utils;

public class MainActivity extends AppCompatActivity {
    public static boolean login = false;
    ImageView ivNavigation;
    View bottomSheet;
    ImageView ivSwipe;
    RelativeLayout rlBottomSheet;
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
    }
    
    private void initData () {
        mBottomSheetBehavior = BottomSheetBehavior.from (bottomSheet);
        mBottomSheetBehavior.setPeekHeight ((int) Utils.pxFromDp (this, 76));
        mBottomSheetBehavior.setState (BottomSheetBehavior.STATE_COLLAPSED);
        Utils.setTypefaceToAllViews (this, ivNavigation);
    }
    
    private void initListener () {
        rlBottomSheet.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                if (mBottomSheetBehavior.getState () == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior.setState (BottomSheetBehavior.STATE_COLLAPSED);
                } else {
                    mBottomSheetBehavior.setState (BottomSheetBehavior.STATE_EXPANDED);
                }
                
            }
        });
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
        if (! login) {
            Intent intent = new Intent (MainActivity.this, LoginActivity.class);
            startActivity (intent);
            finish ();
        }
    }
}