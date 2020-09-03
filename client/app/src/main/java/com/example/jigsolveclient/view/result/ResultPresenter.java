package com.example.jigsolveclient.view.result;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.jigsolveclient.base.BasePresenter;
import com.example.jigsolveclient.rest.RestClient;
import com.example.jigsolveclient.rest.RestService;

import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultPresenter extends BasePresenter<ResultView> {



    public void attemptToLoadResultPicture(){
        Call<ResponseBody> call = RestClient.getInstance().getProcessResult();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                        view.setResultPicture(bitmap);
                        Log.i("result", "Result ready");
                    }
                    else {
                        //TODO exception when body is empty
                        Log.e("result-exception", "Brak dopasowania");
                    }
                }else {
                    //TODO exception when response isn't successful
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("result-exception", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    private void convertBitsToResultPicture(){}
}
