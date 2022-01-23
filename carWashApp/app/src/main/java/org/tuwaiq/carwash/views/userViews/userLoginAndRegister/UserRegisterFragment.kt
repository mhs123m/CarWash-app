package org.tuwaiq.carwash.views.userViews.userLoginAndRegister

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
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.utils.HelperFunctions
import org.tuwaiq.carwash.views.userViews.UserViewModel
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity

class UserRegisterFragment : Fragment() {
    lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_register, container, false)
        // call viewModel
        viewModel =
            ViewModelProvider(
                requireActivity() as UserSignInActivity
            )[UserViewModel::class.java]

        // link views
        val textInputFullName = v.findViewById<TextInputEditText>(R.id.textInputUserRegistFullName)
        val textInputEmail = v.findViewById<TextInputEditText>(R.id.textInputUserRegistEmail)
        val textInputPhone = v.findViewById<TextInputEditText>(R.id.textInputUserRegistPhone)
        val textInputPassword = v.findViewById<TextInputEditText>(R.id.textInputUserRegistPassword)
        val textInputCPassword =
            v.findViewById<TextInputEditText>(R.id.textInputUserRegistConfirmPassword)
        val registerUserBtn = v.findViewById<Button>(R.id.buttonRegisterUser)
        val tvSignIn = v.findViewById<TextView>(R.id.textViewUserRegistSignIn)
        val cpb = v.findViewById<CircularProgressBar>(R.id.circularProgressBar1)

        // on click, register user
        registerUserBtn.setOnClickListener {
            cpb.visibility = View.VISIBLE
            // get text from inputTexts
            val fullName = textInputFullName.text.toString()
            val email = textInputEmail.text.toString()
            val phone = textInputPhone.text.toString()
            val password = textInputPassword.text.toString()
            val cPassword = textInputCPassword.text.toString()

            // user to be registered
            val user = User(null, email, fullName, phone, password)
            if (fullName.isEmpty()) {
                textInputFullName.error = "You must enter your full name"
                textInputFullName.requestFocus()
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
            viewModel.registerNewUser(user)

            viewModel.registerLiveData.observe(this, {
            if (it!=null) {
                // intent to user home page with current user
                startActivity(Intent(v.context, UserMainActivity::class.java))
                activity?.finish()

                Log.d("USER_LOGIN","success fragment: $it")
            } else {
                Log.d("USER_REGISTER", "fail $it")
                // email or phone number might be already register.
            }
                cpb.visibility = View.GONE
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