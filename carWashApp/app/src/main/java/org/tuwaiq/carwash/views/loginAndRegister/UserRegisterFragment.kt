package org.tuwaiq.carwash.views.loginAndRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import org.tuwaiq.carwash.R
class UserRegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_register, container, false)

        // find Sign in tv
        val tvSignIn = v.findViewById<TextView>(R.id.textViewUserRegistSignIn)
        // on click get back to sign in
        tvSignIn.setOnClickListener {
            parentFragmentManager.commit {
                replace<UserLoginFragment>(R.id.mFrameLayout)
                // popBackStackImmediate for fragments is like finish() for activities
                parentFragmentManager.popBackStackImmediate()

            }
        }

        return v
    }
}