package tcc.acs_precadastro;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by anail on 09/10/2016.
 */
public class CallWebService {

    public CallWebService (final Context context, double latitude, double longitude){

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
                + latitude + "," +longitude;
        Log.e("URL", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //Log.e("Response: ", response);
                    Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("ERROR", "That didn't work!");
            }
        });

        queue.add(stringRequest);
    }
}
