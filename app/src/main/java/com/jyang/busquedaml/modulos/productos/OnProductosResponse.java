package com.jyang.busquedaml.modulos.productos;

import com.jyang.busquedaml.modelo.ProductosResponse;

import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class OnProductosResponse implements SingleObserver<Response<ProductosResponse>> {

    private ProductosContract.Model.OnFinishListener onFinishListener;
    private Disposable mSubscription;

    public OnProductosResponse(ProductosContract.Model.OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    @Override
    public void onSubscribe(Disposable subscription) {
        this.mSubscription = subscription;
    }

    @Override
    public void onSuccess(Response<ProductosResponse> productosResponseResponse) {
        if (productosResponseResponse.isSuccessful()) {
            this.onFinishListener.onFinished(productosResponseResponse.body().getProductos());
        } else {
            onFinishListener.onFinishedError();
        }
        mSubscription.dispose();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof UnknownHostException || e instanceof ConnectException) {
            onFinishListener.onFailureConnection(e);
        } else {
            onFinishListener.onFailure(e);
        }
        mSubscription.dispose();
    }
}
