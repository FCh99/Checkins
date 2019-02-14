package udemy.fausto.com.checkins

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.foursquare.android.nativeoauth.FoursquareOAuth

// todas las operaciones necesarias van a ir aqui
class Foursquare (var activity : AppCompatActivity, var activityDestino: AppCompatActivity) {

    private val CODIGO_CONEXION = 200
    private val CODIGO_INTERCAMBIO_TOKEN = 201
    private val CLIENT_ID = "5MGLUW5CHINPZJWPWMZ5T1QQSBROF4IEGJSDVDBQ0WY3SW2J"
    private val CLIENT_SECRET = "FAL1KJ1UV5VEBCSQLCPI2HESLSXLFYZVFOPMLVXIG4DVPOOD"
    private val SETTINGS = "settings"

    init {

    }

    fun iniciarSesion() {
        val intent = FoursquareOAuth.getConnectIntent(activity.applicationContext, CLIENT_ID)
        if (FoursquareOAuth.isPlayStoreIntent(intent)) {
            Mensaje.mensajeError(activity.applicationContext, Errores.NO_HAY_APP_FSQR)
            activity.startActivity(intent)
        } else {
            activity.startActivityForResult(intent, CODIGO_CONEXION)

        }


    }


    private fun validarActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        when(requestCode) {
            CODIGO_CONEXION -> { conexionCompleta(resultCode, data) }
            CODIGO_INTERCAMBIO_TOKEN -> { intercambioTokenCompleta( resultCode, data)}
        }
}

    private fun conexionCompleta(resultCode: Int, data: Intent?) {
        val codigoRespuesta = FoursquareOAuth.getAuthCodeFromResult(resultCode, data)
        val excepcion = codigoRespuesta.exception

        if (excepcion == null ) {
            val codigo = codigoRespuesta.code
            realizarIntercambioToken(codigo)
        } else {
            Mensaje.mensajeError(activity.applicationContext, Errores.ERROR_CONEXION_FSQR)
        }

    }

    private fun realizarIntercambioToken(codigo: String) {
        val intent = FoursquareOAuth.getTokenExchangeIntent(activity.applicationContext, CLIENT_ID, CLIENT_SECRET, codigo )
        activity.startActivityForResult(intent, CODIGO_INTERCAMBIO_TOKEN)
    }





    private fun intercambioTokenCompleta(resultCode: Int, data: Intent?) {
        val respuestaToken = FoursquareOAuth.getTokenFromResult(resultCode, data)
        val excepcion = respuestaToken.exception

        if (excepcion == null) {
            val accessToken = respuestaToken.accessToken
            guardarToken()

            navegarSiguienteActividad(activityDestino)

        } else {
            Mensaje.mensajeError(activity.applicationContext, Errores.ERROR_INTERCAMBIO_TOKEN)


        }

    }



}