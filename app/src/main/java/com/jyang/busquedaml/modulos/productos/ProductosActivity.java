package com.jyang.busquedaml.modulos.productos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jyang.busquedaml.R;
import com.jyang.busquedaml.adapter.ProductosAdapter;
import com.jyang.busquedaml.modelo.Producto;
import com.jyang.busquedaml.modulos.descripcion.DescripcionActivity;
import com.jyang.busquedaml.service.productos.ProductosService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.jyang.busquedaml.utils.Constantes.FILTRO;
import static com.jyang.busquedaml.utils.Constantes.KEY_PRODUCTO_ID;

public class ProductosActivity extends AppCompatActivity implements ProductosContract.View, OnItemClickListener {

    private static final String TAG = "ProductosActivity";
    private TextView textView;
    private TextView busquedaSindatos;
    private View layoutData;
    private View layoutError;
    private View layoutSinDatos;
    private ProductosAdapter productosAdapter;
    private List<Producto> productos;
    private RecyclerView rProductos;
    private String busqueda;
    private Button botonReintentar;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        findComponents();
        listeners();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.productos));


        ProductosService productosService = new ProductosService();
        productosService.productos(this.busqueda)
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
                .subscribe(new OnProductosResponse(this));

    }

    private void findComponents() {
        textView = findViewById(R.id.busqueda);
        layoutData = findViewById(R.id.layoutData);
        layoutError = findViewById(R.id.layoutError);
        layoutSinDatos = findViewById(R.id.layoutSinDatos);
        rProductos = findViewById(R.id.recyclerview);
        botonReintentar = findViewById(R.id.botonReintentar);
        progressBar = findViewById(R.id.loading);
        busquedaSindatos = findViewById(R.id.busquedaSindatos);


        this.productos = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rProductos.setLayoutManager(llm);
        productosAdapter = new ProductosAdapter(this.productos, ProductosActivity.this, this);
        rProductos.setAdapter(productosAdapter);

        Intent mIntent = getIntent();
        this.busqueda = mIntent.getStringExtra(FILTRO);
        textView.setText(getResources().getString(R.string.resultadoBusqueda) + busqueda);

    }

    private void listeners(){
        botonReintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductosService productosService = new ProductosService();
                productosService.productos(busqueda)
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
                        .subscribe(new OnProductosResponse(ProductosActivity.this));
            }
        });
    }


    @Override
    public void setDataToRecyclerView(List<Producto> productos) {
        this.productos.clear();
        layoutError.setVisibility(View.GONE);

        if ( productos.size() == 0) {
            layoutData.setVisibility(View.GONE);
            busquedaSindatos.setText(busqueda);
            layoutSinDatos.setVisibility(View.VISIBLE);

        } else {
            layoutSinDatos.setVisibility(View.GONE);
            layoutData.setVisibility(View.VISIBLE);
            this.productos.addAll(productos);
            productosAdapter.notifyDataSetChanged();
        }

    }

    public void showLoading(){
        layoutData.setVisibility(View.GONE);
        layoutError.setVisibility(View.GONE);
        layoutSinDatos.setVisibility(View.GONE);
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
    public void onResponseFailure(Throwable throwable) {
        layoutData.setVisibility(View.GONE);
        layoutSinDatos.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        Log.e(TAG, "error", throwable);

    }

    @Override
    public void onItemClick(int position) {
        if (position == -1) {
            return;
        }
        Intent detailIntent = new Intent(this, DescripcionActivity.class);
        detailIntent.putExtra(KEY_PRODUCTO_ID, productos.get(position).getId());
        startActivity(detailIntent);
    }
}
