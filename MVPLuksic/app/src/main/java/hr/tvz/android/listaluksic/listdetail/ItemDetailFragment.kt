package hr.tvz.android.listaluksic.listdetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import hr.tvz.android.listaluksic.ListItem
import hr.tvz.android.listaluksic.MainActivity3
import hr.tvz.android.listaluksic.R
import hr.tvz.android.listaluksic.model.BazaPodataka
import hr.tvz.android.listaluksic.model.ToDo
import hr.tvz.android.listaluksic.model.ToDoDao

class ItemDetailFragment: Fragment() {
    val ARG_ITEM_ID = "item_id"

    var mItem: DummyItem? = null
    var selectedItem: ListItem? = null
    var ITEMS: MutableList<DummyItem> = ArrayList<DummyItem>()


    private var listOfItems: MutableList<ListItem> = ArrayList<ListItem>()
    var ITEM_MAP: MutableMap<String, DummyItem> = HashMap<String, DummyItem>()

    lateinit var todoDao: ToDoDao

    lateinit var db : BazaPodataka

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            requireContext(),
            BazaPodataka::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .build()
        todoDao = db.todoDao()

        if(todoDao.getAll().isEmpty()) {
            todoDao.insertAll(
                ToDo(
                    1,
                    getString(R.string.mackica),
                    getString(R.string.vocka),
                    R.drawable.ic_vocka_foreground
                ),
                ToDo(
                    2,
                    getString(R.string.bicikl),
                    getString(R.string.biciklopis),
                    R.drawable.ic_bicikl_foreground
                ),
                ToDo(
                    3,
                    getString(R.string.lak),
                    getString(R.string.lakopis),
                    R.drawable.ic_lak_foreground
                )
            )
        }

        createDummyContent()
        if (arguments?.containsKey(ARG_ITEM_ID) == true) {
            mItem = ITEM_MAP.get(requireArguments().getString(ARG_ITEM_ID))
        }
        /*if (mItem != null) {
            if (mItem!!.content == getString(R.string.mackica)) {
                selectedItem = ListItem(getString(R.string.mackica), getString(R.string.vocka), R.drawable.ic_vocka_foreground)
            } else if (mItem!!.content == getString(R.string.bicikl)) {
                selectedItem = ListItem(getString(R.string.bicikl), getString(R.string.biciklopis), R.drawable.ic_bicikl_foreground)
            } else {
                selectedItem = ListItem(getString(R.string.lak), getString(R.string.lakopis), R.drawable.ic_lak_foreground)
            }
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("selectedItem", selectedItem)
            startActivity(intent)
        }*/
}

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView: View = inflater.inflate(R.layout.activity_item, container, false)
            if (mItem != null) {
                if (mItem!!.content == getString(R.string.mackica)) {
                    (rootView.findViewById<View>(R.id.list_item_title_TV) as TextView).text =
                        getString(R.string.mackica)
                    (rootView.findViewById<View>(R.id.list_item_details_TV) as TextView).text =
                        getString(R.string.vocka)
                    (rootView.findViewById<View>(R.id.list_item_imageButton) as ImageButton).setImageResource(
                        R.drawable.ic_vocka_foreground
                    )
                } else if (mItem!!.content == getString(R.string.bicikl)) {
                    (rootView.findViewById<View>(R.id.list_item_title_TV) as TextView).text =
                        getString(R.string.bicikl)
                    (rootView.findViewById<View>(R.id.list_item_details_TV) as TextView).text =
                        getString(R.string.biciklopis)
                    (rootView.findViewById<View>(R.id.list_item_imageButton) as ImageButton).setImageResource(
                        R.drawable.ic_bicikl_foreground
                    )
                } else {
                    (rootView.findViewById<View>(R.id.list_item_title_TV) as TextView).text =
                        getString(R.string.lak)
                    (rootView.findViewById<View>(R.id.list_item_details_TV) as TextView).text =
                        getString(R.string.lakopis)
                    (rootView.findViewById<View>(R.id.list_item_imageButton) as ImageButton).setImageResource(
                        R.drawable.ic_lak_foreground
                    )
                }
            }
            val image = rootView.findViewById<ImageButton>(R.id.list_item_imageButton)
            image.setOnClickListener {
                callingPhoneNumber(rootView)
            }

            return rootView
        }

        fun createDummyContent() {

            val todoList: List<ToDo> = todoDao.getAll()
            for(todo in todoList){
                ITEMS.add(DummyItem(todo.id.toString(), todo.title))
            }

            for (item in ITEMS) {
                ITEM_MAP.put(item.id, item)
            }
        }

        class DummyItem internal constructor(var id: String, var content: String) {
            override fun toString(): String {
                return content.toString()
            }
        }

        fun callingPhoneNumber(view: android.view.View) {
        val intent = Intent(context, MainActivity3::class.java)
        startActivity(intent)
    }

}