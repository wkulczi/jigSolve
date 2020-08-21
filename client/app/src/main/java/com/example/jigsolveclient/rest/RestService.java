package com.example.jigsolveclient.rest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RestService {

    @Multipart
    @POST("upload-puzzle")
    Call<ResponseBody> uploadPuzzle (
            @Part MultipartBody.Part filePart);

    @Multipart
    @POST("upload-puzzles")
    Call<ResponseBody> uploadPuzzlePicture (@Part MultipartBody.Part filePart);

    @GET("match-puzzle")
    Call<ResponseBody> getProcessResult ();
}
