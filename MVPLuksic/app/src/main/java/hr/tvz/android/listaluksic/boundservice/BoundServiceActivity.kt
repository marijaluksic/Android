package hr.tvz.android.listaluksic.boundservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.listaluksic.R

class BoundServiceActivity : AppCompatActivity() {

    var boundService: LocalBoundService? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
    }

    override fun onStart() {
        super.onStart()
        // Binding na LocalService
        val intent = Intent(this, LocalBoundService::class.java)
        bindService(intent, mConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        // Unbind sa servisa
        if (isBound) {
            unbindService(mConnection)
            isBound = false
        }
    }

    val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            // Bound-ali smo se na servis, castamo IBinder kako bi dohvatili instancu servisa
            val binder: LocalBoundService.LocalBinder = service as LocalBoundService.LocalBinder
            boundService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }

    fun onButtonClick(view: android.view.View) {
        if (isBound) {
            //Pozivanje metode servisa
            //Naravno, ako se poziva nešto što bi moglo trajati, ovo je potrebno
            //staviti u zasebnu dretvu
            val num = boundService!!.getRandomNumber()
            Toast.makeText(this, "Got $num problems but Kotlin ain't one!", Toast.LENGTH_SHORT).show()
        }
    }
}