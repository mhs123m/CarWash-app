package org.tuwaiq.carwash.views.userViews

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.tuwaiq.carwash.model.LoginModel
import org.tuwaiq.carwash.model.User
import org.tuwaiq.carwash.repository.UserRepository

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()

    var registerLiveData = MutableLiveData<User>()
    var loginLiveData = MutableLiveData<User>()
    // get live data of user from repo to viewmodel
    fun registerNewUser(user: User) {
        registerLiveData = userRepository.registerNewUser(user)
    }

    // get live data of user from repo to viewmodel
    fun userLogIn(loginModel: LoginModel){
       loginLiveData = userRepository.userLogIn(loginModel)
    }
}