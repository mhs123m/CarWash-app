package org.tuwaiq.carwash.util

import android.util.Patterns
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import org.tuwaiq.carwash.R

class HelperFunctions {


    fun isValidEmail (email: String): Boolean {
        return email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    interface IOnBackPressed {
        fun onBackPressed(): Boolean
    }
}