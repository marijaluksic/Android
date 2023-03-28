package hr.tvz.android.listaluksic.listdetail

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import androidx.fragment.app.viewModels
import androidx.room.Room
import hr.tvz.android.listaluksic.ListItem
import hr.tvz.android.listaluksic.MainActivity
import hr.tvz.android.listaluksic.R
import hr.tvz.android.listaluksic.databinding.ActivityMainBinding
import hr.tvz.android.listaluksic.model.BazaPodataka
import hr.tvz.android.listaluksic.model.ToDo
import hr.tvz.android.listaluksic.model.ToDoDao
import hr.tvz.android.listaluksic.viewmodel.MainActivityViewModel

//class ItemListFragment(data: ArrayList<ListItem>) : ListFragment() {

class ItemListFragment() : ListFragment() {

    private val STATE_ACTIVATED_POSITION = "activated_position"
    var mActivatedPosition = ListView.INVALID_POSITION

    var ITEMS: MutableList<DummyItem> = ArrayList<DummyItem>()

    var ITEM_MAP: MutableMap<String, DummyItem> = HashMap<String, DummyItem>()

    private var listOfItems: MutableList<ListItem> = ArrayList<ListItem>()
    private lateinit var binding: ActivityMainBinding

    lateinit var todoDao: ToDoDao

    lateinit var db : BazaPodataka


    private val viewModel : MainActivityViewModel by viewModels()

    interface Callbacks {
        fun onItemSelected(id: String?)
    }

    private val sDummyCallbacks: Callbacks =
        object : Callbacks {
            override fun onItemSelected(id: String?) {}
        }

    var mCallbacks: Callbacks = sDummyCallbacks


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

        listAdapter = ArrayAdapter(
            requireActivity(),
            hr.tvz.android.listaluksic.R.layout.card_layout,
            hr.tvz.android.listaluksic.R.id.item_title,
            ITEMS)

        viewModel.getItemsFromAPI()
        viewModel.itemsFromApi.observe(this){
            MainActivity.itemsList.clear()
            MainActivity.itemsList.addAll(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Restore the previously serialized activated item position
        if(savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION))
        }
    }

    private fun setActivatedPosition(position: Int) {
        if (position == ListView.INVALID_POSITION) {
            listView.setItemChecked(mActivatedPosition, false)
        } else {
            listView.setItemChecked(position, true)
        }

        mActivatedPosition = position
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Activities containing this fragment must implement its callbacks
        check(context is Callbacks) { "Activity must implement fragment's callbacks." }

        mCallbacks = context
    }

    override fun onDetach() {
        super.onDetach()
        mCallbacks = sDummyCallbacks
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

        mCallbacks.onItemSelected(ITEMS.get(position).id)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition)
        }
    }

    fun setActivateOnItemClick(activateOnItemClick: Boolean) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        listView.choiceMode =
            if (activateOnItemClick) ListView.CHOICE_MODE_SINGLE else ListView.CHOICE_MODE_NONE
    }
    fun createDummyContent() {
        val todoList: List<ToDo> = todoDao.getAll()
        todoList.asReversed()
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
}