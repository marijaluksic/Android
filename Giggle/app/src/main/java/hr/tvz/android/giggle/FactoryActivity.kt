package hr.tvz.android.giggle

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.giggle.databinding.ActivityFactoryBinding
import hr.tvz.android.giggle.databinding.ActivityMain2Binding

class FactoryActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<FactoryRVAdapter.GiggleFactoryViewHolder>? = null
    private var giggleTypes: MutableList<String> = ArrayList<String>()

    private lateinit var binding: ActivityFactoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFactoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val giggle1 = getString(R.string.type_image)
        val giggle2 = getString(R.string.type_phrase)
        val giggle3 = getString(R.string.type_link)
        val giggle4 = getString(R.string.type_quote)

        giggleTypes.add(giggle1)
        giggleTypes.add(giggle2)
        giggleTypes.add(giggle3)
        giggleTypes.add(giggle4)

        layoutManager = LinearLayoutManager(this)
        binding.factoryRV.layoutManager = layoutManager

        adapter = FactoryRVAdapter(this,  giggleTypes)
        binding.factoryRV.adapter = adapter
    }
}