package com.jyang.busquedaml.modulos.descripcion;

import com.jyang.busquedaml.modelo.DescripcionResponse;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class OnDescripcionResponse implements SingleObserver<Response<DescripcionResponse>> {

    private DescripcionContract.View viewDescripcion;
    private Disposable mSubscription;

    public OnDescripcionResponse(DescripcionContract.View viewDescripcion){
        this.viewDescripcion = viewDescripcion;
    }

    @Override
    public void onSubscribe(Disposable subscription) {
        this.mSubscription = subscription;
    }

    @Override
    public void onSuccess(Response<DescripcionResponse> descripcionResponseResponse) {
        viewDescripcion.onResponseSuccess(descripcionResponseResponse.body());
        this.mSubscription.dispose();
    }

    @Override
    public void onError(Throwable e) {
        viewDescripcion.onResponseFailure(e);
        this.mSubscription.dispose();
    }
}
