package com.jyang.busquedaml.modulos.productos;

import com.jyang.busquedaml.modelo.Producto;

import java.util.List;

public class ProductosPresenter implements  ProductosContract.Presenter, ProductosContract.Model.OnFinishListener {

    private ProductosContract.View productosView;
    private ProductosContract.Model productosModel;


    public ProductosPresenter(ProductosContract.View productosView) {
        this.productosView = productosView;
        this.productosModel = new ProductosModel();
    }

    @Override
    public void onFinished(List<Producto> productos) {
        if (this.productosModel != null ) {
            this.productosView.stopLoading();
        }
        this.productosView.setDataToRecyclerView(productos);
    }

    @Override
    public void onFailure(Throwable throwable) {
        if (this.productosModel != null ) {
            this.productosView.stopLoading();
        }
        this.productosView.onResponseFailure(throwable);
    }

    @Override
    public void onFailureConnection(Throwable throwable) {
        if (this.productosModel != null ) {
            this.productosView.stopLoading();
        }
        this.productosView.onResponseFailureConnection(throwable);
    }

    @Override
    public void onFinishedError() {
        if (this.productosModel != null ) {
            this.productosView.stopLoading();
        }
        this.productosView.onResponseError();
    }

    @Override
    public void onDestroy() {
        this.productosModel = null;
    }

    @Override
    public void requestProductosData(String query) {
        if (this.productosModel != null ) {
            this.productosView.showLoading();
        }
        this.productosModel.getProductos(this, query);
    }
}
