package hr.tvz.android.giggle

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class LocalBoundService : Service() {
    private val binder = LocalBinder()

    private val mGenerator = Random()

    class LocalBinder : Binder() {
        fun getService(): LocalBoundService {
            return LocalBoundService()
        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    fun getRandomNumber(): Int {
        return mGenerator.nextInt(100)
    }
}