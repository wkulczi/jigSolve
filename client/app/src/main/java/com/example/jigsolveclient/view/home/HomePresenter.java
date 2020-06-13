package com.example.jigsolveclient.view.home;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.Toast;

import com.example.jigsolveclient.base.BasePresenter;
import com.example.jigsolveclient.rest.RestClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter extends BasePresenter<HomeView> {



    void attemptToLoadSinglePuzzle(Bitmap puzzle) throws IOException {

        File puzzleFileToUpload = convertBitmapToFile("puzzle", puzzle);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), puzzleFileToUpload);
        //MultipartBody.Part body = MultipartBody.Part.createFormData("picture", puzzleFileToUpload.getName(), reqFile);
        MultipartBody.Part body = MultipartBody.Part.create(reqFile);

        Call<ResponseBody> call = RestClient.getInstance().uploadPuzzle(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(view.getContext(), response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                }
        });
    }

    void attemptToLoadPicture(final Uri pictureUri) {
        File puzzlePictureFile = convertUriToFile("picture", pictureUri);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), puzzlePictureFile);
        MultipartBody.Part body = MultipartBody.Part.create(reqFile);

        Call<ResponseBody> call = RestClient.getInstance().uploadPuzzlePicture(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(view.getContext(), response.message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    void attemptToStartSolveProcess() {

    }

    private File convertUriToFile(String filename, Uri puzzlePictureUri) {
        return new File(filename, puzzlePictureUri.getPath());
    }

    private File convertBitmapToFile(String filename, Bitmap puzzleBitmap){
        File f = new File(view.getContext().getCacheDir(), filename);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Convert bitmap to byte array
        Bitmap bitmap = puzzleBitmap;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

        //write the bytes in file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
}
