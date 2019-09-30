package com.jyang.busquedaml.service.producto;

import com.jyang.busquedaml.modelo.DescripcionResponse;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DescripcionServiceInterface {
    @GET("/items")
    Single<Response<DescripcionResponse>> descripcion(@Query("id") String id);
}
