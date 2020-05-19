package com.example.jigsolveclient.base;

import android.content.Context;

public interface BaseView  {

    void showError(String title, String name);
    Context getContext();

    void showLoading();
    void hideLoading();
}
