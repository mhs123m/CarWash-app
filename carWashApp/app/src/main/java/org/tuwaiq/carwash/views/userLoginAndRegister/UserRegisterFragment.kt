package org.tuwaiq.carwash.views.userLoginAndRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.util.HelperFunctions

class UserRegisterFragment : Fragment() {
    lateinit var viewModel: UserLogInViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_register, container, false)
        // call viewModel
        viewModel = ViewModelProvider(this)[UserLogInViewModel::class.java]

        // link views
        val textInputFullName = v.
        findViewById<TextInputEditText>(R.id.textInputUserRegistFullName)
        val textInputEmail =v.
        findViewById<TextInputEditText>(R.id.textInputUserRegistEmail)
        val textInputPhone =v.
        findViewById<TextInputEditText>(R.id.textInputUserRegistPhone)
        val textInputPassword =v.
        findViewById<TextInputEditText>(R.id.textInputUserRegistPassword)
        val textInputCPassword =v.
        findViewById<TextInputEditText>(R.id.textInputUserRegistConfirmPassword)
        val registerUserBtn = v.findViewById<Button>(R.id.buttonUserRegister)
        val tvSignIn = v.findViewById<TextView>(R.id.textViewUserRegistSignIn)

        // get text from inputTexts
        val fullName = textInputFullName.text.toString()
        val email = textInputEmail.text.toString()
        val phone = textInputPhone.text.toString()
        val password = textInputPassword.text.toString()
        val cPassword = textInputCPassword.text.toString()

        // user to be registered
        val user = User(null,email,fullName,phone,password)

        // on click, register user
        registerUserBtn.setOnClickListener {

            if (fullName.isNotEmpty()){
                textInputFullName.error = "You must enter your full name"
                textInputFullName.requestFocus()
                return@setOnClickListener
            }
//            if (!HelperFunctions.isValidEmail(email)){
//                textInputEmail.error = "Invalid Email"
//                textInputEmail.requestFocus()
//                return@setOnClickListener
//            }
//            if (!HelperFunctions.isValidPhoneNumber(phone)){
//                textInputPhone.error = "Invalid Phone Number"
//                textInputPhone.requestFocus()
//                return@setOnClickListener
//            }
            if (password!= cPassword){
                textInputCPassword.error = "Password must match"
                textInputCPassword.requestFocus()
                return@setOnClickListener
            }
            viewModel.registerNewUser(user).observe(viewLifecycleOwner,{
                if (it) {
                    Toast.makeText(v.context, "hell yes", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(v.context, "oh no", Toast.LENGTH_SHORT).show()
                }
            })
        }
        // on click get back to sign in (if already having an account)
        tvSignIn.setOnClickListener {
            parentFragmentManager.commit {
                replace<UserLoginFragment>(R.id.mFrameLayout)
                // popBackStackImmediate for fragments is like finish() for activities
                parentFragmentManager.popBackStackImmediate()
            }
        }

        return v
    }
}