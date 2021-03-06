package udemy.fausto.com.checkins

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class Network (var activity : AppCompatActivity) {

    // tenemos los permisos y estamos conectados

    fun hayRed() : Boolean {
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected

    }

    fun httpRequest(context: Context, url: String, httpResponse: HttpResponse) {

        if (hayRed()) {
            val queue = Volley.newRequestQueue(context)

            val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String>{
                    response ->
                httpResponse.httpResponseSuccess(response)

            }, Response.ErrorListener {
                    error ->
                Log.d("HTTP_REQUEST", error.message )
                Mensaje.mensajeError(context, Errores.HTTP_ERROR)

            })
        } else {
            Mensaje.mensajeError(context, Errores.NO_HAY_RED)

        }






    }





}