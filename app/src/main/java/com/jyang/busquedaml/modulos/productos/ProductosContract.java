package com.jyang.busquedaml.modulos.productos;

import com.jyang.busquedaml.modelo.Producto;

import java.util.List;

public interface ProductosContract {
    interface View {
        void setDataToRecyclerView(List<Producto> productos);

        void onResponseFailure(Throwable throwable);
    }

    interface Model {

    }
}
