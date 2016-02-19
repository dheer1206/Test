package com.dgulati.test;

import android.content.Context;
import android.webkit.WebChromeClient;
import android.widget.Toast;

/**
 * Created by Dheer on 19-02-2016.
 */
public class JavaScriptINterface extends WebChromeClient{
    Context mContext;

    /** Instantiate the interface and set the context */
    JavaScriptINterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
