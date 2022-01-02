package org.tuwaiq.carwash.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.util.Patterns
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import org.tuwaiq.carwash.R
import org.tuwaiq.carwash.model.Store
import org.tuwaiq.carwash.model.User
import retrofit2.Response

class HelperFunctions {


    companion object {

        fun isValidEmail(email: String): Boolean {
            return email.isNotEmpty()
//                    && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidPhoneNumber(phone: String):Boolean {
            return phone.isNotEmpty()
//                    && phone.length>8 && Patterns.PHONE.matcher(phone).matches()
        }

        fun <T> ifResponseNotNullPostValue(liveData: MutableLiveData<T>, response: Response<T>): MutableLiveData<T> {
            if (response.isSuccessful) {
                liveData.postValue(response.body())
                Log.d("$liveData", "success res.body: ${response.body()}")
            } else {
                Log.d("$liveData", "fail res.message: ${response.message()}")
            }
            return liveData
        }

        fun saveLoggedInStoreData (response: Response<Store>){
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

            if (!prefEdit.commit()){
                Log.d("prefEdit", "no edits committed for the store")
                return
            }
        }

        fun saveLoggedInUserData (response: Response<User>){
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

            if (!prefEdit.commit()){
                Log.d("prefEdit", "no edits committed for the store")
                return
            }
        }

        fun clearPref (){
            val prefClear = Globals.sharedPreferences.edit()
            prefClear.clear().apply()
        }

        fun decodePicFromApi(encodedString: String): Bitmap {

            val imageBytes = Base64.decode(encodedString, Base64.DEFAULT)

            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }

    }
}