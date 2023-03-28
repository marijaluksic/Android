package hr.tvz.android.listaluksic

import android.app.NotificationChannel
import android.app.NotificationManager
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import hr.tvz.android.listaluksic.R.id
import hr.tvz.android.listaluksic.R.layout
import hr.tvz.android.listaluksic.broadcast.AplikacijskiReceiver
import hr.tvz.android.listaluksic.broadcast.SistemskiReceiver
import hr.tvz.android.listaluksic.listdetail.ItemDetailFragment
import hr.tvz.android.listaluksic.listdetail.ItemListFragment
import hr.tvz.android.listaluksic.viewmodel.MainActivityViewModel
import hr.tvz.android.listaluksic.widget.ItemWidget


class MainActivity : AppCompatActivity(), ItemListFragment.Callbacks {

    var mTwoPane = false

    // Kreiranje kanala za notifikacije
    var MOJ_KANAL = "mojKanal"
    private var data: ArrayList<ListItem> = ArrayList()

    private val viewModel : MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val music = MediaPlayer.create(this, R.raw.sound_effect)
        music.start()


        viewModel.getItemsFromAPI()
        viewModel.itemsFromApi.observe(this){

            itemsList.clear()
            itemsList.addAll(it)
        }

        if (findViewById<FrameLayout>(id.item_detail_container) != null) {
            mTwoPane = true
        } else {
            mTwoPane = false
            var itemListFragment = ItemListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(id.item_list_container, itemListFragment)
                .commit()
        }
        //startThread()

        registerReceiver(
            AplikacijskiReceiver(),
            IntentFilter("hr.tvz.android.listaluksic.broadcast.testbc")
        )

        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        val sistemskiReceiver = SistemskiReceiver()
        registerReceiver(sistemskiReceiver, intentFilter)


        // Kreiranje kanala za notifikacije
        // Provjera da li je API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MOJ_KANAL,
                "Ime kanala",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Opis kanala"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("Main activity", "getInstanceId failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Log.d("Main activity token:", token)
            Toast.makeText(this@MainActivity, token, Toast.LENGTH_SHORT).show()
        })

        val intent = Intent(this, ItemWidget::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
// Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
// since it seems the onUpdate() is only fired on that:
        val ids: IntArray = AppWidgetManager.getInstance(application)
            .getAppWidgetIds(ComponentName(application, ItemWidget::class.java))
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        sendBroadcast(intent)
    }

    override fun onItemSelected(id: String?) {
        var arguments = Bundle()
        arguments.putString(ItemDetailFragment().ARG_ITEM_ID, id)
        var detailFragment = ItemDetailFragment()
        detailFragment.arguments = arguments
        var fragmentTransaction = supportFragmentManager.beginTransaction()

        if (mTwoPane) {
            fragmentTransaction
                .replace(R.id.item_detail_container, detailFragment)
                .commit()
        } else {
            fragmentTransaction
                .addToBackStack(null)
                .replace(R.id.item_list_container, detailFragment)
                .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            id.menu_ostalo -> {
                val intent = Intent(baseContext, MainActivity2::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*private fun startThread() {
        val thread = Thread {


            val db = Room.databaseBuilder(
                applicationContext,
                BazaPodataka::class.java, "database-name"
            ).build()


            val todoDAO = db.todoDao()
            if (todoDAO.getAll().isEmpty()) {
                todoDAO.insertAll(
                    oDo(
                        1,
                        getString(R.string.mackica),
                        getString(R.string.vocka),
                        R.drawable.ic_vocka_foreground
                    ),
                    oDo(
                        2,
                        getString(R.string.bicikl),
                        getString(R.string.biciklopis),
                        R.drawable.ic_bicikl_foreground
                    ),
                    oDo(
                        3,
                        getString(R.string.lak),
                        getString(R.string.lakopis),
                        R.drawable.ic_lak_foreground
                    )
                )
            }


            val todoList: List<oDo> = todoDAO.getAll()
            for (odo in todoList) {
                data.add(
                    ListItem(odo.title, odo.detail, odo.image)
                )
            }



            if (!mTwoPane) {
                val itemListFragment = ItemListFragment(data)
                supportFragmentManager
                    .beginTransaction()
                    .add(id.item_list_container, itemListFragment)
                    .commit()
            } else {
                val itemListFragment = ItemListFragment(data)
                supportFragmentManager
                    .beginTransaction()
                    .add(id.item_detail_container, itemListFragment)
                    .commit()
            }
        }
        thread.start()
    }*/

    companion object{
        var itemsList : ArrayList<ItemDTO> = arrayListOf()
        var mTwoPane = true
    }
}
