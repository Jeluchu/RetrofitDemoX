package com.jeluchu.retrofitdemo.network;

import com.jeluchu.retrofitdemo.model.Post;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetPostService {

    // FUNCION PARA RECOGER LOS DATOS DE LA API
    @GET("/photos")
    Call<List<Post>> getPosts();

}
