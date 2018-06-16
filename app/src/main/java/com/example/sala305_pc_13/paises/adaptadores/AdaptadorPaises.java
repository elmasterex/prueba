package com.example.sala305_pc_13.paises.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sala305_pc_13.paises.R;
import com.example.sala305_pc_13.paises.WebService.Paises;

import java.util.ArrayList;

public class AdaptadorPaises extends ArrayAdapter<Paises> {

    public AdaptadorPaises(Context context, ArrayList<Paises> datos) {
        super(context, R.layout.activity_main, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lv_item, null);
        TextView nombre = (TextView)item.findViewById(R.id.Nombre);
        nombre.setText(getItem(position).getNombre());
        TextView codigo = (TextView)item.findViewById(R.id.Codigo);
        codigo.setText(getItem(position).getCodigo());
        return(item);
    }

}