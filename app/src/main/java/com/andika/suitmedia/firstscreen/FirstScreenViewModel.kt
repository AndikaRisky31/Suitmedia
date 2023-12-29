package com.andika.suitmedia.firstscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class FirstScreenViewModel : ViewModel() {

    val palindromeText = MutableLiveData<String>()
    val inputNamaText = MutableLiveData<String>()
    val buttonCheckEnabled: LiveData<Boolean> = palindromeText.map { it.trim().isNotEmpty() }
    val buttonNextEnabled: LiveData<Boolean> = inputNamaText.map { it.trim().isNotEmpty() }

    fun updateButtonCheckStatus(text: String?) {
        palindromeText.value = text.orEmpty()
    }

    fun updateButtonNextStatus(text: String?) {
        inputNamaText.value = text.orEmpty()
    }

    fun isPalindrome(sentence: String): Boolean {
        val cleanSentence = sentence.lowercase().replace("[^a-zA-Z]".toRegex(), "")
        val reversedSentence = cleanSentence.reversed()
        return cleanSentence == reversedSentence
    }
}
