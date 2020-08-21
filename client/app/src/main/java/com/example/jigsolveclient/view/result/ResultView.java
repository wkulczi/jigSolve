package com.example.jigsolveclient.view.result;

import android.graphics.Bitmap;

import com.example.jigsolveclient.base.BaseView;

public interface ResultView extends BaseView {

    void setResultPicture(Bitmap bitmap);
    void resetResultPicture();

}
