package com.jeluchu.retrofitdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jeluchu.retrofitdemo.R;
import com.jeluchu.retrofitdemo.model.Post;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> dataList;
    private Context context;

    public PostAdapter(Context context, List<Post> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    // ITEMS DE LA LISTA Y SU TAMAÑO
    @Override
    public int getItemCount() { return dataList.size(); }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView txtTitle;
        ImageView coverImage;

        PostViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.title);
            coverImage = mView.findViewById(R.id.coverImage);
        }
    }

    // INFO LA VISTA PARA QUE ME CARGUE EL ITEM EN EL ADAPTER
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    // PONGO LOS DATOS EN CADA ITEM DE LA LISTA
    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.txtTitle.setText(dataList.get(position).getTitle());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.coverImage);

    }

}
