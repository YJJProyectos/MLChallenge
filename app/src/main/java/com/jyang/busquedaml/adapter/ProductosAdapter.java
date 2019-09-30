package com.jyang.busquedaml.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jyang.busquedaml.R;
import com.jyang.busquedaml.modelo.Producto;
import com.jyang.busquedaml.modulos.productos.OnItemClickListener;
import com.jyang.busquedaml.modulos.productos.ProductosActivity;
import com.jyang.busquedaml.utils.Format;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosViewHolder> {

    private List<Producto> productos;
    private static Context mContext;
    private OnItemClickListener onItemClickListener;

    public ProductosAdapter(List<Producto> productos, Context context, OnItemClickListener onItemClickListener) {
        this.productos = productos;
        this.mContext = context;
        this.onItemClickListener = onItemClickListener;
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView tProducto;
        private TextView tPrecio;


        public ProductosViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagenProducto);
            tProducto = itemView.findViewById(R.id.tituloProducto);
            tPrecio = itemView.findViewById(R.id.precioProducto);
        }
    }

    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Creamos una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.productos_card, parent, false);

        ProductosViewHolder vh = new ProductosViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ProductosViewHolder holder, final int position) {
        Producto producto = productos.get(position);
        holder.tProducto.setText(producto.getTitulo());
        holder.tPrecio.setText(Format.formatDecimalSignal(producto.getPrecio()));

        // loading album cover using Glide library
        Glide.with((Activity) mContext)
                .load(producto.getImagen())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        //holder.imageView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        //holder.imageView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    @Override
    public int getItemCount() {
        return productos.size();
    }


}
