package hr.tvz.android.giggle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AplicationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(
            context,
            R.string.daporuka,
            Toast.LENGTH_LONG
        ).show()
    }
}