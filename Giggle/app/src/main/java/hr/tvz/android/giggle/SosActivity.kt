package hr.tvz.android.giggle

import android.Manifest
import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import hr.tvz.android.giggle.databinding.ActivitySosBinding

class SosActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySosBinding
    lateinit var giggleDAO: GiggleDAO
    lateinit var giggleDb : GiggleDatabase
    private var giggleList: MutableList<GiggleData> = ArrayList<GiggleData>()
    private val MY_PERMISSIONS_REQUEST_SEND_SMS = 0
    private val TIMEOUT = 500
    private val TIMEOUT1 = 1000
    private val TIMEOUT2 = 1500
    private val TIMEOUT3 = 2000
    private val TIMEOUT4 = 2500


    private val TIMEOUT5 = 3000
    private val TIMEOUT6 = 3500
    private val TIMEOUT7 = 4000
    private val TIMEOUT8 = 4500
    private val TIMEOUT9 = 5000
    private val TIMEOUT10 = 5500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySosBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        giggleDb = Room.databaseBuilder(
            applicationContext,
            GiggleDatabase::class.java, "GiggleDatabase"
        )
            .allowMainThreadQueries()
            .build()
        giggleDAO = giggleDb.getDao()
        giggleList = giggleDAO.getAll()
        val giggleImages = giggleList.filter {it.type.lowercase() == getString(R.string.type_image).lowercase()}.count()
        if(giggleImages > 2) {
            val imageList: MutableList<GiggleData> = giggleList.filter{it.type.lowercase() == getString(R.string.type_image).lowercase()}.toMutableList()

            val phraseList: MutableList<GiggleData> = giggleList.filter{it.type.lowercase() == getString(R.string.type_phrase).lowercase()}.toMutableList()
            val fadeIn: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            val fadeOut: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

            val music = MediaPlayer.create(this, R.raw.sound_effect)
            music.start()
            Handler().postDelayed(Runnable {
            binding.SosTV.text = "3"
            binding.SosTV.startAnimation(fadeOut)

            Handler().postDelayed(Runnable {
                binding.SosTV.text = "2"
                binding.SosTV.startAnimation(fadeOut)

                Handler().postDelayed(Runnable{
                    binding.SosTV.text = "1"
                    binding.SosTV.startAnimation(fadeOut)

                    Handler().postDelayed(Runnable{
                        binding.SosTV.text = ""
                        DownloadImageFromInternet(binding.SosIV).execute(imageList[imageList.size-1].content)

                        Handler().postDelayed(Runnable{
                            binding.SosIV.setImageDrawable(null)
                            binding.SosTV.text = phraseList[phraseList.size-1].content


                            Handler().postDelayed({
                                binding.SosTV.text = ""
                                DownloadImageFromInternet(binding.SosIV).execute(imageList[imageList.size-2].content)

                                Handler().postDelayed({
                                    binding.SosIV.setImageDrawable(null)
                                    binding.SosTV.text = phraseList[phraseList.size-2].content


                                    Handler().postDelayed({
                                        binding.SosTV.text = ""
                                        DownloadImageFromInternet(binding.SosIV).execute(imageList[imageList.size-3].content)

                                        Handler().postDelayed({
                                            binding.SosIV.setImageDrawable(null)
                                            binding.SosTV.text = phraseList[phraseList.size-3].content


                                            Handler().postDelayed({
                                                binding.SosIV.setImageDrawable(null)
                                                binding.SosTV.text = "fin"


                                                Handler().postDelayed({
                                                    binding.SosTV.text = ""

                                                    val builder = AlertDialog.Builder(this)

                                                    builder.setMessage(R.string.sos_dialog_pitanje)
                                                        .setPositiveButton(R.string.da) { dialog, which ->
                                                            val browserIntent = Intent(
                                                                Intent.ACTION_VIEW,
                                                                Uri.parse(getString(R.string.rpn_hma_link))
                                                            )
                                                            startActivity(browserIntent)
                                                            Toast.makeText(
                                                                applicationContext,
                                                                R.string.sos_dialog_yes,
                                                                Toast.LENGTH_LONG
                                                            ).show()
                                                        }
                                                        .setNegativeButton(R.string.ne) { dialog, which ->
                                                            Toast.makeText(
                                                                applicationContext,
                                                                R.string.sos_dialog_no,
                                                                Toast.LENGTH_LONG
                                                            ).show()
                                                            if (ContextCompat.checkSelfPermission(this,
                                                                    Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                                                                val uri = Uri.parse("smsto:")
                                                                val it = Intent(Intent.ACTION_SENDTO, uri)
                                                                it.putExtra("sms_body", getString(R.string.text_sos_message))
                                                                try {
                                                                    startActivity(it)
                                                                } catch (e: ActivityNotFoundException) {
                                                                    Toast.makeText(
                                                                        applicationContext,
                                                                        getString(R.string.start_activity_error),
                                                                        Toast.LENGTH_LONG
                                                                    ).show()
                                                                }
                                                            }
                                                            else {
                                                                val browserIntent = Intent(
                                                                    Intent.ACTION_VIEW,
                                                                    Uri.parse(getString(R.string.rpn_hma_link))
                                                                )
                                                                startActivity(browserIntent)
                                                            }
                                                        }
                                                        .create()
                                                        .show()

                                                }, TIMEOUT10.toLong())

                                            }, TIMEOUT9.toLong())


                                        }, TIMEOUT8.toLong())


                                }, TIMEOUT7.toLong())


                        }, TIMEOUT6.toLong())

                    }, TIMEOUT5.toLong())

                }, TIMEOUT4.toLong())
            }, TIMEOUT3.toLong())
        }, TIMEOUT2.toLong())
        }, TIMEOUT1.toLong())
        }, TIMEOUT.toLong())
        }

        else {
            Toast.makeText( applicationContext,getString(R.string.add_more_giggles), Toast.LENGTH_SHORT).show()
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.SEND_SMS)) {
                } else {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.SEND_SMS),
                        MY_PERMISSIONS_REQUEST_SEND_SMS)
                }
            }
            if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    val uri = Uri.parse("smsto:")
                    val it = Intent(Intent.ACTION_SENDTO, uri)
                    it.putExtra("sms_body", getString(R.string.text_sos_message))
                    try {
                        startActivity(it)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.start_activity_error),
                         Toast.LENGTH_LONG
                        ).show()
                    }
            }
            else {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.rpn_hma_link))
                )
                startActivity(browserIntent)
            }
        }
    }


    @SuppressLint("StaticFieldLeak")
    @Suppress("DEPRECATION")
    private inner class DownloadImageFromInternet(var imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {

        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageURL = urls[0]
            var image: Bitmap? = null
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
            }
            catch (e: Exception) {
                Log.e("Error Message", e.message.toString())
                e.printStackTrace()
            }
            return image
        }
        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }

}
