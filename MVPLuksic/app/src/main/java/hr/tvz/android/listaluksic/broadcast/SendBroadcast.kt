package hr.tvz.android.listaluksic.broadcast

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.listaluksic.R

class SendBroadcast : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }

    fun sendBc(view: android.view.View) {
        val uniqueActionString = "hr.tvz.android.listaluksic.broadcast.testbc"
        val broadcastIntent = Intent()
        broadcastIntent.action = uniqueActionString
        sendBroadcast(broadcastIntent)
    }
}