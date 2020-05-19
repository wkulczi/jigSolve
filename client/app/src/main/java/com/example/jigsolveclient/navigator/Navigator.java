package com.example.jigsolveclient.navigator;

import android.content.Context;

import com.example.jigsolveclient.view.home.HomeActivity;
import com.example.jigsolveclient.view.result.ResultActivity;
import com.example.jigsolveclient.view.start.StartActivity;

public class Navigator {

    public static void startStart(Context context) {
        context.startActivity(StartActivity.getStartingIntent(context));
    }

    public static void startHome(Context context) {
        context.startActivity(HomeActivity.getStartingIntent(context));
    }

    public static void startResult(Context context) {
        context.startActivity(ResultActivity.getStartingIntent(context));
    }

}
