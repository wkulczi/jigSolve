package com.example.jigsolveclient.view.start;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.jigsolveclient.R;
import com.example.jigsolveclient.base.BaseActivity;
import com.example.jigsolveclient.navigator.Navigator;

import java.util.Objects;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialog);

        builder.setTitle("Help");
        builder.setMessage(getString(R.string.help_info));

        builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v("Data", "git");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.rgb(57,31,103)));
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
