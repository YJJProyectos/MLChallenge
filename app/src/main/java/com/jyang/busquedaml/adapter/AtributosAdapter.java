package com.jyang.busquedaml.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jyang.busquedaml.R;
import com.jyang.busquedaml.modelo.Atributo;
import com.jyang.busquedaml.modelo.AtributoEstructura;

import java.util.List;

public class AtributosAdapter extends RecyclerView.Adapter<AtributosAdapter.AtributosViewHolder> {

    private List<Atributo> atributos;
    private static Context mContext;

    public AtributosAdapter(List<Atributo> atributos, Context context) {
        this.atributos = atributos;
        this.mContext = context;
    }

    public class AtributosViewHolder extends RecyclerView.ViewHolder {

        private TextView atributoNombre;
        private TextView atributoValor;


        public AtributosViewHolder(View itemView) {
            super(itemView);
            atributoNombre = itemView.findViewById(R.id.atributoNombre);
            atributoValor = itemView.findViewById(R.id.atributoValor);
        }
    }

    @NonNull
    @Override
    public AtributosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.atributos_datos, parent, false);

        AtributosViewHolder vh = new AtributosViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AtributosViewHolder holder, int position) {
        Atributo atributo = this.atributos.get(position);
        holder.atributoNombre.setText(atributo.getNombre());
        if ( atributo.getValor() != null && !atributo.getValor().isEmpty()) {
            holder.atributoValor.setText(atributo.getValor());
        } else {
            if (atributo.getAtributoEstructura() != null
                    && atributo.getAtributoEstructura().getNumber() != null
                    && atributo.getAtributoEstructura().getUnit() != null) {
                holder.atributoValor.setText(atributo.getAtributoEstructura().getNumber() + " " + atributo.getAtributoEstructura().getUnit());
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.atributos.size();
    }

}
