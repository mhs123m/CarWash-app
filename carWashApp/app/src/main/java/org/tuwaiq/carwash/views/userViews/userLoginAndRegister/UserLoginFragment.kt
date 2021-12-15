package org.tuwaiq.carwash.views.userViews.userLoginAndRegister

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText

import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.views.storeViews.storeLoginAndRegister.StoreSignInActivity
import org.tuwaiq.carwash.views.userViews.UserViewModel
import org.tuwaiq.carwash.views.userViews.userMainActivity.UserMainActivity


class UserLoginFragment : Fragment() {
    lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_login, container, false)
        // call viewModel
        viewModel =
            ViewModelProvider(
                requireActivity() as UserSignInActivity
            )[UserViewModel::class.java]

        // link views
        val textInputEmail = v.findViewById<TextInputEditText>(R.id.textInputEditTextUserEmail)
        val textInputPassword =
            v.findViewById<TextInputEditText>(R.id.textInputEditTextUserPassword)
        val logInBtn = v.findViewById<Button>(R.id.buttonUserLogIn)
        val tvUserSignUp =
            v.findViewById<TextView>(R.id.textViewUserNoAccountSignUp)
        val tvStoreClickHere = v.findViewById<TextView>(R.id.textViewCarWashClickHere)

        // on click, (sign in user)
        logInBtn.setOnClickListener {
            // get email and password
            val email = textInputEmail.text.toString()
            val password = textInputPassword.text.toString()

            if (email.isEmpty()){
                textInputEmail.error = "Please enter email"
                textInputEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                textInputPassword.error = "Please enter password"
                textInputPassword.requestFocus()
                return@setOnClickListener
            }

            viewModel.userLogIn(LoginModel(email,password))

            viewModel.loginLiveData.observe(this,{
                if (it != null){
                    // save current user to shared pref
                    val pref = this.activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
                    pref!!.edit().putString("uID",it._id).apply()
                    pref.edit().putString("uName",it.fullname).apply()
                    pref.edit().putString("uEmail",it.email).apply()
                    pref.edit().putString("uPhone",it.phone).apply()
                    // intent to user home page with current user
                    startActivity(Intent(v.context,UserMainActivity::class.java))
                    activity?.finish()

                    Log.d("USER_LOGIN","success: $it")
                } else {
                    Log.d("USER_LOGIN","fail: $it")
                    // go to ur mom he he he
                }
            })
        }




        // on click, (no account) set user registration fragment
        tvUserSignUp.setOnClickListener {
            parentFragmentManager.commit {
                replace<UserRegisterFragment>(R.id.mFrameLayout)
                setReorderingAllowed(true)
                //keep login fragment in stack
                addToBackStack("userLogInFragment")
            }
        }

        //on click, (if a car wash provider) intent to store login activity
        tvStoreClickHere.setOnClickListener {
            startActivity(Intent(v.context, StoreSignInActivity::class.java))
        }
        return v
    }

}