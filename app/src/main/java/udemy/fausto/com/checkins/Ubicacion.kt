package udemy.fausto.com.checkins

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

class Ubicacion (activity: AppCompatActivity, ubicacionListener: UbicacionListener)  {

    private val permisoFineLocation = android.Manifest.permission.ACCESS_FINE_LOCATION
    private val permisoCoarseLocation = android.Manifest.permission.ACCESS_COARSE_LOCATION
    private val CODIGO_SOLICITUD_UBICACION = 100

    private var fusedLocationClient : FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private var callback: LocationCallback? = null
    private var activity = activity

    init {
        fusedLocationClient = FusedLocationProviderClient(activity.applicationContext)

        inicializarLocationRequest()

        callback = object: LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                ubicacionListener.ubicacionResponse(p0!!)
            }

        }
    }

    private fun inicializarLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest?.interval = 10000
        locationRequest?.fastestInterval = 5000
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY


    }


    private fun validarPermisosUbicacion(): Boolean {
       val hayUbicacionPrecisa = ActivityCompat.checkSelfPermission(activity.applicationContext, permisoFineLocation) == PackageManager.PERMISSION_GRANTED
        val hayUbicacionOrdinaria = ActivityCompat.checkSelfPermission(activity.applicationContext, permisoCoarseLocation) == PackageManager.PERMISSION_GRANTED
        return hayUbicacionPrecisa && hayUbicacionOrdinaria
    }


    private fun pedirPermiso() {
        val deboProveerContexto = ActivityCompat.shouldShowRequestPermissionRationale(activity, permisoFineLocation)
    }

}