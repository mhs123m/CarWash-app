package org.tuwaiq.carwash.views.storeViews.storeLoginAndRegister

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.views.storeViews.StoreViewModel
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity
import org.tuwaiq.carwash.views.userViews.userLoginAndRegister.UserSignInActivity

class StoreLogInFragment : Fragment() {
    lateinit var viewModel: StoreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_store_log_in, container, false)


        // call viewModel
        viewModel =
            ViewModelProvider(
                requireActivity() as StoreSignInActivity
            )[StoreViewModel::class.java]

        // link views
        val textInputEmail = v.findViewById<TextInputEditText>(R.id.textInputEditTextStoreEmail)
        val textInputPassword =
            v.findViewById<TextInputEditText>(R.id.textInputEditTextStorePassword)
        val logInBtn = v.findViewById<Button>(R.id.buttonStoreLogIn)
        val tvApply = v.findViewById<TextView>(R.id.textViewStoreApply)
        val tvNotProviderClickHere = v.findViewById<TextView>(R.id.textViewStoreClickHere)
        val cpb = v.findViewById<CircularProgressBar>(R.id.circularProgressBarStore)

        // on click, (sign in store)
        logInBtn.setOnClickListener {
            cpb.visibility = View.VISIBLE
            // get email and password
            val email = textInputEmail.text.toString()
            val password = textInputPassword.text.toString()

            if (email.isEmpty()) {
                textInputEmail.error = "Please enter email"
                textInputEmail.requestFocus()
                cpb.visibility = View.GONE
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                textInputPassword.error = "Please enter password"
                textInputPassword.requestFocus()
                cpb.visibility = View.GONE
                return@setOnClickListener
            }

            viewModel.storeLogIn(LoginModel(email, password))

            viewModel.loginLiveData.observe(this, {
                if (it != null) {
                    Log.d("STORE_LOGIN", "success: $it")
                    // intent to user home page with current Store
                    val i = Intent(v.context, StoreMainActivity::class.java)

                    i.addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TOP
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    )
                    startActivity(i)
                } else {
                    Log.d("USER_LOGIN", "fail: $it")
                    // go to ur mom he he he
                }
                cpb.visibility = View.GONE
            })
        }


        // on click, (if no account) transaction to apply fragment
        tvApply.setOnClickListener {
            parentFragmentManager.commit {
                replace<StoreRegisterFragment>(R.id.storeFrameLayout)
                setReorderingAllowed(true)
                //keep login fragment in stack
                addToBackStack("StoreLogInFragment")
            }
        }

        //on click, (if not a car wash provider)
        // intent to log in as a user and finish() store and user activities
        tvNotProviderClickHere.setOnClickListener {
            val i = Intent(v.context, UserSignInActivity::class.java)

            i.addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
                        or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
            startActivity(i)

        }


        return v
    }
}