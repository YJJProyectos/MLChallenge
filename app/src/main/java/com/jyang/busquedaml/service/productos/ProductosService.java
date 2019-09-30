package com.jyang.busquedaml.service.productos;

import com.jyang.busquedaml.modelo.ProductosResponse;
import com.jyang.busquedaml.service.base.BaseService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ProductosService extends BaseService {

    private ProductosServiceInterface productosService;

    public  ProductosService() {
        super();
        productosService = retrofit.create(ProductosServiceInterface.class);
    }

    public Single<Response<ProductosResponse>> productos(String search) {
        return productosService.productos(search)
                .subscribeOn(Schedulers.io());
    }

}
