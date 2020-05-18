package com.example.jigsolveclient.view.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.jigsolveclient.R;
import com.example.jigsolveclient.base.BaseActivity;
import com.example.jigsolveclient.navigator.Navigator;

import butterknife.BindView;
import butterknife.OnClick;

public class StartActivity extends BaseActivity {

    @BindView(R.id.start_button)
    Button startButton;

    @BindView(R.id.exit_button)
    Button exitButton;

    @BindView(R.id.help_button)
    Button helpButton;

    public static Intent getStartingIntent(Context context){
        return new Intent(context, StartActivity.class);
    }

    @OnClick(R.id.start_button)
    protected void onStartButtonClick() {
        Navigator.startHome(this);
    }

    @OnClick(R.id.exit_button)
    protected void onExitButtonClick() {
        finish();
    }

    @OnClick(R.id.help_button)
    protected void onHelpButtonClick() {
        //TODO start help activity on button clicked
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    public void showLoading() { }

    @Override
    public void hideLoading() { }
}
