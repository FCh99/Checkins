package udemy.fausto.com.checkins

import android.content.Context
import android.widget.Toast

class Mensaje {
    companion object {
        fun mensaje(context: Context, mensaje: Mensajes) {
            var str = ""
            when(mensaje) {
                Mensajes.RATIONALE -> str = "Requiero permisos para obtener ubicación"
            }

        }

        fun mensajeError(context: Context, error: Errores) {
            var mensaje = ""

            when(error) {
                Errores.NO_HAY_RED -> {
                    mensaje = "No hay una conexión disponible"

                }
                Errores.HTTP_ERROR-> {
                    mensaje = "Hubo un problema en la solicitud"

                }
                Errores.NO_HAY_APP_FSQR-> {
                    mensaje = "NO hay Aplicación de Foursquare"
                }
                Errores.ERROR_CONEXION_FSQR -> {
                    mensaje= "No se puedo completar la conexión a Foursquare"
                }
                Errores.ERROR_INTERCAMBIO_TOKEN -> {
                    mensaje = "No se pudo completar el intercambio de Token en Foursquare"
                }
                Errores.ERROR_GUARDAR_TOKEN -> {
                    mensaje = "Error al guardar el Token"
                }
                Errores.PERMISO_NEGADO -> {
                    mensaje = "No diste los permisos para obtener tu ubicación"
                }

            }
            Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show()

        }
    }
}