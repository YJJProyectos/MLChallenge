package com.jyang.busquedaml.modulos.descripcion;


import com.jyang.busquedaml.modelo.DescripcionResponse;

public interface DescripcionContract {

    interface View {
        void onResponseSuccess(DescripcionResponse descripcion);

        void onResponseFailure(Throwable throwable);
    }

    interface Model {

    }
}
