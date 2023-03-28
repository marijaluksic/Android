package hr.tvz.android.giggle

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.giggle.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val WELCOME_TIMEOUT = 1500

    private val WELCOME_TIMEOUT2 = 2000

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.textView.text = getString(R.string.frown)
        binding.textView2.text = ""
        binding.imageButton.setImageResource(R.drawable.ic_frown_foreground)
        binding.imageButton.setOnClickListener {
            frownButtonClick(it)
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction((ConnectivityManager.CONNECTIVITY_ACTION))
        val systemReceiver = SystemReciever()
        registerReceiver(systemReceiver, intentFilter)

    }


    fun frownButtonClick(view: View) {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val animation2: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        val animation3: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        binding.textView2.setText(R.string.upside_down)
        binding.textView.startAnimation(animation2)
        binding.textView2.startAnimation(animation)
        binding.imageButton.startAnimation(animation3)

        Handler().postDelayed(Runnable {
            binding.textView.setText("")
            binding.imageButton.setImageResource(R.drawable.ic_happy_foreground)
            /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.imageButton.setBackgroundColor(getColor(R.color.launcherBackground))
            }*/

            Handler().postDelayed({
                val welcome = Intent(this, MainActivity2::class.java)
                startActivity(welcome)
                binding.textView.startAnimation(animation)
                binding.textView2.startAnimation(animation2)
                binding.textView.text = getString(R.string.frown)
                binding.textView2.text = ""
                binding.imageButton.setImageResource(R.drawable.ic_frown_foreground)
                binding.imageButton.startAnimation(animation)
            }, WELCOME_TIMEOUT2.toLong())
        }, WELCOME_TIMEOUT.toLong())

    }
}