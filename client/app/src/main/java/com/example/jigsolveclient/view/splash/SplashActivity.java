package com.example.jigsolveclient.view.splash;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jigsolveclient.R;
import com.example.jigsolveclient.navigator.Navigator;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class SplashActivity extends AppCompatActivity {

    private Subscription subscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onPause() {
        subscription.unsubscribe();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        subscription.unsubscribe();
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        subscription = Observable.timer(3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onNext(Long aLong) {
                Navigator.startStart(SplashActivity.this);
                finish();
            }
        });
        super.onResume();
    }
}
