package hr.tvz.android.listaluksic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.listaluksic.boundservice.BoundServiceActivity
import hr.tvz.android.listaluksic.databinding.OstatakBinding
import hr.tvz.android.listaluksic.notifikacije.OsnovnaNotifikacija

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: OstatakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OstatakBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonBaza.setOnClickListener {
            val intent = Intent(baseContext, ZadatakBaza::class.java)
            startActivity(intent)
        }
        binding.buttonNotifikacija.setOnClickListener {
            val intent = Intent(baseContext, OsnovnaNotifikacija::class.java)
            startActivity(intent)
        }
        binding.buttonServis.setOnClickListener {
            val intent = Intent(baseContext, BoundServiceActivity::class.java)
            startActivity(intent)
        }
    }

}