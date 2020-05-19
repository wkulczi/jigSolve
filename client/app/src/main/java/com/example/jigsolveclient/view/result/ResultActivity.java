package com.example.jigsolveclient.view.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.jigsolveclient.R;
import com.example.jigsolveclient.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ResultActivity extends BaseActivity {

    @BindView(R.id.result_picture_img)
    ImageView resultPicture;

    @BindView(R.id.reset_button)
    Button resetButton;

    @OnClick(R.id.reset_button)
    protected void onResetButtonClick() {
        finish();
    }

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, ResultActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }
}
