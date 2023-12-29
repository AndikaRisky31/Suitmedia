package com.andika.suitmedia.firstscreen
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.andika.suitmedia.databinding.ActivityFirstScreenBinding
import com.andika.suitmedia.secondscreen.SecondScreenActivity

class FirstScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding
    private lateinit var viewModel: FirstScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[FirstScreenViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.inputPalindrome.addTextChangedListener {
            viewModel.updateButtonCheckStatus(it?.toString())
        }

        binding.inputNama.addTextChangedListener {
            viewModel.updateButtonNextStatus(it?.toString())
        }

        binding.buttonCheck.setOnClickListener {
            val sentence = binding.inputPalindrome.text.toString()
            val isPalindrome = viewModel.isPalindrome(sentence)

            val message = if (isPalindrome) {
                "Palindrome"
            } else {
                "Not Palindrome"
            }

            showToast(message)
        }

        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, SecondScreenActivity::class.java)
            intent.putExtra("inputNama", binding.inputNama.text.toString())
            startActivity(intent)
        }

        viewModel.updateButtonCheckStatus(binding.inputPalindrome.text.toString())
        viewModel.updateButtonNextStatus(binding.inputNama.text.toString())
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
