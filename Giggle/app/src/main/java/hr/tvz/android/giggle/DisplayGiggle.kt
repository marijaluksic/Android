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
import hr.tvz.android.giggle.databinding.ActivityCollectionBinding
import hr.tvz.android.giggle.databinding.ActivityDisplayGiggleBinding

class DisplayGiggle : AppCompatActivity() {

    private lateinit var binding: ActivityDisplayGiggleBinding

    lateinit var giggleDAO: GiggleDAO
    lateinit var giggleDb : GiggleDatabase

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)

                binding = ActivityDisplayGiggleBinding.inflate(layoutInflater)
                val view = binding.root
                setContentView(view)

                val giggle : GiggleData? = intent.getSerializableExtra("EXTRA_GIGGLE") as GiggleData?
                if (giggle != null) {
                    binding.DisplayGiggleTV.text = giggle.title
                    if(giggle.type.lowercase() == (getString(R.string.type_link)).lowercase()) {
                        binding.DisplayGiggleTV1.autoLinkMask = Linkify.ALL
                    }
                    binding.DisplayGiggleTV1.text = giggle.content
                    binding.DeleteButton.setOnClickListener{
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



}