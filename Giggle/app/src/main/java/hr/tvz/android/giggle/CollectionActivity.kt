package hr.tvz.android.giggle

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import hr.tvz.android.giggle.databinding.ActivityCollectionBinding


class CollectionActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<CollectionRVAdapter.CollectionViewHolder>? = null
    private var giggleList: MutableList<GiggleData> = ArrayList<GiggleData>()
    lateinit var giggleDAO: GiggleDAO
    lateinit var giggleDb : GiggleDatabase

    private lateinit var binding: ActivityCollectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCollectionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val giggleSelection = listOf(getString(R.string.type_image), getString(R.string.type_phrase), getString(R.string.type_link), getString(R.string.type_quote))
        val arrayAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            giggleSelection
        )
        binding.CollectionSpinner.adapter = arrayAdapter

        giggleDb = Room.databaseBuilder(
            applicationContext,
            GiggleDatabase::class.java, "GiggleDatabase"
        )
            .allowMainThreadQueries()
            .build()
        giggleDAO = giggleDb.getDao()
        giggleList = giggleDAO.getAll()

        layoutManager = LinearLayoutManager(this)
        binding.collectionRV.layoutManager = layoutManager

        adapter = CollectionRVAdapter(this,  giggleList)
        binding.collectionRV.adapter = adapter


        binding.CollectionButton.setOnClickListener{
            if(binding.CollectionSpinner.selectedItem.toString().lowercase() == (getString(R.string.type_image)).lowercase()) {
                giggleDb = Room.databaseBuilder(
                    applicationContext,
                    GiggleDatabase::class.java, "GiggleDatabase"
                )
                    .allowMainThreadQueries()
                    .build()
                giggleDAO = giggleDb.getDao()
                val filteredList = giggleDAO.getAll().filter {it.type.lowercase() == getString(R.string.type_image).lowercase()}

                giggleList = filteredList as MutableList<GiggleData>

                layoutManager = LinearLayoutManager(this)
                binding.collectionRV.layoutManager = layoutManager

                adapter = CollectionRVAdapter(this,  giggleList)
                binding.collectionRV.adapter = adapter
            }
            if(binding.CollectionSpinner.selectedItem.toString().lowercase() == (getString(R.string.type_phrase)).lowercase()) {
                giggleDb = Room.databaseBuilder(
                    applicationContext,
                    GiggleDatabase::class.java, "GiggleDatabase"
                )
                    .allowMainThreadQueries()
                    .build()
                giggleDAO = giggleDb.getDao()
                val filteredList = giggleDAO.getAll().filter {it.type.lowercase() == getString(R.string.type_phrase).lowercase()}

                giggleList = filteredList as MutableList<GiggleData>

                layoutManager = LinearLayoutManager(this)
                binding.collectionRV.layoutManager = layoutManager

                adapter = CollectionRVAdapter(this,  giggleList)
                binding.collectionRV.adapter = adapter
            }
            if(binding.CollectionSpinner.selectedItem.toString().lowercase() == (getString(R.string.type_quote)).lowercase()) {
                giggleDb = Room.databaseBuilder(
                    applicationContext,
                    GiggleDatabase::class.java, "GiggleDatabase"
                )
                    .allowMainThreadQueries()
                    .build()
                giggleDAO = giggleDb.getDao()
                val filteredList = giggleDAO.getAll().filter {it.type.lowercase() == getString(R.string.type_quote).lowercase()}

                giggleList = filteredList as MutableList<GiggleData>

                layoutManager = LinearLayoutManager(this)
                binding.collectionRV.layoutManager = layoutManager

                adapter = CollectionRVAdapter(this,  giggleList)
                binding.collectionRV.adapter = adapter
            }
            if(binding.CollectionSpinner.selectedItem.toString().lowercase() == (getString(R.string.type_link)).lowercase()) {
                giggleDb = Room.databaseBuilder(
                    applicationContext,
                    GiggleDatabase::class.java, "GiggleDatabase"
                )
                    .allowMainThreadQueries()
                    .build()
                giggleDAO = giggleDb.getDao()
                val filteredList = giggleDAO.getAll().filter {it.type.lowercase() == getString(R.string.type_link).lowercase()}

                giggleList = filteredList as MutableList<GiggleData>

                layoutManager = LinearLayoutManager(this)
                binding.collectionRV.layoutManager = layoutManager

                adapter = CollectionRVAdapter(this,  giggleList)
                binding.collectionRV.adapter = adapter
            }
        }
    }
}