package org.tuwaiq.carwash.views.storeViews.storeMainActivity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.util.HelperFunctions
import org.tuwaiq.carwash.views.storeViews.storeInfoActivity.StoreInfoActivity
import org.tuwaiq.carwash.views.userViews.userLoginAndRegister.UserSignInActivity

class StoreMoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_store_more, container, false)

        //link views
        val testBtnToSetting = v.findViewById<Button>(R.id.buttonTakeMeToSettings)
        testBtnToSetting.setOnClickListener {
            startActivity(Intent(v.context,StoreInfoActivity::class.java))
        }

        val btnLogOut = v.findViewById<Button>(R.id.buttonStoreLogOut)
        btnLogOut.setOnClickListener {
            HelperFunctions.clearPref()
            val i = Intent(v.context, UserSignInActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
        }


        return v
    }
}