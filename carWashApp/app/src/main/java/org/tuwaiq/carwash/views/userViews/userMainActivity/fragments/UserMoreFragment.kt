package org.tuwaiq.carwash.views.userViews.userMainActivity.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.util.HelperFunctions
import org.tuwaiq.carwash.views.userViews.userLoginAndRegister.UserSignInActivity

class UserMoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_more, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogOut = view.findViewById<Button>(R.id.buttonLogOutUser)
        btnLogOut.setOnClickListener {
            HelperFunctions.clearPref()
            val i = Intent(view.context, UserSignInActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
        }

    }

}