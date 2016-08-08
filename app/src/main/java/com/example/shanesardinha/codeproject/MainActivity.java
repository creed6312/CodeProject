package com.example.shanesardinha.codeproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shanesardinha.codeproject.Interfaces.IMainPresenter;
import com.example.shanesardinha.codeproject.Presenters.MainPresenter;
import com.example.shanesardinha.codeproject.Views.MainView;

public class MainActivity extends AppCompatActivity implements MainView
{
    private IMainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        mainPresenter = new MainPresenter(this);



    }

    @Override
    public void UpdateUI() {

    }

}
