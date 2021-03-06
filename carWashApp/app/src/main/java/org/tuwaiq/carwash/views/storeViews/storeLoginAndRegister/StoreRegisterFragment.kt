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
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.utils.HelperFunctions
import org.tuwaiq.carwash.views.storeViews.StoreViewModel
import org.tuwaiq.carwash.views.storeViews.storeMainActivity.StoreMainActivity

class StoreRegisterFragment : Fragment() {
    lateinit var viewModel: StoreViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_store_register, container, false)

        // call viewModel
        viewModel =
            ViewModelProvider(
                requireActivity() as StoreSignInActivity
            )[StoreViewModel::class.java]

        // link views
        val textInputName = v.findViewById<TextInputEditText>(R.id.textInputRegistStoreName)
        val textInputEmail = v.findViewById<TextInputEditText>(R.id.textInputRegistStoreEmail)
        val textInputPhone = v.findViewById<TextInputEditText>(R.id.textInputStoreRegistPhone)
        val textInputPassword = v.findViewById<TextInputEditText>(R.id.textInputStoreRegistPassword)
        val textInputCPassword =
            v.findViewById<TextInputEditText>(R.id.textInputStoreRegistConfirmPassword)
        val registerStoreBtn = v.findViewById<Button>(R.id.buttonStoreApply)
        val tvSignIn = v.findViewById<TextView>(R.id.textViewStoreRegistSignIn)
        val cpb = v.findViewById<CircularProgressBar>(R.id.circularProgressBarStore2)

        // on click, register store
        registerStoreBtn.setOnClickListener {
            cpb.visibility = View.VISIBLE
            // get text from inputTexts
            val name = textInputName.text.toString()
            val email = textInputEmail.text.toString()
            val phone = textInputPhone.text.toString()
            val password = textInputPassword.text.toString()
            val cPassword = textInputCPassword.text.toString()

            // store to be registered
            val store = Store(null, name, email, phone, password, null, null)
            if (name.isEmpty()) {
                textInputName.error = "Store Name is required"
                textInputName.requestFocus()
                cpb.visibility = View.GONE
                return@setOnClickListener
            }
            if (!HelperFunctions.isValidEmail(email)) {
                textInputEmail.error = "Invalid Email"
                textInputEmail.requestFocus()
                cpb.visibility = View.GONE
                return@setOnClickListener
            }
            if (!HelperFunctions.isValidPhoneNumber(phone)) {
                textInputPhone.error = "Invalid Phone Number"
                textInputPhone.requestFocus()
                return@setOnClickListener
            }
            if (password != cPassword) {
                textInputCPassword.error = "Password must match"
                textInputCPassword.requestFocus()
                cpb.visibility = View.GONE
                return@setOnClickListener
            }
            viewModel.registerNewStore(store)

            viewModel.registerLiveData.observe(this, {
                if (it != null) {
                    Log.d("USER_REGISTER", "success: $it")
                    // intent to user home page with current Store
                    val i = Intent(v.context, StoreMainActivity::class.java)
                    i.addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                                or Intent.FLAG_ACTIVITY_CLEAR_TOP
                                or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    )
                    startActivity(i)

                } else {
                    Log.d("USER_REGISTER", "fail: $it")
                }
                cpb.visibility = View.GONE
            })
        }


        // on click get back to Login fragment (if already has an account)
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