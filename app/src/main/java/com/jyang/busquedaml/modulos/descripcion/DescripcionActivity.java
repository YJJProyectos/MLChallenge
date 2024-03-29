package com.jyang.busquedaml.modulos.descripcion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jyang.busquedaml.R;
import com.jyang.busquedaml.adapter.AtributosAdapter;
import com.jyang.busquedaml.adapter.DescripcionImagenesAdapter;
import com.jyang.busquedaml.modelo.Atributo;
import com.jyang.busquedaml.modelo.DescripcionResponse;
import com.jyang.busquedaml.modelo.ImagenResponse;
import com.jyang.busquedaml.utils.Format;

import java.util.ArrayList;
import java.util.List;


import static com.jyang.busquedaml.utils.Constantes.KEY_PRODUCTO_ID;


public class DescripcionActivity extends AppCompatActivity implements  DescripcionContract.View {

    private static final String TAG = "DescripcionActivity";

    private List<Atributo> atributos;
    private List<ImagenResponse> imagenes;
    private RecyclerView rvAtributos;
    private RecyclerView rvImagenes;
    private AtributosAdapter atributosAdapter;
    private DescripcionImagenesAdapter descripcionImagenesAdapter;
    private TextView titulo;
    private TextView precio;
    private TextView cantidadDisponible;
    private TextView cantidadVendido;
    private TextView textoError;
    private ImageView imageView;
    private View layoutData;
    private View layoutError;
    private String idProducto;
    private Button botonReintentar;
    private ProgressBar progressBar;

    private DescripcionPresenter descripcionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        findComponents();
        listeners();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.descripcion));

        this.descripcionPresenter = new DescripcionPresenter(this);
        this.descripcionPresenter.requestDescripcionData(this.idProducto);


    }

    private void findComponents() {
        titulo = findViewById(R.id.tituloProducto);
        precio = findViewById(R.id.precioProducto);
        layoutData = findViewById(R.id.layoutData);
        layoutError = findViewById(R.id.layoutError);
        imageView = findViewById(R.id.imagenProducto);
        botonReintentar = findViewById(R.id.botonReintentar);
        progressBar = findViewById(R.id.loading);
        textoError = findViewById(R.id.textoError);
        cantidadDisponible = findViewById(R.id.cantidad_disponible);
        rvAtributos = findViewById(R.id.rvAtributos);
        cantidadVendido = findViewById(R.id.cantidad_vendido);
        rvImagenes = findViewById(R.id.rvImagenes);

        Intent mIntent = getIntent();
        this.idProducto = mIntent.getStringExtra(KEY_PRODUCTO_ID);
        titulo.setText(idProducto);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvAtributos.setLayoutManager(llm);
        rvAtributos.setNestedScrollingEnabled(false);
        this.atributos = new ArrayList<>();
        atributosAdapter = new AtributosAdapter(this.atributos, this);
        rvAtributos.setAdapter(atributosAdapter);

        LinearLayoutManager llmImagenes = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        rvImagenes.setLayoutManager(llmImagenes);
        this.imagenes = new ArrayList<>();
        descripcionImagenesAdapter = new DescripcionImagenesAdapter(this.imagenes, this);
        rvImagenes.setAdapter(descripcionImagenesAdapter);

    }


    private void listeners(){
        botonReintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descripcionPresenter.requestDescripcionData(idProducto);
            }
        });
    }

    public void showLoading(){
        layoutData.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void stopLoading(){
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResponseSuccess(DescripcionResponse descripcion) {
        titulo.setText(descripcion.getTitulo());
        precio.setText(Format.formatDecimalSignal(descripcion.getPrecio()));
        cantidadDisponible.setText(getResources().getString(R.string.cantidad_disponible) + descripcion.getCantidad_disponible());
        cantidadVendido.setText(getResources().getString(R.string.cantidad_vendido) + descripcion.getCantidad_vendido());

        this.atributos.clear();
        this.atributos.addAll(descripcion.getAtributos());
        this.atributosAdapter.notifyDataSetChanged();

        this.imagenes.clear();
        this.imagenes.addAll(descripcion.getImagenes());
        this.descripcionImagenesAdapter.notifyDataSetChanged();

        Glide.with(this)
                .load(descripcion.getImagen())
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(imageView);
        layoutData.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.descripcionPresenter.onDestroy();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        showErrorDescripcionService(getResources().getString(R.string.errorDescripcionServicio));
        Log.e(TAG, "error " + throwable.getClass() + throwable.getMessage(), throwable);
    }

    @Override
    public void onResponseFailureConnection(Throwable throwable) {
        showErrorDescripcionService(getResources().getString(R.string.errorConexionServicio));
        Log.e(TAG, "error " + throwable.getClass() + throwable.getMessage(), throwable);
    }


    @Override
    public void onResponseError() {
        showErrorDescripcionService(getResources().getString(R.string.errorDescripcionServicio));
    }

    private void showErrorDescripcionService(String mensajeError){
        layoutData.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        textoError.setText(mensajeError);
    }


}
