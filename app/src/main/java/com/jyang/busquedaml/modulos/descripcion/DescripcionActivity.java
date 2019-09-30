package com.jyang.busquedaml.modulos.descripcion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jyang.busquedaml.R;
import com.jyang.busquedaml.modelo.DescripcionResponse;
import com.jyang.busquedaml.service.producto.DescripcionService;
import com.jyang.busquedaml.utils.Format;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.jyang.busquedaml.utils.Constantes.KEY_PRODUCTO_ID;


public class DescripcionActivity extends AppCompatActivity implements  DescripcionContract.View{

    private static final String TAG = "DescripcionActivity";

    private TextView titulo;
    private TextView precio;
    private TextView descripcion;
    private TextView textoError;
    private ImageView imageView;
    private View layoutData;
    private View layoutError;
    private String idProducto;
    private Button botonReintentar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);

        findComponents();
        listeners();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.descripcion));


        DescripcionService descripcionService = new DescripcionService();
        descripcionService.productos(this.idProducto)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showLoading();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        stopLoading();
                    }
                })
                .subscribe(new OnDescripcionResponse(this));

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

        Intent mIntent = getIntent();
        this.idProducto = mIntent.getStringExtra(KEY_PRODUCTO_ID);
        titulo.setText(idProducto);

    }


    private void listeners(){
        botonReintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DescripcionService descripcionService = new DescripcionService();
                descripcionService.productos(idProducto)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                showLoading();
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                stopLoading();
                            }
                        })
                        .subscribe(new OnDescripcionResponse(DescripcionActivity.this));
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
        Glide.with(this)
                .load(descripcion.getImagen())
                .apply(new RequestOptions().placeholder(R.drawable.ic_place_holder).error(R.drawable.ic_place_holder))
                .into(imageView);
        layoutData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        layoutData.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        textoError.setText(getResources().getString(R.string.errorDescripcionServicio));
        Log.e(TAG, "error", throwable);
    }
}
