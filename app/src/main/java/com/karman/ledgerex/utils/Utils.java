package com.karman.ledgerex.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karman.ledgerex.R;

public class Utils {
    public static void shakeView (Activity activity, View view) {
        Animation shake = AnimationUtils.loadAnimation (activity, R.anim.shake);
        view.startAnimation (shake);
    }

    public static void showToast (Activity activity, String message, boolean duration_long) {
        if (duration_long) {
            Toast.makeText (activity, message, Toast.LENGTH_LONG).show ();
        } else {
            Toast.makeText (activity, message, Toast.LENGTH_SHORT).show ();
        }
    }
    
    public static void setTypefaceToAllViews (Activity activity, View view) {
        Typeface tf = getTypeface (activity);
        applyTypeface (getParentView (view), tf);
    }
    
    public static void showLog (int log_type, String tag, String message, boolean show_flag) {
        if (Constants.show_log) {
            if (show_flag) {
                switch (log_type) {
                    case Log.DEBUG:
                        Log.d (tag, message);
                        break;
                    case Log.ERROR:
                        Log.e (tag, message);
                        break;
                    case Log.INFO:
                        Log.i (tag, message);
                        break;
                    case Log.VERBOSE:
                        Log.v (tag, message);
                        break;
                    case Log.WARN:
                        Log.w (tag, message);
                        break;
                    case Log.ASSERT:
                        Log.wtf (tag, message);
                        break;
                }
            }
        }
    }
    
    public static void hideSoftKeyboard (Activity activity) {
        View view = activity.getCurrentFocus ();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService (Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow (view.getWindowToken (), 0);
        }
    }
    
    public static void showSnackBar (Activity activity, CoordinatorLayout coordinatorLayout, String message, int duration, String button_text, View.OnClickListener onClickListener) {
        final Snackbar snackbar = Snackbar.make (coordinatorLayout, message, duration);
        snackbar.setAction (button_text, onClickListener);
        
        View sbView = snackbar.getView ();
        sbView.setBackgroundColor (activity.getResources ().getColor (R.color.primary_dark));
        TextView textView = (TextView) sbView.findViewById (android.support.design.R.id.snackbar_text);
        TextView textView2 = (TextView) sbView.findViewById (android.support.design.R.id.snackbar_action);
        textView.setTextColor (activity.getResources ().getColor (R.color.secondary_text));
        textView2.setTextColor (activity.getResources ().getColor (R.color.secondary_text));
        textView.setTypeface (getTypeface (activity));
        textView2.setTypeface (getTypeface (activity));
        snackbar.show ();
    }
    
    public static float dpFromPx (Context context, float px) {
        return px / context.getResources ().getDisplayMetrics ().density;
    }
    
    public static float pxFromDp (Context context, float dp) {
        return dp * context.getResources ().getDisplayMetrics ().density;
    }
    
    public static Typeface getTypeface (Context c) {
        Typeface typeface = Typeface.createFromAsset (c.getAssets (), Constants.font_name);
        return typeface;
    }
    
    public static Typeface getTypeface (Context c, String font_name) {
        Typeface typeface = Typeface.createFromAsset (c.getAssets (), font_name);
        return typeface;
    }
    
    public static ViewGroup getParentView (View v) {
        ViewGroup vg = null;
        if (v != null)
            vg = (ViewGroup) v.getRootView ();
        return vg;
    }
    
    public static void applyTypeface (ViewGroup v, Typeface f) {
        if (v != null) {
            int vgCount = v.getChildCount ();
            for (int i = 0; i < vgCount; i++) {
                if (v.getChildAt (i) == null)
                    continue;
                if (v.getChildAt (i) instanceof ViewGroup)
                    applyTypeface ((ViewGroup) v.getChildAt (i), f);
                else {
                    View view = v.getChildAt (i);
                    if (view instanceof TextView)
                        ((TextView) (view)).setTypeface (f);
                    else if (view instanceof EditText)
                        ((EditText) (view)).setTypeface (f);
                    else if (view instanceof Button)
                        ((Button) (view)).setTypeface (f);
                }
            }
        }
    }
}


