package com.jyang.busquedaml.modulos.descripcion;


import com.jyang.busquedaml.modelo.DescripcionResponse;

public interface DescripcionContract {

    interface Model {

        interface OnFinishListener {

            void onFinished(DescripcionResponse descripcion);

            void onFailure(Throwable throwable);

            void onFailureConnection(Throwable throwable);

            void onFinishedError();
        }
        void getDescripcion(OnFinishListener onFinishListener, String id);
    }

    interface View {

        void showLoading();

        void stopLoading();

        void onResponseSuccess(DescripcionResponse descripcion);

        void onResponseFailure(Throwable throwable);

        void onResponseFailureConnection(Throwable throwable);

        void onResponseError();
    }


    interface Presenter {
        void onDestroy();

        void requestDescripcionData(String id);
    }

}
