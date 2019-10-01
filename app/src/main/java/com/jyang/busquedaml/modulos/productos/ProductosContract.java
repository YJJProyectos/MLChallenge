package com.jyang.busquedaml.modulos.productos;

import com.jyang.busquedaml.modelo.Producto;

import java.util.List;

public interface ProductosContract {

    interface Model {

        interface OnFinishListener {

            void onFinished(List<Producto> productos);

            void onFailure(Throwable throwable);

            void onFailureConnection(Throwable throwable);

            void onFinishedError();
        }

        void getProductos(OnFinishListener onFinishListener, String query);
    }

    interface View {

        void showLoading();

        void stopLoading();

        void setDataToRecyclerView(List<Producto> productos);

        void onResponseFailure(Throwable throwable);

        void onResponseFailureConnection(Throwable throwable);

        void onResponseError();
    }

    interface Presenter {

        void onDestroy();

        void requestProductosData(String query);
    }
}
