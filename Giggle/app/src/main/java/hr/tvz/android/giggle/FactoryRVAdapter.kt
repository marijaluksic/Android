package hr.tvz.android.giggle

import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import hr.tvz.android.giggle.databinding.CardLayoutBinding

class FactoryRVAdapter(
    private val context: Context,
    private val giggleTypes: MutableList<String>
    ): RecyclerView.Adapter<FactoryRVAdapter.GiggleFactoryViewHolder>() {

    lateinit var giggleDAO: GiggleDAO
    lateinit var giggleDb : GiggleDatabase

    class GiggleFactoryViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = CardLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiggleFactoryViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false)
        return GiggleFactoryViewHolder(v)
    }

    override fun getItemCount(): Int {
        return giggleTypes.size
    }

    override fun onBindViewHolder(holder: GiggleFactoryViewHolder, position: Int) {
        val giggle = giggleTypes[position]
        holder.binding.CardViewTV.text = giggleTypes[position]


        holder.binding.root.setOnClickListener {
            /*if(giggle.lowercase() == "image") {
                if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    val intent = Intent(context, AddNewImageGiggle::class.java).apply {
                        putExtra("EXTRA_GIGGLE", giggleTypes[position])
                    }
                    context.startActivity(intent)
                } else {
                }
            }
            else*/
                if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    val intent = Intent(context, AddNewGiggle::class.java).apply {
                        putExtra("EXTRA_GIGGLE", giggleTypes[position])
                    }
                    context.startActivity(intent)
                } else {
                    val textView : TextView = (context as AppCompatActivity).findViewById(R.id.AddNewGiggleTV)
                    textView.text = context.getString(R.string.title_input)
                    val textView1 : TextView = (context as AppCompatActivity).findViewById(R.id.AddNewGiggleTV1)
                    if(giggle == context.getString(R.string.type_phrase)) {
                        textView1.text = context.getString(R.string.phrase_input)
                    }
                    else if(giggle == context.getString(R.string.type_quote)) {
                        textView1.text = context.getString(R.string.quote_input)
                    }
                    else {
                        textView1.text = context.getString(R.string.link_input)
                    }
                    val button : Button = (context as AppCompatActivity).findViewById(R.id.AddNewGiggleButton)
                    val title : EditText = (context as AppCompatActivity).findViewById(R.id.AddNewGiggleET)
                    val content : EditText = (context as AppCompatActivity).findViewById(R.id.AddNewGiggleET1)
                    button.setOnClickListener{

                        var newGiggle = GiggleData(
                            null,
                            giggle,
                            title.text.toString(),
                            content.text.toString()
                        )
                        giggleDb = Room.databaseBuilder(
                            (context as AppCompatActivity),
                            GiggleDatabase::class.java, "GiggleDatabase"
                        )
                            .allowMainThreadQueries()
                            .build()
                        giggleDAO = giggleDb.getDao()
                        giggleDAO.insertOne(newGiggle)

                        title.setText("")
                        content.setText("")
                        val giggleAdded = Intent(context, MainActivity2::class.java)
                        context.startActivity(giggleAdded)
                    }
                }

        }
    }
}