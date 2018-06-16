package com.example.sala305_pc_13.paises.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Paises {
    private String nombre;
    private String codigo;
    private String cpdigo2;

    public Paises(JSONObject a) throws JSONException {
        nombre = a.getString("name").toString();
        codigo = a.getString("alpha2_code").toString();
        cpdigo2 = a.getString("alpha3_code").toString();

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCpdigo2() {
        return cpdigo2;
    }

    public void setCpdigo2(String cpdigo2) {
        this.cpdigo2 = cpdigo2;
    }


    public static ArrayList<Paises> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Paises> paises = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            paises.add(new Paises(datos.getJSONObject(i)));
        }
        return paises;
    }
}
