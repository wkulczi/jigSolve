package com.example.jigsolveclient.view.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.jigsolveclient.R;
import com.example.jigsolveclient.base.BaseActivity;
import com.example.jigsolveclient.navigator.Navigator;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements HomeView {

    private static Integer RECEIVE_FILE = 2;
    private static Integer REQUEST_CAMERA = 1;
    private static Integer SELECT_FILE = 0;

    private InterstitialAd mInterstitialAd;

    @BindView(R.id.picture_img)
    ImageView puzzlePictureImage;

    @BindView(R.id.load_picture_button)
    Button loadPictureButton;

    @BindView(R.id.load_puzzle_button)
    Button loadPuzzleButton;

    @BindView(R.id.process_button)
    Button processButton;

    private AdView mAdView;

    HomePresenter presenter;

    private Uri pictureImage;
    private Bitmap puzzleImage;
    private Uri imageUri;

    public static Intent getStartingIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @SuppressLint("IntentReset")
    @OnClick(R.id.load_picture_button)
    protected void loadPictureOnButtonClick() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, SELECT_FILE);
    }

    @OnClick(R.id.load_puzzle_button)
    protected void loadPuzzleOnButtonClick() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @OnClick(R.id.process_button)
    protected void processButtonClick() {
        if (pictureImage == null && puzzleImage == null) {
            showDataRequired();
        } else if (puzzleImage == null) {
            showPuzzleImgRequired();
        } else if (pictureImage == null) {
            showPictureImgRequired();
        } else {
            if(mInterstitialAd != null && mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
               Navigator.startResult(this);
               finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == REQUEST_CAMERA) {

                Bundle bundle;
                if (data != null) {
                    bundle = data.getExtras();

                    setPuzzle((Bitmap) bundle.get("data"));

                    presenter.attemptToLoadSinglePuzzle(puzzleImage);
                }

            } else if (requestCode == SELECT_FILE) {

                if (data != null) {
                    setPicture(data.getData());
                    int dimensionInPixel = 200;
                    puzzlePictureImage.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInPixel, getResources().getDisplayMetrics());
                    puzzlePictureImage.requestLayout();

                    puzzlePictureImage.setBackgroundResource(R.drawable.image_border);

                    puzzlePictureImage.setImageURI(pictureImage);

                    try {
                        InputStream is = getContentResolver().openInputStream(pictureImage);

                        presenter.attemptToLoadPicture(is);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new HomePresenter();

        presenter.setView(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                Navigator.startResult(HomeActivity.this);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        if(mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if(mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void setProcessButtonEnable(boolean enable) {
        processButton.setEnabled(enable);
    }

    @Override
    public void showPictureImgRequired() {
        Toast.makeText(this, getString(R.string.load_picture_warning), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPuzzleImgRequired() {
        Toast.makeText(this, getString(R.string.load_puzzle_warning), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataRequired() {
        Toast.makeText(this, getString(R.string.load_data_warning), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProcessButtonSucceeded() {
        //TODO attempt to send picture and puzzle to server

    }

    @Override
    public void setPicture(Uri pictureUri) {
        pictureImage = pictureUri;
    }

    @Override
    public Uri getPicture() {
        return pictureImage;
    }

    @Override
    public void setPuzzle(Bitmap bitmap) {
        puzzleImage = bitmap;
    }

    @Override
    public Bitmap getPuzzle() {
        return puzzleImage;
    }
}
