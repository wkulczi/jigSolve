package com.example.jigsolveclient.view.home;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.example.jigsolveclient.base.BasePresenter;
import com.example.jigsolveclient.rest.RestClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter extends BasePresenter<HomeView> {



    void attemptToLoadSinglePuzzle(Bitmap puzzle) {

        File puzzleFileToUpload = convertBitmapToFile("puzzle", puzzle);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpg"), puzzleFileToUpload);


        MultipartBody.Part body = MultipartBody.Part.createFormData("puzzle", puzzleFileToUpload.getName(), reqFile);

        Call<ResponseBody> call = RestClient.getInstance().uploadPuzzle(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error", t.getMessage());
                }
        });
    }

    void attemptToLoadPicture(final InputStream pictureInputStream) {
        File puzzlePictureFile = convertInputStreamToFile("picture", pictureInputStream);

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/jpg"), puzzlePictureFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", puzzlePictureFile.getName(), reqFile);

        Call<ResponseBody> call = RestClient.getInstance().uploadPuzzlePicture(body);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("Upload", "success");}

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload_error", t.getMessage());
            }
        });
    }

    void attemptToStartSolveProcess() {

    }

    private File convertInputStreamToFile(String filename, final InputStream pictureInputStream) {

        File file = new File(view.getContext().getCacheDir(), filename);

        try{
        OutputStream os = new FileOutputStream(file);
        byte[] buf = new byte[1024];

        int len;
        while ((len = pictureInputStream.read(buf)) > 0) {
            os.write(buf, 0, len);
        }
        os.flush();
        os.close();
            pictureInputStream.close();}
        catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private File convertBitmapToFile(String filename, Bitmap puzzleBitmap){

        File f = new File(view.getContext().getCacheDir(), filename);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Convert bitmap to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        puzzleBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);

        //write the bytes in file
        try {
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return f;
    }
}
