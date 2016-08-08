package com.example.shanesardinha.codeproject.Interfaces;

/**
 * Created by shanesardinha on 2016/08/08.
 */
public interface IMainPresenter {
    void onResume();

    void onItemClicked(int position);

    void onDestroy();

    void hideProgress();
}
