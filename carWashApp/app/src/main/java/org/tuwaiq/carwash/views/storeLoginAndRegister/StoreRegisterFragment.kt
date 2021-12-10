package org.tuwaiq.carwash.views.storeLoginAndRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.userLoginAndRegister.UserLoginFragment

class StoreRegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_store_register, container, false)

        // find Sign in tv
        val tvSignIn = v.findViewById<TextView>(R.id.textViewStoreRegistSignIn)
        // on click get back to Login fragment
        tvSignIn.setOnClickListener {
            parentFragmentManager.commit {
                replace<StoreLogInFragment>(R.id.storeFrameLayout)
                // popBackStackImmediate for fragments is like finish() for activities
                parentFragmentManager.popBackStackImmediate()
            }
        }

        return v
    }
}