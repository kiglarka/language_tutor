package com.codecool.languagetutor.main_activity;

public class MainActivityPresenter <V extends MainActivityView> {
    V view;
    MainActivityPresenter(){

    }

    public void onAttach(V view){
        this.view = view;
    }

    public void onDetach(){
        view = null;
    }

}
