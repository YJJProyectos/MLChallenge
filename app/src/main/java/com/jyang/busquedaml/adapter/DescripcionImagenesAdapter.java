package com.jyang.busquedaml.adapter;

import android.app.Activity;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.RequestOptions;

import com.jyang.busquedaml.R;
import com.jyang.busquedaml.modelo.ImagenResponse;

import java.util.List;

public class DescripcionImagenesAdapter extends RecyclerView.Adapter<DescripcionImagenesAdapter.ImagenesViewHolder> {

    private List<ImagenResponse> imagenes;
    private static Context mContext;

    public DescripcionImagenesAdapter(List<ImagenResponse> imagenes, Context context) {
        this.imagenes = imagenes;
        this.mContext = context;
    }



    public class ImagenesViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ImagenesViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagenProducto);
        }
    }

    @NonNull
    @Override
    public ImagenesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.descripcion_imagen_card, parent, false);

        ImagenesViewHolder vh = new ImagenesViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagenesViewHolder holder, int position) {
        ImagenResponse imagenResponse = this.imagenes.get(position);

        // loading album cover using Glide library
        Glide.with((Activity) mContext)
                .load(imagenResponse.getUrl_segura())
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.imagenes.size();
    }

}
