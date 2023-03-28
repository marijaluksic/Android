package hr.tvz.android.listaluksic.push

import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MojFirebaseMessagingService: FirebaseMessagingService() {
    private val TAG = " FCM Service"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        //Ovdje je potrebno hendlati poruke
        //Poslati broadcast, podignuti aktivnost, startati servis ili sl
        val intent = Intent(applicationContext, ShowMessageActivity::class.java)
        intent.putExtra("poruka", remoteMessage)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

        Log.d(MojFirebaseMessagingService().TAG, "From: " + remoteMessage.from)
        Log.d(MojFirebaseMessagingService().TAG, "To: " + remoteMessage.to)
        Log.d(
            MojFirebaseMessagingService().TAG,
            "Notification Message Body: " + remoteMessage.notification!!
                .body
        )
    }

    override fun onNewToken(token: String) {
        Log.d(MojFirebaseMessagingService().TAG, "Refresh token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        // Ovdje je potrebno poslati token na vlastiti server kako bi se točno znalo kamo poruka mora doći
        /*val tokenMap : HashMap<String, String> = HashMap()
        tokenMap["fcmToken"] = token?: ""
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("fcmTokens")
            .document(token?:"")
            .set(tokenMap)
            .addOnSuccessListener { Log.d(TAG, "*************** token was added: ${token}") }
            .addOnFailureListener { Log.d(TAG, "*************** token was NOT added") }*/
    }
}