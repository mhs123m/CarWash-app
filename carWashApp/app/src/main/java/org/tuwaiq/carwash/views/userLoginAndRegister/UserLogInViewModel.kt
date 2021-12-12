package org.tuwaiq.carwash.views.userLoginAndRegister

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.repository.UserRepository

class UserLogInViewModel : ViewModel() {

    val userRepository = UserRepository()

    fun registerNewUser(user: User): LiveData<Boolean> {
        val mLiveData = MutableLiveData<Boolean>()

        userRepository
            .registerNewUser(user)
            .observeForever {
                    mLiveData.postValue(true)
            }

        return mLiveData
    }

}