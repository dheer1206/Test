package com.dgulati.test;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.MimeTypeMap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;



public class MainFragment extends Fragment {

    private WebView ourBrowser;
    private ProgressBar pb;
    View v;
    LinearLayout ll;
    String dfgh;




    public WebView getOurBrowser() {
        return ourBrowser;
    }


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_main, container, false);

        ourBrowser = (WebView) v.findViewById(R.id.wvBrowser);
        pb = (ProgressBar) v.findViewById(R.id.progressBar);
        ll = (LinearLayout) v.findViewById(R.id.lallan);


        CookieManager.setAcceptFileSchemeCookies(true);

        ourBrowser.getSettings().setLoadWithOverviewMode(true);
        ourBrowser.getSettings().setJavaScriptEnabled(true);

        ourBrowser.getSettings().setUseWideViewPort(true);
        ourBrowser.getSettings().setBuiltInZoomControls(true);
        ourBrowser.setWebViewClient(new ourViewClient());
        ourBrowser.setWebChromeClient(new JavaScriptINterface(getContext()));

        try {
            ourBrowser.loadUrl("http://xampaperz.com");
         //String cookieStart = CookieManager.getInstance().getCookie("http://xampaperz.com") ;
           //Log.d("Cookie", cookieStart) ;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return v;
    }


    private class ourViewClient extends WebViewClient {


        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pb.setVisibility(View.VISIBLE);
            ll.setVisibility(View.VISIBLE);
            Log.d("TheURL", "ShouldGEtVisible");

        }

        public boolean shouldOverrideUrlLoading(WebView wv, final String url) {
            // TODO Auto-generated method stub
            Log.d("TheURL", url);

            boolean b = wv.getSettings().getJavaScriptEnabled();
            Log.d("TheURL", String.valueOf(b));
            dfgh = "http://xampaperz.com/college/exams/xampaperz/paper-viewer.php";



            if (url.length() > 59) {
                if (url.substring(0, 61).equals(dfgh)) {
                    Log.d("TheURL", "Strings Match");
                    wv.getSettings().setJavaScriptEnabled(false);
                } else {
                    ourBrowser.getSettings().setJavaScriptEnabled(true);
                }
            } else {
                wv.getSettings().setJavaScriptEnabled(true);
            }


            wv.loadUrl(url);

            if (MimeTypeMap.getFileExtensionFromUrl(url).contentEquals("jpg")) {

                MainActivity.fab.setVisibility(View.VISIBLE);
                MainActivity.fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("TheURL", "Im here" + url) ;
                        DownloadData dd = new DownloadData(getContext());
                        dd.startDownload(url);
                        MainActivity.fab.setVisibility(View.INVISIBLE);
                    }
                });

            }
            CookieManager.setAcceptFileSchemeCookies(true);
            Log.d("Cookie",url);
            try {
                String cookie = CookieManager.getInstance().getCookie(url);
                Log.d("Cookie", cookie) ;
                Log.d("Cookie", "Saved") ;
            }catch(Exception e){
                Log.d("CookieEx", e.getMessage()) ;
             }
             //Log.d("Cookie",cookie);


            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pb.setVisibility(View.GONE);
            ll.setVisibility(View.GONE);

        }
    }

    public void onBackWebView() {


        Log.d("TheURL", ourBrowser.getUrl()) ;


        if ((MimeTypeMap.getFileExtensionFromUrl(ourBrowser.getUrl()).contentEquals("jpg"))) {
                ourBrowser.getSettings().setJavaScriptEnabled(false);
               MainActivity.fab.setVisibility(View.INVISIBLE);
            }else {
            ourBrowser.getSettings().setJavaScriptEnabled(true);
        }

        ourBrowser.goBack();

    }

//    private String getCookie(String siteName,String CookieName){
//        String CookieValue = null;
//        String[] temp = null ;
//
//        CookieManager cookieManager = CookieManager.getInstance();
//        String cookies = cookieManager.getCookie(siteName);
//        try {
//          temp = cookies.split(";");
//        }catch (Exception e){
//            Log.d("Cookie", e.getMessage()) ;
//        }
//
//        for (String ar1 : temp ){
//            if(ar1.contains(CookieName)){
//                String[] temp1=ar1.split("=");
//                CookieValue = temp1[1];
//            }
//        }
//        return CookieValue;
//    }

}

