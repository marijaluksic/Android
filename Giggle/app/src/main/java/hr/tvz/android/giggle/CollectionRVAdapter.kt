package hr.tvz.android.giggle

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

class CollectionRVAdapter
    (val context: Context,
    private val giggleList: MutableList<GiggleData>
    ): RecyclerView.Adapter<CollectionRVAdapter.CollectionViewHolder>() {

    lateinit var giggleDAO: GiggleDAO
    lateinit var giggleDb : GiggleDatabase

    class CollectionViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = CardLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false)
        return CollectionViewHolder(v)
    }

    override fun getItemCount(): Int {
        return giggleList.size
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val giggle = giggleList[position]
        holder.binding.CardViewTV.text = giggleList[position].title


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
                if(giggle.type == context.getString(R.string.type_image)) {
                    val intent = Intent(context, DisplayImageGiggle::class.java).apply {
                        putExtra("EXTRA_GIGGLE", giggle)
                    }

                    context.startActivity(intent)
                }
                else {
                    val intent = Intent(context, DisplayGiggle::class.java).apply {
                        putExtra("EXTRA_GIGGLE", giggle)
                    }

                    context.startActivity(intent)
                }

        }
    }
}