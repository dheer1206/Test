package com.dgulati.test;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

/**
 * Created by Dheer on 18-02-2016.
 */
public class DownloadData {

    Context mContext ;

    public DownloadData(Context context){
        this.mContext = context ;
    }

    public void startDownload(String temp){

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(temp));
        request.setTitle("File Download..");
        request.setDescription("File is downloading...");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, "Xampaperz");

        Log.d("TheURL", MimeTypeMap.getFileExtensionFromUrl(temp)) ;
        DownloadManager manager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

        Toast.makeText(mContext, "Your paper is downloading", Toast.LENGTH_SHORT).show();
        Log.d("TheURL", "Downloaded") ;

    }

}
