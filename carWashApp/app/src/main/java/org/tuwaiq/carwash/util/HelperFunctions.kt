package org.tuwaiq.carwash.util

import android.util.Patterns
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import org.tuwaiq.carwash.R

class HelperFunctions {


    companion object {

        fun isValidEmail(email: String): Boolean {
            return email.isNotEmpty()
//                    && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPhoneNumber(phone: String):Boolean {
            return phone.isNotEmpty()
//                    && phone.length>8 && Patterns.PHONE.matcher(phone).matches()
        }
    }
}