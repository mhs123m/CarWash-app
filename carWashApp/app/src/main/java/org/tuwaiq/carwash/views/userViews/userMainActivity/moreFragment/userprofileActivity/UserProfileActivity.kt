package org.tuwaiq.carwash.views.userViews.userMainActivity.moreFragment.userprofileActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputEditText
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.databinding.ActivityUserProfileBinding
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.utils.Globals
import org.tuwaiq.carwash.views.userViews.UserViewModel

class UserProfileActivity : AppCompatActivity() {
    private var userFromServer: User? = null
    private lateinit var binding: ActivityUserProfileBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var textInputUserName: TextInputEditText
    private lateinit var textInputUserEmail: TextInputEditText
    private lateinit var textInputUserPhone: TextInputEditText
    private lateinit var btnUpdate: Button
    private lateinit var backBtn: ImageView
    private lateinit var currentUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // linkViews
        linkViews()
        setViewsWithDataFromServer()

        backBtn.setOnClickListener {
            finish()
        }

        btnUpdate.setOnClickListener {
            updateInfo()
        }

    }



    private fun linkViews() {
        //link views
        textInputUserName = binding.textInputUserNameProfile
        textInputUserEmail = binding.textInputUserEmailProfile
        textInputUserPhone = binding.textInputPhoneProfile
        btnUpdate = binding.buttonUserUpdateInfo
        backBtn = binding.imageViewArrowBackUser
    }

    private fun setViewsWithDataFromServer() {
        viewModel.getUserById(Globals.sharedPreferences.getString("ID", null)!!)
        viewModel.oneUserLiveData.observe(this) { user ->
            userFromServer = user
            // set textInput with info of current logged in store
            textInputUserName.setText(userFromServer?.fullname)
            textInputUserEmail.setText(userFromServer?.email)
            textInputUserPhone.setText(userFromServer?.phone)
        }
    }
    private fun updateInfo() {
        // updated info
        currentUser = User(
            userFromServer?._id,
            textInputUserEmail.text.toString(),
            textInputUserName.text.toString(),
            textInputUserPhone.text.toString(),
            null,
        )
        viewModel.updatedUser(currentUser._id!!, currentUser)
        viewModel.updatedUserLiveData.observe(this) {
            Log.d("STORE_UPDATE", "btn pressed: $it")
            // set textInput with info of current logged in store
            textInputUserName.setText(it?.fullname)
            textInputUserEmail.setText(it?.email)
            textInputUserPhone.setText(it?.phone)
        }
    }
}
