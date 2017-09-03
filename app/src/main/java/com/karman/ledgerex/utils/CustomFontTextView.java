package com.karman.ledgerex.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by CapternalSystems on 7/4/2016.
 */
public class CustomFontTextView extends AppCompatTextView {
    
    public CustomFontTextView (Context context, AttributeSet attrs, int defStyle) {
        super (context, attrs, defStyle);
        init ();
    }
    
    public CustomFontTextView (Context context, AttributeSet attrs) {
        super (context, attrs);
        init ();
    }
    
    public CustomFontTextView (Context context) {
        super (context);
        init ();
    }
    
    private void init () {
        Typeface tf = Typeface.createFromAsset (getContext ().getAssets (), Constants.font_name);
//        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "MyriadPro-Regular.otf");
        setTypeface (tf);
    }
    
}
