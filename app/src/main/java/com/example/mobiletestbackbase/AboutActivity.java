package com.example.mobiletestbackbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity implements About.View {

    private TextView country;
    private TextView name;
    private TextView _id;
    private TextView lon;
    private TextView lat;
    private ProgressBar progressBar;
    private android.view.View errorView;
    private android.view.View infoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AboutPresenterImpl aboutPresenter = new AboutPresenterImpl(this, this);
        country = findViewById(R.id.country);
        name = findViewById(R.id.name);
        _id = findViewById(R.id._id);
        lon = findViewById(R.id.lon);
        lat = findViewById(R.id.lat);
        progressBar = findViewById(R.id.progressBar);
        errorView = findViewById(R.id.errorView);
        infoContainer = findViewById(R.id.infoContainer);
        aboutPresenter.getAboutInfo();
    }

    @Override
    public void setCountry(String countryString) {
        infoContainer.setVisibility(android.view.View.VISIBLE);
        country.setText(countryString);
    }

    @Override
    public void setName(String nameString) {
        name.setText(nameString);
    }

    @Override
    public void set_idCode(int _id) {
        this._id.setText(_id);
    }

    @Override
    public void setLat(double lat) {
        lon.setText(Double.toString(lat));
    }

    @Override
    public void setLon(double lon) {
        lat.setText(Double.toString(lon));
    }

    @Override
    public void showError() {
        errorView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(android.view.View.GONE);
    }
}
