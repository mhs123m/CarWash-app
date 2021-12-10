package org.tuwaiq.carwash.views.userLoginAndRegister

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.*

import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.storeLoginAndRegister.StoreSignInActivity
import org.w3c.dom.Text


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
        // find "store?" click here
        val tvStoreClickHere = v.findViewById<TextView>(R.id.textViewCarWashClickHere)

        // on click, set user registration fragment
        tvUserSignUp.setOnClickListener {
            parentFragmentManager.commit {
                replace<UserRegisterFragment>(R.id.mFrameLayout)
                setReorderingAllowed(true)
                //keep login fragment in stack
                addToBackStack("userLogInFragment")
            }
        }

        //on click, intent to store login activity
        tvStoreClickHere.setOnClickListener {
            startActivity(Intent(v.context,StoreSignInActivity::class.java))
        }
        return v
    }

}