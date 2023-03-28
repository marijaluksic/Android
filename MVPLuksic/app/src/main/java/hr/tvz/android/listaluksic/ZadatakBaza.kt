package hr.tvz.android.listaluksic

import android.R
import android.os.Bundle
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import hr.tvz.android.listaluksic.databinding.BazaBinding
import hr.tvz.android.listaluksic.model.BazaPodataka
import hr.tvz.android.listaluksic.model.ToDo
import hr.tvz.android.listaluksic.model.ToDoDao

class ZadatakBaza : AppCompatActivity() {

    lateinit var binding: BazaBinding
    lateinit var todoDao: ToDoDao

    lateinit var db : BazaPodataka

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BazaBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        db = Room.databaseBuilder(
            applicationContext,
            BazaPodataka::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .build()
        todoDao = db.todoDao()

        if(todoDao.getAll().isEmpty()) {
            todoDao.insertAll(
                ToDo(
                    1,
                    getString(hr.tvz.android.listaluksic.R.string.mackica),
                    getString(hr.tvz.android.listaluksic.R.string.vocka),
                    hr.tvz.android.listaluksic.R.drawable.ic_vocka_foreground
                ),
                ToDo(
                    2,
                    getString(hr.tvz.android.listaluksic.R.string.bicikl),
                    getString(hr.tvz.android.listaluksic.R.string.biciklopis),
                    hr.tvz.android.listaluksic.R.drawable.ic_bicikl_foreground
                ),
                ToDo(
                    3,
                    getString(hr.tvz.android.listaluksic.R.string.lak),
                    getString(hr.tvz.android.listaluksic.R.string.lakopis),
                    hr.tvz.android.listaluksic.R.drawable.ic_lak_foreground
                )
            )
        }

        ispisi()
    }

    private fun ispisi() {
        var toDos = todoDao.getAll()

        val list = ArrayList<HashMap<String, String?>>()
        var item: HashMap<String, String?>
        for (t in toDos) {
            item = HashMap()
            item["title"] = t.title
            item["detail"] = t.detail
            item["image"] = t.image.toString()
            list.add(item)
        }

        val adapter = SimpleAdapter(
            this, list,
            R.layout.two_line_list_item, arrayOf(
                "title",
                "detail"
            ), intArrayOf(
                R.id.text1,
                R.id.text2
            )
        )
        binding.list.adapter = adapter
    }

    fun obrisi(view: android.view.View) {
        var toDos = todoDao.getAll()
        for (t in toDos) {
            todoDao.delete(t)
        }
        ispisi()
    }
    fun unesi(view: android.view.View) {
        var todo = ToDo(
            null,
            binding.notica.text.toString(),
            binding.title.text.toString(),
            binding.notica2.text.toString().toInt()
        )

        todoDao.insertOne(todo)

        //Ukloni tekst
        binding.title.setText("")
        binding.notica.setText("")
        binding.notica2.setText("")
        ispisi()
    }
}