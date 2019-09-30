package com.jyang.busquedaml.modulos.productos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private View layoutData;
    private View layoutError;
    private ProductosAdapter productosAdapter;
    private List<Producto> productos;
    private RecyclerView rProductos;
    private String busqueda;
    //private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        findComponents();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(R.string.productos));


        ProductosService productosService = new ProductosService();
        productosService.productos(this.busqueda)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        //progress.show();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        //progress.dismiss();
                    }
                })
                .subscribe(new OnProductosResponse(this));

    }

    private void findComponents() {
        textView = findViewById(R.id.busqueda);
        layoutData = findViewById(R.id.layoutData);
        layoutError = findViewById(R.id.layoutError);
        rProductos = findViewById(R.id.recyclerview);
        /*if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setTitle(getString(R.string.titulo_loading));
            progress.setMessage(getString(R.string.mensaje_loading));
        }*/
        this.productos = new ArrayList<>();
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rProductos.setLayoutManager(llm);
        productosAdapter = new ProductosAdapter(this.productos, ProductosActivity.this, this);
        rProductos.setAdapter(productosAdapter);

        Intent mIntent = getIntent();
        this.busqueda = mIntent.getStringExtra(FILTRO);
        textView.setText(getResources().getString(R.string.resultadoBusqueda) + busqueda);

    }


    @Override
    public void setDataToRecyclerView(List<Producto> productos) {
        layoutData.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);
        this.productos.clear();
        this.productos.addAll(productos);
        productosAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        layoutData.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        Log.e(TAG, "error", throwable);
        Toast.makeText(this, "error " + throwable.toString(), Toast.LENGTH_SHORT).show();

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
