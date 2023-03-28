package hr.tvz.android.listaluksic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RVAdapter
    (public val listOfItems: MutableList<ListItem>): RecyclerView.Adapter<RVAdapter.ViewHolder>() {

    private lateinit var mListener:onItemCLickListener

    interface onItemCLickListener{
        fun onItemClick(position:Int)
    }

    fun setOnItemClickListener(listener:onItemCLickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v, mListener)
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

    override fun onBindViewHolder(holder: RVAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = listOfItems[position].title
    }

    inner class ViewHolder(itemView: View, listener: onItemCLickListener): RecyclerView.ViewHolder(itemView){
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.item_title)

            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)

            }
        }
    }
}