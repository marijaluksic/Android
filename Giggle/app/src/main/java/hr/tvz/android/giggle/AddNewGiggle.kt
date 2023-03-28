package hr.tvz.android.giggle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import hr.tvz.android.giggle.databinding.ActivityAddNewGiggleBinding
import hr.tvz.android.giggle.databinding.ActivityCollectionBinding

class AddNewGiggle : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewGiggleBinding

    lateinit var giggleDAO: GiggleDAO
    lateinit var giggleDb : GiggleDatabase

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityAddNewGiggleBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            val giggleType : String? = intent.getStringExtra("EXTRA_GIGGLE")
            if (giggleType != null) {
                binding.AddNewGiggleTV.text = getString(R.string.title_input)

                if(giggleType == getString(R.string.type_phrase)) {
                    binding.AddNewGiggleTV1.text = getString(R.string.phrase_input)
                }
                else if(giggleType == getString(R.string.type_quote)) {
                    binding.AddNewGiggleTV1.text = getString(R.string.quote_input)
                }
                else {
                    binding.AddNewGiggleTV1.text = getString(R.string.link_input)
                }
                binding.AddNewGiggleButton.setOnClickListener{
                    var newGiggle = GiggleData(
                        null,
                        giggleType,
                        binding.AddNewGiggleET.text.toString(),
                        binding.AddNewGiggleET1.text.toString()
                    )
                    giggleDb = Room.databaseBuilder(
                        applicationContext,
                        GiggleDatabase::class.java, "GiggleDatabase"
                    )
                        .allowMainThreadQueries()
                        .build()
                    giggleDAO = giggleDb.getDao()
                    giggleDAO.insertOne(newGiggle)
                    val message = binding.AddNewGiggleET.text.toString() + getString(R.string.giggle_added)
                    Toast.makeText( applicationContext,message, Toast.LENGTH_SHORT).show()

                    binding.AddNewGiggleET.setText("")
                    binding.AddNewGiggleET1.setText("")
                    val giggleAdded = Intent(this, MainActivity2::class.java)
                    startActivity(giggleAdded)
                }
            }
        }
    }
