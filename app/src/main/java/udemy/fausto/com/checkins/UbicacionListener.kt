package udemy.fausto.com.checkins

import com.google.android.gms.location.LocationResult

interface UbicacionListener {
    fun ubicacionResponse(locationResult: LocationResult)



}