package com.example.api_dummy_json_users1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //SE CREA LA PROPIEDAD PARA APILAR LAS PETICIONES HTTPS
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SE PASA EL CONTEXTO DE LA CLASE
        requestQueue = Volley.newRequestQueue(this);
        String url = "https://dummyjson.com/users"; // Reemplaza con la URL de tu API

        //SE OPTIENE EL OBJETO DEL JSON QUE RETORNA COMO RESPUESTA LA API
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // MANEJAMOS LA RESPUESTA EL JSON
                        try {
                            //OBTENEMOS EL ARRAY DE CON TODOS LOS USARIOS QUE TIENE EL JSON
                            JSONArray usersArray = response.getJSONArray("users");

                            //SE RECORRE EL ARRAY DE CON LOS USUARIOS QUE ESTAN DENTRO DE OBJETOS JSON
                            for (int i = 0; i < usersArray.length(); i++) {
                                //SE OPTIENE EL OBJETO SEGUN LA POSICION EN LA QUE ESTE DENTRO DEL ARRAY
                                JSONObject jsonObject = usersArray.getJSONObject(i);

                                // SE OPTIENEN LOS VALORES QUE SE NECESITAN DEL OBJETO
                                String firstName = jsonObject.getString("firstName");
                                String imageUrl = jsonObject.getString("image");

                                // SE CREA DINAMICAMENTE UN TEXT VIEW PARA HACER LA LISTA DINAMICA DE LOS NOMBRES
                                TextView firstNameTextView = new TextView(MainActivity.this);
                                firstNameTextView.setText(firstName);
                                // SE CREA DINAMICAMENTE UN IMAGE VIEW PARA HACER LA LISTA DINAMICA DE LOS NOMBRES
                                LinearLayout linearLayout = findViewById(R.id.linearLayout); // Reemplaza con el ID de tu contenedor
                                linearLayout.addView(firstNameTextView);

                                // CREACION DEL IMAGEN VIEW PARA MOSTRAR LA IMAGEN
                                ImageView imageView = new ImageView(MainActivity.this);
                                // SE IMPLEMENTA LA LIBRERIA DE PICASSO PARA MOSTRAR LA IMAGEN
                                Picasso.get().load(imageUrl).into(imageView);
                                // SE AGREGA EL IMAGE VIEW A LA ACTTIVIDAD
                                linearLayout.addView(imageView);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // EN CASO DE ERROR SE MUESTRA
                        error.printStackTrace();
                    }
                });

        // Agrega la solicitud a la cola de solicitudes
        requestQueue.add(request);
    }
}