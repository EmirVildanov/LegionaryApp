package com.example.legionaryapp.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.legionaryapp.R
import java.security.InvalidParameterException

fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.Welcome -> {
            findNavController().navigate(R.id.welcome_fragment)
        }
        Screen.SignUp -> {
            findNavController().navigate(R.id.sign_up_fragment)
        }
        Screen.Main -> {
            findNavController().navigate(R.id.main_fragment)
        }
    }
}