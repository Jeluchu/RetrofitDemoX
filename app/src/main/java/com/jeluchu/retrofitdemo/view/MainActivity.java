package com.jeluchu.retrofitdemo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;
import com.jeluchu.retrofitdemo.R;
import com.jeluchu.retrofitdemo.adapter.PostAdapter;
import com.jeluchu.retrofitdemo.model.Post;
import com.jeluchu.retrofitdemo.network.GetPostService;
import com.jeluchu.retrofitdemo.network.RetrofitClientInstance;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        // INSTANCIO E INICIALIZO RETROFIT
        GetPostService service = RetrofitClientInstance.getRetrofitInstance().create(GetPostService.class);

        // REALIZO LAS LLAMADAS PARA OBTENER LOS DATOS
        Call<List<Post>> call = service.getPosts();
        call.enqueue(new Callback<List<Post>>() {

            // RESPUESTA CORRECTA ME CARGA LOS DATOS EN LA LISTA
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }

            // RESPUESTA INCORRECTA ME SALTA UN MENSAJE DE ERROR
            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Algo salió mal, prueba más tarde", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // FUNCION PARA INICIALIZAR LA LISTA Y CARGAR DATOS EN EL ADAPTER
    private void generateDataList(List<Post> photoList) {
        RecyclerView recyclerView = findViewById(R.id.rvPost);
        PostAdapter adapterPost = new PostAdapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterPost);
    }

}
