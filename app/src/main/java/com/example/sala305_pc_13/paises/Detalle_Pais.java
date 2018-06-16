package com.example.sala305_pc_13.paises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sala305_pc_13.paises.WebService.Asynchtask;
import com.example.sala305_pc_13.paises.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Detalle_Pais extends AppCompatActivity  implements Asynchtask {

    TextView nombre, capital, codigo, url;
    ImageView imagenpais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__pais);
        nombre = (TextView) this.findViewById(R.id.Nombre);
        capital = (TextView) this.findViewById(R.id.capital);
        url = (TextView) this.findViewById(R.id.url);

        imagenpais = (ImageView) this.findViewById(R.id.imagen);
        Bundle bundle = this.getIntent().getExtras();
        enviar(bundle.getString("CODIGO"));
        Glide.with(this)
                .load("http://www.geognos.com/api/en/countries/flag/"+bundle.getString("CODIGO")+".png")
                .error(R.drawable.pdf)
                .into(imagenpais);

    }


    public void enviar(String codigo)
    {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService ( "http://www.geognos.com/api/en/countries/info/"+codigo+".json" , datos,
                Detalle_Pais.this, Detalle_Pais.this);

        ws.execute("");

    }

    public void llenar(String result)throws JSONException{
        JSONObject jsonObject = new JSONObject(result);
        JSONObject res = jsonObject.getJSONObject("Results");
        nombre.setText("Pais: "+res.getString("Name"));
        JSONObject cap = res.getJSONObject("Capital");
        capital.setText("Pais: "+cap.getString("Name"));
        url.setText("Info : "+res.getString("CountryInfo"));

    }


    @Override
    public void processFinish(String result) throws JSONException {
        llenar(result);
    }
}
