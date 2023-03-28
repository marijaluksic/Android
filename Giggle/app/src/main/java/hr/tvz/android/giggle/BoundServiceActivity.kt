package hr.tvz.android.giggle

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast

class BoundServiceActivity : AppCompatActivity() {
    var boundService: LocalBoundService? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service)
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, LocalBoundService::class.java)
        bindService(intent, mConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(mConnection)
            isBound = false
        }
    }

    val mConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
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
            val num = boundService!!.getRandomNumber()
            Toast.makeText(this, "Got $num problems but Kotlin ain't one!", Toast.LENGTH_SHORT).show()
        }
    }
}