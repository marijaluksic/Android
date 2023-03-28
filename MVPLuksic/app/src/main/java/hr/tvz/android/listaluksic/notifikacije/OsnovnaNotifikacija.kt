package hr.tvz.android.listaluksic.notifikacije

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.listaluksic.MainActivity
import hr.tvz.android.listaluksic.MainActivity3
import hr.tvz.android.listaluksic.R

class OsnovnaNotifikacija : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_osnovna_notifikacija)
    }

    fun posaljiNotifikaciju(view: android.view.View) {
        var builder = Notification.Builder(this, MainActivity().MOJ_KANAL)
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .setLargeIcon(BitmapFactory.decodeResource(resources, android.R.drawable.ic_menu_compass))
            .setContentTitle("Kitty patrol")
            .setContentText("Meow mijau!")
            .setSubText("47")
            .setTicker("Claws out")

        val resultIntent = Intent(this, MainActivity3::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            resultIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        builder.setContentIntent(pendingIntent)

        var notification: Notification = builder.build()
        var notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notification)
    }
}