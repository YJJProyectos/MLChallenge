package com.jyang.busquedaml.modulos.descripcion;

import com.jyang.busquedaml.modelo.DescripcionResponse;

import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

public class OnDescripcionResponse implements SingleObserver<Response<DescripcionResponse>> {

    private DescripcionContract.Model.OnFinishListener onFinishListener;
    private Disposable mSubscription;

    public OnDescripcionResponse(DescripcionContract.Model.OnFinishListener onFinishListener){
        this.onFinishListener = onFinishListener;
    }

    @Override
    public void onSubscribe(Disposable subscription) {
        this.mSubscription = subscription;
    }

    @Override
    public void onSuccess(Response<DescripcionResponse> descripcionResponseResponse) {

        if (descripcionResponseResponse.isSuccessful()) {
            onFinishListener.onFinished(descripcionResponseResponse.body());
        } else {
            onFinishListener.onFinishedError();
        }

        this.mSubscription.dispose();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof UnknownHostException || e instanceof ConnectException) {
            onFinishListener.onFailureConnection(e);
        } else {
            onFinishListener.onFailure(e);
        }
        this.mSubscription.dispose();
    }
}
