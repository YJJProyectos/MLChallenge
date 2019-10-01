package com.jyang.busquedaml.modulos.descripcion;

import com.jyang.busquedaml.modelo.DescripcionResponse;

public class DescripcionPresenter implements DescripcionContract.Presenter, DescripcionContract.Model.OnFinishListener {


    private DescripcionContract.Model descripcionModel;
    private DescripcionContract.View descripcionView;

    public DescripcionPresenter(DescripcionContract.View descripcionView) {
        this.descripcionView = descripcionView;
        this.descripcionModel = new DescripcionModel();
    }

    @Override
    public void onFinished(DescripcionResponse descripcion) {
        if ( this.descripcionModel != null){
            this.descripcionView.stopLoading();
        }
        this.descripcionView.onResponseSuccess(descripcion);
    }

    @Override
    public void onFailure(Throwable throwable) {
        if ( this.descripcionModel != null){
            this.descripcionView.stopLoading();
        }
        this.descripcionView.onResponseFailure(throwable);
    }

    @Override
    public void onFailureConnection(Throwable throwable) {
        if ( this.descripcionModel != null){
            this.descripcionView.stopLoading();
        }
        this.descripcionView.onResponseFailureConnection(throwable);
    }

    @Override
    public void onFinishedError() {
        if ( this.descripcionModel != null){
            this.descripcionView.stopLoading();
        }
        this.descripcionView.onResponseError();
    }

    @Override
    public void onDestroy() {
        this.descripcionModel = null;
    }

    @Override
    public void requestDescripcionData(String id) {
        if ( this.descripcionModel != null){
            this.descripcionView.showLoading();
        }
        this.descripcionModel.getDescripcion(this, id);
    }
}
