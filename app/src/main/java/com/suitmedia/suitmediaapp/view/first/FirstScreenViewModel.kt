package com.suitmedia.suitmediaapp.view.first

import androidx.lifecycle.ViewModel

class FirstScreenViewModel : ViewModel() {
    var isPalindrome = false

    fun checkPalindrome(input: String) {
        val cleanInput = input.replace("\\s+".toRegex(), "").lowercase()
        isPalindrome = cleanInput == cleanInput.reversed()
    }
}