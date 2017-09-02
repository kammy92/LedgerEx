package com.karman.ledgerex.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karman.ledgerex.R;
import com.karman.ledgerex.utils.Utils;


public class LoginActivity extends AppCompatActivity {
    TextView tvNext;
    RelativeLayout rlBack;
    CoordinatorLayout clMain;
    ProgressDialog progressDialog;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        initView ();
        initData ();
        initListener ();
    }
    
    private void initData () {
        progressDialog = new ProgressDialog (LoginActivity.this);
    }
    
    private void initView () {
        clMain = (CoordinatorLayout) findViewById (R.id.clMain);
        rlBack = (RelativeLayout) findViewById (R.id.rlBack);
        Utils.setTypefaceToAllViews (this, rlBack);
    }
    
    private void initListener () {
    }

/*
    private void showAccessPINDialog () {
        final MaterialDialog dialog = new MaterialDialog.Builder (this)
                .customView (R.layout.dialog_password, true)
                .build ();

        TextView tvForgotPIN = (TextView) dialog.getCustomView ().findViewById (R.id.tvForgotPIN);
        final EditText etAccessPIN = (EditText) dialog.getCustomView ().findViewById (R.id.etAccessPIN);
        final EditText etAccessPINTemp = (EditText) dialog.getCustomView ().findViewById (R.id.etAccessPINTemp);
        etAccessPIN.setEnabled (false);
        Utils.setTypefaceToAllViews (this, etAccessPIN);
        etAccessPINTemp.addTextChangedListener (new TextWatcher () {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count) {
                if (s.toString ().trim ().length () == 4) {
                    if (Integer.parseInt (s.toString ().trim ()) == userDetailPref.getIntPref (MainActivity.this, UserDetailsPref.HOSPITAL_ACCESS_PIN)) {
                        Utils.hideSoftKeyboard (MainActivity.this);
                        dialog.dismiss ();
                        showSettingDialog ();
                    } else {
                        Utils.shakeEditText (MainActivity.this, etAccessPIN);
//                        etAccessPIN.setText ("camy― ― ― ―");
                        etAccessPINTemp.setText (etAccessPINTemp.getText ().toString ().substring (0, 0));
                        SpannableString s2 = new SpannableString (getResources ().getString (R.string.snackbar_text_invalid_access_pin));
                        s2.setSpan (new TypefaceSpan (MainActivity.this, Constants.font_name), 0, s.length (), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        etAccessPINTemp.setError (s2);
                        Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_invalid_access_pin), Snackbar.LENGTH_LONG, null, null);
                    }
                }
            }

            @Override
            public void afterTextChanged (Editable s) {
                etAccessPINTemp.setError (null);
                switch (s.toString ().trim ().length ()) {
//                    case 0:
//                        etAccessPIN.setText ("― ― ― ―");
//                        etAccessPIN.setText ("― ― ― ―");
//                        etAccessPIN.setText (Html.fromHtml ("\\u2015 \\u2015 \\u2015 \\u2015"));
//                        break;
                    case 1:
//                        etAccessPIN.setText ("* ― ― ―");
                        etAccessPIN.setText ("• ― ― ―");
//                        etAccessPIN.setText (Html.fromHtml ("\\u2605 \\u2015 \\u2015 \\u2015"));
                        break;
                    case 2:
//                        etAccessPIN.setText ("* * ― ―");
                        etAccessPIN.setText ("• • ― ―");
//                        etAccessPIN.setText (Html.fromHtml ("\\u2605 \\u2605 \\u2015 \\u2015"));
                        break;
                    case 3:
//                        etAccessPIN.setText ("* * * ―");
                        etAccessPIN.setText ("• • • ―");
//                        etAccessPIN.setText (Html.fromHtml ("\\u2605 \\u2605 \\u2605 \\u2015"));
                        break;
//                    case 4:
//                        etAccessPIN.setText ("* * * *");
//                        etAccessPIN.setText ("★ ★ ★ ★");
//                        etAccessPIN.setText ("\\u2605 \\u2605 \\u2605 \\u2605");
//                        break;
                    default:
//                        etAccessPIN.setText ("― ― ― ―");
                        etAccessPIN.setText ("― ― ― ―");
//                        etAccessPIN.setText (Html.fromHtml ("\\u2015 \\u2015 \\u2015 \\u2015"));
                        break;
                }
            }
        });

        tvForgotPIN.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                sendForgotPINRequestToServer ();
            }
        });

        final TextView tv0 = (TextView) dialog.getCustomView ().findViewById (R.id.tv0);
        final TextView tv1 = (TextView) dialog.getCustomView ().findViewById (R.id.tv1);
        final TextView tv2 = (TextView) dialog.getCustomView ().findViewById (R.id.tv2);
        final TextView tv3 = (TextView) dialog.getCustomView ().findViewById (R.id.tv3);
        final TextView tv4 = (TextView) dialog.getCustomView ().findViewById (R.id.tv4);
        final TextView tv5 = (TextView) dialog.getCustomView ().findViewById (R.id.tv5);
        final TextView tv6 = (TextView) dialog.getCustomView ().findViewById (R.id.tv6);
        final TextView tv7 = (TextView) dialog.getCustomView ().findViewById (R.id.tv7);
        final TextView tv8 = (TextView) dialog.getCustomView ().findViewById (R.id.tv8);
        final TextView tv9 = (TextView) dialog.getCustomView ().findViewById (R.id.tv9);
        final ImageView ivBack = (ImageView) dialog.getCustomView ().findViewById (R.id.ivBack);

        ivBack.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    ivBack.setBackgroundResource (R.drawable.button_filled);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ivBack.setImageDrawable (getResources ().getDrawable (R.drawable.ic_delete_white, getApplicationContext ().getTheme ()));
                    } else {
                        ivBack.setImageDrawable (getResources ().getDrawable (R.drawable.ic_delete_white));
                    }
                    if (etAccessPINTemp.getText ().toString ().length () > 0) {
                        etAccessPINTemp.setText (etAccessPINTemp.getText ().toString ().substring (0, etAccessPINTemp.getText ().toString ().length () - 1));
                    }
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ivBack.setImageDrawable (getResources ().getDrawable (R.drawable.ic_delete, getApplicationContext ().getTheme ()));
                    } else {
                        ivBack.setImageDrawable (getResources ().getDrawable (R.drawable.ic_delete));
                    }
                    ivBack.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });

        tv0.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv0.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv0.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "0");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv0.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv0.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });

        tv1.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv1.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv1.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "1");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv1.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv1.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });
        tv2.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv2.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv2.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "2");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv2.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv2.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });
        tv3.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv3.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv3.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "3");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv3.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv3.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });
        tv4.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv4.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv4.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "4");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv4.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv4.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });
        tv5.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv5.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv5.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "5");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv5.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv5.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });
        tv6.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv6.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv6.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "6");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv6.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv6.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });
        tv7.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv7.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv7.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "7");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv7.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv7.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });
        tv8.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv8.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv8.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "8");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv8.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv8.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });
        tv9.setOnTouchListener (new View.OnTouchListener () {
            @Override
            public boolean onTouch (View v, MotionEvent event) {
                if (event.getAction () == MotionEvent.ACTION_DOWN) {
                    tv9.setTextColor (getResources ().getColor (R.color.text_color_white));
                    tv9.setBackgroundResource (R.drawable.button_filled);
                    etAccessPINTemp.setText (etAccessPINTemp.getText ().toString () + "9");
                    return true;
                } else if (event.getAction () == MotionEvent.ACTION_UP) {
                    tv9.setTextColor (getResources ().getColor (R.color.colorPrimary));
                    tv9.setBackgroundResource (R.drawable.button_empty);
                    return true;
                }
                return false;
            }
        });

        dialog.getWindow ().setLayout (CoordinatorLayout.LayoutParams.WRAP_CONTENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        dialog.show ();

        Utils.hideSoftKeyboard (this);
    }

    private void sendLoginDetailsToServer (final String username, final String password) {
        if (NetworkConnection.isNetworkAvailable (LoginActivity.this)) {
            Utils.showProgressDialog (progressDialog, getResources ().getString (R.string.progress_dialog_text_logging_in), true);
            Utils.showLog (Log.INFO, "" + AppConfigTags.URL, AppConfigURL.URL_LOGIN_DEVICE, true);
            StringRequest strRequest1 = new StringRequest (Request.Method.POST, AppConfigURL.URL_LOGIN_DEVICE,
                    new com.android.volley.Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    int login_status = jsonObj.getInt (AppConfigTags.LOGIN_STATUS);
                                    switch (login_status) {
                                        case 0:
                                            Utils.showSnackBar (LoginActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                            break;

                                        case 1:
                                            UserDetailsPref userDetailsPref = UserDetailsPref.getInstance ();
                                            userDetailsPref.putIntPref (LoginActivity.this, UserDetailsPref.DEVICE_ID, jsonObj.getInt (AppConfigTags.DEVICE_ID));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.DEVICE_LOCATION, jsonObj.getString (AppConfigTags.DEVICE_LOCATION));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.HOSPITAL_NAME, jsonObj.getString (AppConfigTags.HOSPITAL_NAME));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.HOSPITAL_LOGO, jsonObj.getString (AppConfigTags.HOSPITAL_LOGO));
//                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.HOSPITAL_LOGIN_KEY, Utils.decrypt (jsonObj.getString (AppConfigTags.HOSPITAL_LOGIN_KEY)));
//                                            userDetailsPref.putIntPref (LoginActivity.this, UserDetailsPref.HOSPITAL_ACCESS_PIN, Integer.parseInt (Utils.decrypt (jsonObj.getString (AppConfigTags.HOSPITAL_ACCESS_PIN))));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.HOSPITAL_LOGIN_KEY, jsonObj.getString (AppConfigTags.HOSPITAL_LOGIN_KEY));
                                            userDetailsPref.putIntPref (LoginActivity.this, UserDetailsPref.HOSPITAL_ACCESS_PIN, jsonObj.getInt (AppConfigTags.HOSPITAL_ACCESS_PIN));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.HOSPITAL_DEFAULT_PATIENT_ID, jsonObj.getString (AppConfigTags.HOSPITAL_DEFAULT_PATIENT_ID));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.SUBSCRIPTION_STATUS, jsonObj.getString (AppConfigTags.SUBSCRIPTION_STATUS));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.SUBSCRIPTION_STARTS, jsonObj.getString (AppConfigTags.SUBSCRIPTION_STARTS));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.SUBSCRIPTION_EXPIRES, jsonObj.getString (AppConfigTags.SUBSCRIPTION_EXPIRES));
                                            userDetailsPref.putStringPref (LoginActivity.this, UserDetailsPref.LANGUAGE, AppConfigTags.english_language_code);

                                            Intent intent = new Intent (LoginActivity.this, MainActivity.class);
                                            intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity (intent);
                                            overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);

                                            break;
                                        case 2:
                                            Utils.showSnackBar (LoginActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                            break;
                                        case 3:
                                            showActiveSessionDialog (username, password);
                                            break;
                                        case 4:
                                            Utils.showSnackBar (LoginActivity.this, clMain, message, Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_retry), new View.OnClickListener () {
                                                @Override
                                                public void onClick (View v) {
                                                    sendLoginDetailsToServer (username, password);
                                                }
                                            });
                                            break;
                                    }
                                    progressDialog.dismiss ();
                                } catch (Exception e) {
                                    progressDialog.dismiss ();
                                    Utils.showSnackBar (LoginActivity.this, clMain, getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace ();
                                }
                            } else {
                                Utils.showSnackBar (LoginActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            progressDialog.dismiss ();
                        }
                    },
                    new com.android.volley.Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            progressDialog.dismiss ();
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            Utils.showSnackBar (LoginActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String> ();
                    params.put (AppConfigTags.USERNAME, username);
//                    params.put (AppConfigTags.PASSWORD, Utils.encrypt (password));
                    params.put (AppConfigTags.PASSWORD, password);
                    params.put (AppConfigTags.DEVICE_IDENTIFICATION, android_id);
                    params.put (AppConfigTags.DEVICE_API_LEVEL, String.valueOf (android.os.Build.VERSION.SDK_INT));
                    params.put (AppConfigTags.DEVICE_OS_VERSION, android.os.Build.VERSION.RELEASE);
                    params.put (AppConfigTags.DEVICE_MANUFACTURER, android.os.Build.MANUFACTURER);
                    params.put (AppConfigTags.DEVICE_MODEL, android.os.Build.MODEL);
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<> ();
                    params.put (AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            Utils.sendRequest (strRequest1, 60);
        } else {
            Utils.showSnackBar (this, clMain, getResources ().getString (R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_go_to_settings), new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    Intent dialogIntent = new Intent (Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity (dialogIntent);
                }
            });
        }
    }
*/
    
}
