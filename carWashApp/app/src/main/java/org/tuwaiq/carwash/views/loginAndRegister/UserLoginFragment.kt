package org.tuwaiq.carwash.views.loginAndRegister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.*

import org.tuwaiq.carwash.R


class UserLoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_login, container, false)

        // find Sign Up textView
        var tvUserSignUp =
            v.findViewById<TextView>(R.id.textViewUserNoAccountSignUp)

        tvUserSignUp.setOnClickListener {

            // on click, set user registration fragment
            parentFragmentManager.commit {
                replace<UserRegisterFragment>(R.id.mFrameLayout)
                setReorderingAllowed(true)
                addToBackStack("userLogInFragment")
            }
        }
        return v
    }

}