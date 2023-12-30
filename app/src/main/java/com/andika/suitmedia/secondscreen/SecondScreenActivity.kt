package com.andika.suitmedia.secondscreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andika.suitmedia.R
import com.andika.suitmedia.databinding.ActivitySecondScreenBinding
import com.andika.suitmedia.databinding.NavbarBinding
import com.andika.suitmedia.thirdscreen.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private lateinit var bindingNavbar: NavbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        bindingNavbar = NavbarBinding.bind(binding.includeLayout.root)
        setContentView(binding.root)

        setSupportActionBar(bindingNavbar.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        bindingNavbar.titleText.text = "Second Screen"

        bindingNavbar.backButton.setOnClickListener{
            onBackPressedDispatcher.onBackPressed()
        }
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val inputData = sharedPreferences.getString("inputNama", "")
        val fullName = sharedPreferences.getString("fullName","Selected UserName")

        binding.textView3.text = fullName
        binding.name.text = inputData
        binding.button.setOnClickListener{
            val intent = Intent(this,ThirdScreenActivity::class.java)
            startActivity(intent)
        }
    }
}

