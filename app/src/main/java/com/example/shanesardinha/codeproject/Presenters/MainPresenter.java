package com.example.shanesardinha.codeproject.Presenters;

import com.example.shanesardinha.codeproject.Interfaces.IMainPresenter;
import com.example.shanesardinha.codeproject.Views.MainView;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public class MainPresenter implements IMainPresenter {

    private MainView mainView;


    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void webRequest() {

    }

    private void jsonParse()
    {

    }

    public void updateUI()
    {
        mainView.UpdateUI();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void hideProgress() {

    }
}
