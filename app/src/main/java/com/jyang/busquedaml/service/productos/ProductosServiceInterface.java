package com.jyang.busquedaml.service.productos;

import com.jyang.busquedaml.modelo.ProductosResponse;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductosServiceInterface {
    @GET("/sites/MLA/search")
    Single<Response<ProductosResponse>> productos(@Query("q") String query);
}
