package com.jyang.busquedaml.modulos.productos;

import com.jyang.busquedaml.modelo.ProductosResponse;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class OnProductosResponse implements SingleObserver<Response<ProductosResponse>> {

    private ProductosContract.View viewProductos;
    private Disposable mSubscription;

    public OnProductosResponse(ProductosContract.View viewProductos){
        this.viewProductos = viewProductos;
    }

    @Override
    public void onSubscribe(Disposable subscription) {
        this.mSubscription = subscription;
    }

    @Override
    public void onSuccess(Response<ProductosResponse> productosResponseResponse) {
        viewProductos.setDataToRecyclerView(productosResponseResponse.body().getProductos());
        mSubscription.dispose();
    }

    @Override
    public void onError(Throwable e) {
        viewProductos.onResponseFailure(e);
        mSubscription.dispose();
    }
}
