package hr.tvz.android.giggle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


class SystemReciever : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        var status: String? = null
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi enabled"
            } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobile data enabled"
            }
        } else {
            status = "No internet is available"
        }
        Toast.makeText(
            context,
            status,
            Toast.LENGTH_LONG
        ).show()
    }
}