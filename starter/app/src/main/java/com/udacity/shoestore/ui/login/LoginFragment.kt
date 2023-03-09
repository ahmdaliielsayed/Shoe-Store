package com.udacity.shoestore.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.Utils.AppConstants.FIRST_TIME
import com.udacity.shoestore.Utils.AppConstants.IS_LOGIN
import com.udacity.shoestore.Utils.AppConstants.SETTING_FILE
import com.udacity.shoestore.Utils.AppSharedPref
import com.udacity.shoestore.Utils.ValidateInputs.validationAuthTextInputEditText
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _dataBinder: FragmentLoginBinding? = null
    private val dataBinder: FragmentLoginBinding
        get() = _dataBinder!!

    private lateinit var context: Context

    private lateinit var _navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _dataBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return dataBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.context = view.context
        dataBinder.loginFragment = this
        dataBinder.lifecycleOwner = this
        _navController = findNavController()
    }

    fun onLoginClicked() {
        dataBinder.apply {
            if (validationAuthTextInputEditText(Pair(1, emailEditTxt), Pair(2, passwordEditText))) {
                navigate()
            }
        }
    }

    fun onLoginWithAnExistingAccountClicked() {
        navigate()
    }

    private fun navigate() {
        AppSharedPref.getInstance(context, SETTING_FILE).setValue(IS_LOGIN, true)
        if (AppSharedPref.getInstance(context, SETTING_FILE).getBooleanValue(FIRST_TIME, false)) {
            _navController.navigate(R.id.action_loginFragment_to_homeFragment)
        } else {
            _navController.navigate(R.id.action_loginFragment_to_onboardFragment)
        }
    }

    override fun onDestroy() {
        _dataBinder?.unbind()
        _dataBinder = null
        super.onDestroy()
    }
}
