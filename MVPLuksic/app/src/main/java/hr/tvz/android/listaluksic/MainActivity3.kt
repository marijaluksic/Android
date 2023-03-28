package hr.tvz.android.listaluksic

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            val intent: Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:0994414146"))
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    applicationContext,
                    R.string.pokretanje,
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                R.string.dozvole,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun shakeBtnClick(view: android.view.View) {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.animacija)
        view.startAnimation(animation)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_share -> {
                val builder = AlertDialog.Builder(this)

                builder.setMessage(R.string.dialogpitanje)
                    .setPositiveButton(R.string.da) { dialog, which ->

                        val uniqueActionString = "hr.tvz.android.listaluksic.broadcast.testbc"
                        val broadcastIntent = Intent()
                        broadcastIntent.action = uniqueActionString
                        sendBroadcast(broadcastIntent)
                    }
                    .setNegativeButton(R.string.ne) { dialog, which ->
                        Toast.makeText(
                            applicationContext,
                            R.string.neporuka,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    .create()
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    }
