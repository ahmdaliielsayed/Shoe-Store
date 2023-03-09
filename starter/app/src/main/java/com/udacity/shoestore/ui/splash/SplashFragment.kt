package com.udacity.shoestore.ui.splash

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.udacity.shoestore.Utils.AppConstants.SPLASH_TIME_OUT
import com.udacity.shoestore.Utils.AppSharedPref
import com.udacity.shoestore.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _dataBinder: FragmentSplashBinding? = null
    private val dataBinder: FragmentSplashBinding
        get() = _dataBinder!!

    private lateinit var context: Context

    private var _navController: NavController? = null
    private val navController: NavController
        get() = _navController!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _dataBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        return dataBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.context = view.context
        _navController = findNavController()

        Handler(
            Looper.getMainLooper()
        ).postDelayed({
            if (!AppSharedPref.getInstance(context, SETTING_FILE).getBooleanValue(IS_LOGIN, false)) {
                navController.navigate(R.id.action_splashFragment_to_loginFragment)
            } else if (!AppSharedPref.getInstance(context, SETTING_FILE).getBooleanValue(FIRST_TIME, false)) {
                navController.navigate(R.id.action_splashFragment_to_onboardFragment)
            } else {
                navController.navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, SPLASH_TIME_OUT)
    }
}
