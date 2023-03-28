package hr.tvz.android.kalkulator_luksic

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.graphics.toColor
import hr.tvz.android.kalkulator_luksic.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var locale: Locale

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val colorSelection = listOf(getString(R.string.golden), getString(R.string.silver))
        val arrayAdapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            colorSelection
        )
        binding.colorSpinner.adapter = arrayAdapter

        izgatajButtonClick(this)
        changeColor(this)
    }

        fun izgatajButtonClick(context: Context) {
            var result: String = ""
            binding.izgatajButton.setOnClickListener {
                var dan = binding.danET.text.toString().toInt()
                var mjesec = binding.mjesecET.text.toString().toInt()
                when (mjesec) {
                    1 -> {if(dan<20)
                    {
                        result = context.getString(R.string.jarac)
                    } else
                    {
                        result = context.getString(R.string.vodenjak)
                    }
                    }
                    2 -> {if(dan<19)
                    {
                        result = context.getString(R.string.vodenjak)
                    } else
                    {
                        result = context.getString(R.string.riba)
                    }
                    }
                    3 -> {if(dan<21)
                    {
                        result = context.getString(R.string.riba)
                    } else
                    {
                        result = context.getString(R.string.ovan)
                    }
                    }
                    4 -> {if(dan<20)
                    {
                        result = context.getString(R.string.ovan)
                    } else
                    {
                        result = context.getString(R.string.bik)
                    }
                    }
                    5 -> {if(dan<21)
                    {
                        result = context.getString(R.string.bik)
                    } else
                    {
                        result = context.getString(R.string.blizanac)
                    }
                    }
                    6 -> {if(dan<22)
                    {
                        result = context.getString(R.string.blizanac)
                    } else
                    {
                        result = context.getString(R.string.rak)
                    }
                    }
                    7 -> {if(dan<23)
                    {
                        result = context.getString(R.string.rak)
                    } else
                    {
                        result = context.getString(R.string.lav)
                    }
                    }
                    8 -> {if(dan<23)
                    {
                        result = context.getString(R.string.lav)
                    } else
                    {
                        result = context.getString(R.string.djevica)
                    }
                    }
                    9 -> {if(dan<23)
                    {
                        result = context.getString(R.string.djevica)
                    } else
                    {
                        result = context.getString(R.string.vaga)
                    }
                    }
                    10 -> {if(dan<24)
                    {
                        result = context.getString(R.string.vaga)
                    } else
                    {
                        result = context.getString(R.string.skorpion)
                    }
                    }
                    11 -> {if(dan<22)
                    {
                        result = context.getString(R.string.skorpion)
                    } else
                    {
                        result = context.getString(R.string.strijelac)
                    }
                    }
                    12 -> {if(dan<22)
                    {
                        result = context.getString(R.string.strijelac)
                    } else
                    {
                        result = context.getString(R.string.jarac)
                    }
                    }
                }

                binding.resultTV.text = result
            }

        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun changeColor(context: Context)
        {
            binding.colorButton.setOnClickListener(){
                if(binding.colorSpinner.selectedItem.toString() == (context.getString(R.string.silver))) {
                    binding.aplicationV.background = resources.getDrawable(R.color.pink)
                    binding.izgatajButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.silver)))
                    binding.colorButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.silver)))
                    binding.resultTV.background = resources.getDrawable(R.color.silver)
                    binding.resultTV.setTextColor(resources.getColor(R.color.granit))

                    binding.izgatajButton.setTextColor(resources.getColor(R.color.granit))
                    binding.colorButton.setTextColor(resources.getColor(R.color.granit))
                }
                else
                {
                    binding.aplicationV.background = resources.getDrawable(R.color.white)
                    binding.izgatajButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.gold)))
                    binding.colorButton.setBackgroundTintList(ColorStateList.valueOf(resources.getColor(R.color.gold)))
                    binding.resultTV.background = resources.getDrawable(R.color.banana)
                    binding.resultTV.setTextColor(resources.getColor(R.color.gold))

                    binding.izgatajButton.setTextColor(resources.getColor(R.color.white))
                    binding.colorButton.setTextColor(resources.getColor(R.color.white))
                }
            }
        }
    }
