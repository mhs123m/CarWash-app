package org.tuwaiq.carwash.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.model.User
import retrofit2.Response
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.tuwaiq.carwash.views.userViews.userLoginAndRegister.UserSignInActivity
import java.time.Duration


class HelperFunctions {


    companion object {

        fun isValidEmail(email: String): Boolean {
            return email.isNotEmpty()
                    && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPhoneNumber(phone: String): Boolean {
            return phone.isNotEmpty()
                    && phone.length > 8 && Patterns.PHONE.matcher(phone).matches()
        }

        fun <T> ifResponseNotNullPostValue(
            liveData: MutableLiveData<T>, response: Response<T>
        ): MutableLiveData<T> {
            if (response.isSuccessful) {
                liveData.postValue(response.body())
                Log.d("$liveData", "success res.body: ${response.body()}")
            } else {
                Log.d("$liveData", "fail res.message: ${response.message()}")
            }
            return liveData
        }

        fun saveLoggedInStoreData(response: Response<Store>) {
            val prefEdit = Globals.sharedPreferences.edit()
            val token = response.headers()["x-auth"]
            val id = response.body()!!._id
            val name = response.body()!!.name
            val phone = response.body()!!.phone
            val email = response.body()!!.email
            val logo = response.body()!!.logo

            prefEdit.putString("Token", token)
            prefEdit.putString("ID", id)
            prefEdit.putString("Name", name)
            prefEdit.putString("Phone", phone)
            prefEdit.putString("Email", email)
            prefEdit.putString("Logo", logo)
            prefEdit.putBoolean("IsStore", true)

            if (!prefEdit.commit()) {
                Log.d("prefEdit", "no edits committed for the store")
                return
            }
        }

        fun saveLoggedInUserData(response: Response<User>) {
            val prefEdit = Globals.sharedPreferences.edit()
            val token = response.headers().get("x-auth")
            val id = response.body()!!._id
            val name = response.body()!!.fullname
            val phone = response.body()!!.phone
            val email = response.body()!!.email

            prefEdit.putString("Token", token)
            prefEdit.putString("ID", id)
            prefEdit.putString("Name", name)
            prefEdit.putString("Phone", phone)
            prefEdit.putString("Email", email)
            prefEdit.putBoolean("IsUser", true)

            if (!prefEdit.commit()) {
                Log.d("prefEdit", "no edits committed for the store")
                return
            }
        }

        fun decodePicFromApi(encodedString: String): Bitmap {

            val imageBytes = Base64.decode(encodedString, Base64.DEFAULT)

            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }

        fun <T> checkEmptyState(
            imgEmptyState: ImageView,
            tvEmptyState: TextView,
            mRecyclerView: RecyclerView,
            displayedList: List<T>
            ){

            if (displayedList.isEmpty()){
                imgEmptyState.visibility = View.VISIBLE
                tvEmptyState.visibility = View.VISIBLE
                mRecyclerView.visibility = View.GONE
            } else{
                imgEmptyState.visibility = View.GONE
                tvEmptyState.visibility = View.GONE
                mRecyclerView.visibility = View.VISIBLE
            }
        }

    }
}