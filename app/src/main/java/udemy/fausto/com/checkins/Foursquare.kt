package udemy.fausto.com.checkins

import android.support.v7.app.AppCompatActivity
import com.foursquare.android.nativeoauth.FoursquareOAuth

// todas las operaciones necesarias van a ir aqui
class Foursquare (var activity : AppCompatActivity, var activtyDestino: AppCompatActivity) {

    private val CODIGO_CONEXION = 200
    private val CODIGO_INTERCAMBIO_TOKEN = 201
    private val CLIENT_ID = ""
    private val CLIENT_SECRET = ""
    private val SETTINGS = "settings"

    init {

    }

    fun iniciarSesion() {
        val intent = FoursquareOAuth.getConnectIntent(activity.applicationContext, CLIENT_ID)
        if (FoursquareOAuth.isPlayStoreIntent(intent)) {
            Mensaje.mensajeError(activity.applicationContext, Errores.NO_HAY_APP_FSQR)
        }

    }






}