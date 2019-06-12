package com.example.mobiletestbackbase;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.lang.ref.WeakReference;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 */

public class AboutPresenterImpl implements About.Presenter {

    private static final String TAG = "AboutPresenterImpl";
    private final WeakReference<About.View> aboutView;
    private final AboutModelImpl aboutModel;

    public AboutPresenterImpl(About.View view, @NonNull Context context){
        this.aboutView = new WeakReference<>(view);
        this.aboutModel = new AboutModelImpl(this, context);
    }

    @Override
    public void getAboutInfo() {
        About.View aboutViewImpl = aboutView.get();

        aboutViewImpl.showProgress();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                aboutModel.getAboutInfo();
            }
        }, 1000);
    }

    @Override
    public void onSuccess(AboutInfo aboutInfo) {
        About.View aboutViewImpl = aboutView.get();

        if(aboutViewImpl != null){
            aboutViewImpl.hideProgress();
            aboutViewImpl.setCountry(aboutInfo.getCountry());
            Log.d(TAG,"Country works");
            aboutViewImpl.setName(aboutInfo.getName());
            Log.d(TAG,"name works");
            aboutViewImpl.setLat(aboutInfo.getCoord().getLat());
            Log.d(TAG,"lat works");
            aboutViewImpl.setLon(aboutInfo.getCoord().getLon());
            Log.d(TAG,"lon works");
            aboutViewImpl.set_idCode(aboutInfo.get_id());
        }

    }

    @Override
    public void onFail() {
        About.View aboutViewImpl = aboutView.get();
        if (aboutViewImpl != null){
            aboutViewImpl.hideProgress();
            aboutViewImpl.showError();
        }
    }
}
