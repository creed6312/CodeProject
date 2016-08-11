package com.example.shanesardinha.codeproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by shanesardinha on 2016/08/11.
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpViews()
    {
        TextView tvAbout = (TextView) findViewById(R.id.tv_about);
        tvAbout.setText(getResources().getString(R.string.about));

        TextView tvAboutLastFm = (TextView) findViewById(R.id.tv_about_last_fm);
        tvAboutLastFm.setText(getResources().getString(R.string.about_last_fm));
    }
}
