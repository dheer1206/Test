package com.dgulati.test;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dheer on 19-02-2016.
 */
public class GalleryFragment extends android.support.v4.app.Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        DownloadData d = new DownloadData(getContext()) ;
        d.startDownload("http://xampaperz.com/xampaperz/xampaperz/dropzone/uploads/1448397776.0849.jpg");



        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
