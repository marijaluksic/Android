package hr.tvz.android.listaluksic.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import hr.tvz.android.listaluksic.R


class AplikacijskiReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(
            context,
            R.string.daporuka,
            Toast.LENGTH_LONG
        ).show()
    }
}