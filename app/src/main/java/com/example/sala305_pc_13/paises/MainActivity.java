package com.example.sala305_pc_13.paises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sala305_pc_13.paises.WebService.Asynchtask;
import com.example.sala305_pc_13.paises.WebService.Paises;
import com.example.sala305_pc_13.paises.WebService.WebService;
import com.example.sala305_pc_13.paises.adaptadores.AdaptadorPaises;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    ListView listaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaView = (ListView) this.findViewById(R.id.lista);

        enviar();

        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Detalle_Pais.class);
                Bundle b = new Bundle();
                b.putString("CODIGO", ((Paises)a.getItemAtPosition(position)).getCodigo());
                intent.putExtras(b);
                startActivity(intent);
            }
        });


    }


    public void enviar()
    {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService ( "http://services.groupkt.com/country/get/all" , datos,
                MainActivity.this, MainActivity.this);


        ws.execute("");


    }

    @Override
    public void processFinish(String result) throws JSONException {
        JSONObject jsonObject = new JSONObject(result);
        //JSONObject contacto = jsonObject.getJSONObject(0);
        JSONObject res = jsonObject.getJSONObject("RestResponse");
        JSONArray pais = res.getJSONArray("result");


        ArrayList<Paises> paises = Paises.JsonObjectsBuild(pais);
        //Revista[] revistas = new Revista[contact.length()];

        AdaptadorPaises adaptadorRevista = new AdaptadorPaises(this, paises);

        listaView.setAdapter(adaptadorRevista);



    }
}
