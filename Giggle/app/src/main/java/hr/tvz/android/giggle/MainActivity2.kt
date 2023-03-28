package hr.tvz.android.giggle

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import hr.tvz.android.giggle.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {


    private lateinit var binding: ActivityMain2Binding
    lateinit var giggleDAO: GiggleDAO

    private val MY_PERMISSIONS_REQUEST_SEND_SMS = 0
    lateinit var giggleDb : GiggleDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        giggleDb = Room.databaseBuilder(
            applicationContext,
            GiggleDatabase::class.java, "GiggleDatabase"
        )
            .allowMainThreadQueries()
            .build()
        giggleDAO = giggleDb.getDao()
        if(giggleDAO.getAll().isEmpty()){
            giggleDAO.insertAll(
                GiggleData(
                    1,
                    getString(R.string.type_quote),
                    getString(R.string.chocolates),
                    getString(R.string.chocolates_content)
                ),
                GiggleData(
                    2,
                    getString(R.string.type_quote),
                    getString(R.string.rupaul),
                    getString(R.string.rupaul_quote)
                ),
                GiggleData(
                    3,
                    getString(R.string.type_quote),
                    getString(R.string.mimi),
                    getString(R.string.mimi_quote)
                ),
                GiggleData(
                    4,
                    getString(R.string.type_phrase),
                    getString(R.string.putanja),
                    getString(R.string.putanja_phrase)
                ),
                GiggleData(
                    5,
                    getString(R.string.type_phrase),
                    getString(R.string.gas),
                    getString(R.string.gas_phrase)
                ),
                GiggleData(
                    6,
                    getString(R.string.type_phrase),
                    getString(R.string.paris),
                    getString(R.string.paris_phrase)
                ),
                GiggleData(
                    7,
                    getString(R.string.type_link),
                    getString(R.string.dragon),
                    getString(R.string.dragon_link)
                ),
                GiggleData(
                    8,
                    getString(R.string.type_link),
                    getString(R.string.horoskopius),
                    getString(R.string.horoskopius_link)
                ),
                GiggleData(
                    9,
                    getString(R.string.type_link),
                    getString(R.string.rpn_hma),
                    getString(R.string.rpn_hma_link)
                ),
                GiggleData(
                    10,
                    getString(R.string.type_image),
                    getString(R.string.doggo),
                    getString(R.string.doggo_image)
                ),
                GiggleData(
                    11,
                    getString(R.string.type_image),
                    getString(R.string.trinity),
                    getString(R.string.trinity_image)
                ),
                GiggleData(
                    12,
                    getString(R.string.type_image),
                    getString(R.string.megan),
                    getString(R.string.megan_image)
                ),
            )
        }

        binding.button.setText(R.string.collection)
        binding.button1.setText(R.string.sos)
        binding.button2.setText(R.string.factory)

        binding.button.setOnClickListener {
            collectionButtonClick(it)
        }

        binding.button1.setOnClickListener {
            sosButtonClick(it)
        }
        binding.button2.setOnClickListener {
            factoryButtonClick(it)
        }
    }

    fun collectionButtonClick(view: View) {
        val collection = Intent(this, CollectionActivity::class.java)
        startActivity(collection)
    }

    fun sosButtonClick(view: View) {
        val sos = Intent(this, SosActivity::class.java)
        startActivity(sos)
    }

    fun factoryButtonClick(view: View) {
        val factory = Intent(this, FactoryActivity::class.java)
        startActivity(factory)
    }

}