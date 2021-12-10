package org.tuwaiq.carwash.views.storeLoginAndRegister

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.views.userLoginAndRegister.UserRegisterFragment
import org.tuwaiq.carwash.views.userLoginAndRegister.UserSignInActivity

class StoreLogInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_store_log_in, container, false)

        // find Apply textView
        val tvApply = v.findViewById<TextView>(R.id.textViewStoreApply)
        // find "user?" click here
        val tvNotProviderClickHere = v.findViewById<TextView>(R.id.textViewStoreClickHere)

        // on click, transaction to apply fragment
        tvApply.setOnClickListener {
            parentFragmentManager.commit {
                replace<StoreRegisterFragment>(R.id.storeFrameLayout)
                setReorderingAllowed(true)
                //keep login fragment in stack
                addToBackStack("StoreLogInFragment")
            }
        }

        //on click, intent to log in as a user and finish() store and user activities
        tvNotProviderClickHere.setOnClickListener {
            val i = Intent(v.context,UserSignInActivity::class.java)

            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)

        }


        return v
    }
}