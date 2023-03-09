package com.udacity.shoestore.ui.onboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.Utils.AppConstants.FIRST_TIME
import com.udacity.shoestore.Utils.AppConstants.SETTING_FILE
import com.udacity.shoestore.Utils.AppSharedPref
import com.udacity.shoestore.databinding.FragmentOnboardBinding

class OnboardFragment : Fragment() {

    private var _dataBinder: FragmentOnboardBinding? = null
    private val dataBinder: FragmentOnboardBinding
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
        _dataBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_onboard, container, false)
        dataBinder.onboardFragment = this
        dataBinder.lifecycleOwner = this
        return dataBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.context = view.context
        _navController = findNavController()
    }

    fun onBoardingClicked(){
        AppSharedPref.getInstance(context, SETTING_FILE).setValue(FIRST_TIME, true)
        navController.navigate(R.id.action_onboardFragment_to_instructionsFragment)
    }

    override fun onDestroy() {
        _dataBinder?.unbind()
        _dataBinder = null
        _navController = null
        super.onDestroy()
    }
}
