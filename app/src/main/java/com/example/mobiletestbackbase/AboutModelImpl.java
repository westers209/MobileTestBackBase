package com.example.mobiletestbackbase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 */

public class AboutModelImpl implements About.Model {

    private static final String TAG = AboutModelImpl.class.getSimpleName();
    private final About.Presenter presenter;
    private final WeakReference<Context> context;
    private static final int FILE_NAME = R.raw.aboutinfo;

    public AboutModelImpl(@NonNull About.Presenter presenter, @NonNull Context context){
        this.presenter = presenter;
        this.context = new WeakReference<>(context);
    }

    @Override
    public void getAboutInfo() {
        String aboutInfoJson = getAboutInfoFromAssets();

        if(aboutInfoJson != null && !aboutInfoJson.isEmpty()){
    		AboutInfo aboutInfo = parseAboutInfo(aboutInfoJson);
    		if (aboutInfo != null){
        		presenter.onSuccess(aboutInfo);
        		return;
   		 	}
		}

		presenter.onFail();
    }

    private AboutInfo parseAboutInfo(String aboutInfoJson) {
        AboutInfo aboutInfo = null;
        try {
            JSONObject jsonArray = new JSONObject(aboutInfoJson);
            aboutInfo = new AboutInfo();
            aboutInfo.setCountry(jsonArray.getString("country"));
            aboutInfo.setName(jsonArray.getString("name"));
            aboutInfo.getCoord().setLat(jsonArray.getDouble("lat"));
            aboutInfo.getCoord().setLon(jsonArray.getDouble("lon"));
            aboutInfo.set_id(jsonArray.getInt("_id"));
        } catch (JSONException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return aboutInfo;
    }

    private String getAboutInfoFromAssets() {
        if(context.get() != null){
            try{
                InputStream file = context.get().getResources().openRawResource(FILE_NAME);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            }catch (IOException ex){
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }
        return null;
    }
}
