package com.jyang.busquedaml.modulos.productos;

import com.jyang.busquedaml.service.productos.ProductosService;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ProductosModel implements ProductosContract.Model {

    @Override
    public void getProductos(OnFinishListener onFinishListener, String query) {
        ProductosService productosService = new ProductosService();
        productosService.productos(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new OnProductosResponse(onFinishListener));
    }
}
