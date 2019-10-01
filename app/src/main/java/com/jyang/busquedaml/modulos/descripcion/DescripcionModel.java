package com.jyang.busquedaml.modulos.descripcion;

import com.jyang.busquedaml.service.producto.DescripcionService;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class DescripcionModel implements  DescripcionContract.Model {

    @Override
    public void getDescripcion(OnFinishListener onFinishListener, String id) {
        DescripcionService descripcionService = new DescripcionService();
        descripcionService.productos(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new OnDescripcionResponse(onFinishListener));
    }
}
