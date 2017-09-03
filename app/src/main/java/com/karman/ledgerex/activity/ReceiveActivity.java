package com.karman.ledgerex.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.karman.ledgerex.R;
import com.karman.ledgerex.utils.Utils;
import com.karman.ledgerex.utils.qr_code.QRContents;
import com.karman.ledgerex.utils.qr_code.QREncoder;


public class ReceiveActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout rlBack;
    CoordinatorLayout clMain;
    
    TextView tvAddress;
    ImageView ivCopy, ivQRCode;
    
    String address = "0x3635774825368dd25";
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_receive);
        initView ();
        initData ();
        initListener ();
    }
    
    private void initData () {
        Utils.setTypefaceToAllViews (this, rlBack);
        tvAddress.setText (address);
        
        WindowManager manager = (WindowManager) getSystemService (WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay ();
        Point point = new Point ();
        display.getSize (point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;
        
        QREncoder qrgEncoder = new QREncoder (address, null, QRContents.Type.TEXT, smallerDimension);
        try {
            // Getting QR-Code as Bitmap
            Bitmap bitmap = qrgEncoder.encodeAsBitmap ();
            // Setting Bitmap to ImageView
            ivQRCode.setImageBitmap (bitmap);
        } catch (WriterException e) {
            e.printStackTrace ();
        }
    }
    
    private void initView () {
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
        rlBack = (RelativeLayout) findViewById (R.id.rlBack);
        ivQRCode = (ImageView) findViewById (R.id.ivQRCode);
        ivCopy = (ImageView) findViewById (R.id.ivCopy);
        tvAddress = (TextView) findViewById (R.id.tvAddress);
    }
    
    private void initListener () {
        ivCopy.setOnClickListener (this);
        rlBack.setOnClickListener (this);
    }
    
    
    @Override
    public void onClick (View v) {
        switch (v.getId ()) {
            case R.id.ivCopy:
                ClipboardManager clipboard = (ClipboardManager) getSystemService (Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText ("ETHEREUM ADDRESS", address);
                clipboard.setPrimaryClip (clip);
                Utils.showToast (ReceiveActivity.this, "Copied to Clipboard!!", false);
                break;
            case R.id.rlBack:
                finish ();
                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                break;
        }
    }
    
    @Override
    public void onBackPressed () {
        super.onBackPressed ();
        finish ();
        overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
