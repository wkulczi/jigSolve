package com.example.jigsolveclient.view.home;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.jigsolveclient.base.BaseView;

public interface HomeView extends BaseView {

    void setProcessButtonEnable(boolean enable);

    void showPictureImgRequired();
    void showPuzzleImgRequired();
    void showDataRequired();

    void onProcessButtonSucceeded();

    //picture
    void setPicture(Uri pictureUri);
    Uri getPicture();

    //puzzle
    void setPuzzle(Bitmap bitmap);
    Bitmap getPuzzle();
}
