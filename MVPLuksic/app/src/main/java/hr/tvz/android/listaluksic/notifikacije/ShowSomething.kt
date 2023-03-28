package hr.tvz.android.listaluksic.notifikacije

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.listaluksic.R

class ShowSomething : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_something)
        (findViewById<View>(R.id.nekiTekst) as TextView).text = intent.getStringExtra("string")
    }
}