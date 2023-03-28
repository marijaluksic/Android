package hr.tvz.android.giggle

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import hr.tvz.android.giggle.databinding.ActivityDisplayGiggleBinding
import hr.tvz.android.giggle.databinding.ActivityDisplayImageGiggleBinding

class DisplayImageGiggle : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayImageGiggleBinding
    lateinit var giggleDAO: GiggleDAO
    lateinit var giggleDb : GiggleDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayImageGiggleBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val giggle : GiggleData? = intent.getSerializableExtra("EXTRA_GIGGLE") as GiggleData?
        if (giggle != null) {
            binding.DisplayImageGiggleTV.text = giggle.title
            DownloadImageFromInternet(binding.DisplayImageGiggleIV).execute(giggle.content)
            binding.DeleteImageButton.setOnClickListener{
                val builder = AlertDialog.Builder(this)

                builder.setMessage(R.string.dialog_pitanje)
                    .setPositiveButton(R.string.da) { dialog, which ->
                        giggleDb = Room.databaseBuilder(
                            applicationContext,
                            GiggleDatabase::class.java, "GiggleDatabase"
                        )
                            .allowMainThreadQueries()
                            .build()
                        giggleDAO = giggleDb.getDao()
                        giggleDAO.delete(giggle)

                        val giggleDeleted = Intent(this, CollectionActivity::class.java)
                        startActivity(giggleDeleted)

                        Toast.makeText(
                            applicationContext,
                            R.string.yes_message,
                            Toast.LENGTH_LONG
                        ).show()
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