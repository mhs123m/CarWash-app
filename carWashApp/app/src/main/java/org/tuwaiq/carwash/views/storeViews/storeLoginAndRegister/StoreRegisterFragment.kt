package org.tuwaiq.carwash.views.storeViews.storeLoginAndRegister

import android.content.Context.MODE_PRIVATE
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
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.util.HelperFunctions
import org.tuwaiq.carwash.views.storeViews.StoreViewModel

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

        // on click, register store
        registerStoreBtn.setOnClickListener {
            // get text from inputTexts
            val name = textInputName.text.toString()
            val email = textInputEmail.text.toString()
            val phone = textInputPhone.text.toString()
            val password = textInputPassword.text.toString()
            val cPassword = textInputCPassword.text.toString()

            // store to be registered
            val store = Store(null,name,email,phone,password,null,null)
            if (name.isEmpty()) {
                textInputName.error = "Store Name is required"
                textInputName.requestFocus()
                return@setOnClickListener
            }
            if (!HelperFunctions.isValidEmail(email)) {
                textInputEmail.error = "Invalid Email"
                textInputEmail.requestFocus()
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
                return@setOnClickListener
            }
            viewModel.registerNewStore(store)
        }


        viewModel.registerLiveData.observe(this, {
            if (it != null) {
                Log.d("USER_REGISTER", "success: $it")
                // saving user in shared pref
                val pref = this.activity?.getSharedPreferences("store",MODE_PRIVATE)
                pref!!.edit().putString("sID",it._id).apply()
                pref.edit().putString("sName",it.name).apply()
                pref.edit().putString("sEmail",it.email).apply()
                pref.edit().putString("sPhone",it.phone).apply()
                // intent to user home page with current Store TODO

            } else {
                Log.d("USER_REGISTER", "fail: $it")
            }
        })

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