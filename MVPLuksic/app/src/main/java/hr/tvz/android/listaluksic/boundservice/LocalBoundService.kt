package hr.tvz.android.listaluksic.boundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class LocalBoundService: Service() {

    // Binder dodjeljen klijentima
    private val binder = LocalBinder()

    // Random number generator
    private val mGenerator = Random()

    /**
     * Klasa koja se koristi kao ekstenzija Binder-a. Pošto je poznato da se proces
     * ovog servisa uvijek izvršava u istom procesu kao i klijenta, nije potrebno
     * igrati se s IPC (Inter process communication)
     */
    class LocalBinder : Binder() {
        fun getService(): LocalBoundService {
            return LocalBoundService()
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    /** metoda za klijente  */
    fun getRandomNumber(): Int {
        return mGenerator.nextInt(100)
    }
}