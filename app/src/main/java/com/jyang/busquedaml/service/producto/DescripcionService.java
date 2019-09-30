package com.jyang.busquedaml.service.producto;

import com.jyang.busquedaml.modelo.DescripcionResponse;
import com.jyang.busquedaml.service.base.BaseService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class DescripcionService extends BaseService {

    private DescripcionServiceInterface productoService;

    public DescripcionService() {
        super();
        productoService = retrofit.create(DescripcionServiceInterface.class);
    }

    public Single<Response<DescripcionResponse>> productos(String id) {
        return productoService.descripcion(id)
                .subscribeOn(Schedulers.io());
    }
}
