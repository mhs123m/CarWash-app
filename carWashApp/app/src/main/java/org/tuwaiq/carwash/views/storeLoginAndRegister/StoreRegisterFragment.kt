package org.tuwaiq.carwash.views.storeLoginAndRegister

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
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.util.HelperFunctions
import org.tuwaiq.carwash.views.userLoginAndRegister.UserLogInViewModel
import org.tuwaiq.carwash.views.userLoginAndRegister.UserLoginFragment
import org.tuwaiq.carwash.views.userLoginAndRegister.UserSignInActivity

class StoreRegisterFragment : Fragment() {
    lateinit var viewModel: StoreLogInViewModel
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
            )[StoreLogInViewModel::class.java]

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
            val store = Store(null,name,email,phone,password)
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
            viewModel.registerNewStore(store).observe(viewLifecycleOwner, {
                if (it) {
                    Log.d("USER_REGISTER_SUCCESS", "hell yeah")
                } else {
                    Log.d("USER_REGISTER_FAIL", "hell Noooo")
                }
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