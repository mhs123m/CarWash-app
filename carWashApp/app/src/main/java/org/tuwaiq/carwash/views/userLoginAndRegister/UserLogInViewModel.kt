package org.tuwaiq.carwash.views.userLoginAndRegister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.repository.UserRepository
import org.tuwaiq.carwash.util.HelperFunctions

class UserLogInViewModel: ViewModel() {

    val userRepository = UserRepository()

    fun registerNewUser(user: User): LiveData<Boolean>{
        val mLiveData = MutableLiveData<Boolean>()

        userRepository.registerNewUser(user).observeForever {
            if (HelperFunctions.isValidEmail(it.email)) {
                mLiveData.postValue(true)
            } else {
                mLiveData.postValue(false)
            }
        }

        return mLiveData
    }

}