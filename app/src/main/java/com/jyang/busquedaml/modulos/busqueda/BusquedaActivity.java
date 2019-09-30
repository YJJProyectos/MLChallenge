package com.jyang.busquedaml.modulos.busqueda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.jyang.busquedaml.modulos.productos.ProductosActivity;
import com.jyang.busquedaml.R;


import static com.jyang.busquedaml.utils.Constantes.FILTRO;

public class BusquedaActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        findComponents();

    }

    private void findComponents(){
        searchView = findViewById(R.id.search);

        searchView.setOnQueryTextListener(this);

    }


    @Override
    public boolean onQueryTextSubmit(String aBuscar) {
        Intent productosIntent = new Intent(this, ProductosActivity.class);
        productosIntent.putExtra(FILTRO, aBuscar);
        startActivity(productosIntent);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
