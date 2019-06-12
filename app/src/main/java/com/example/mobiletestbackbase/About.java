package com.example.mobiletestbackbase;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 * MVP contract for AboutActivity
 */

public interface About {

    interface Model {
        void getAboutInfo();
    }

    interface Presenter {
        void getAboutInfo();
        void onSuccess(AboutInfo aboutInfo);
        void onFail();
    }

    interface View {
        void setCountry(String country);
        void setName(String name);
        void set_idCode(int _id);
        void setLat(double lat);
        void setLon(double lon);
        void showError();
        void showProgress();
        void hideProgress();
    }
}
