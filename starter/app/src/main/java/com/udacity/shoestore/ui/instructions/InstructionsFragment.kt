package com.udacity.shoestore.ui.instructions

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
import com.udacity.shoestore.databinding.FragmentInstructionsBinding

class InstructionsFragment : Fragment() {

    private var _dataBinder: FragmentInstructionsBinding? = null
    private val dataBinder: FragmentInstructionsBinding
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
        _dataBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_instructions, container, false)
        dataBinder.instructionsFragment = this
        dataBinder.lifecycleOwner = this
        return dataBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.context = view.context
        _navController = findNavController()
    }

    fun introductionsClicked() {
        navController.navigate(R.id.action_instructionsFragment_to_homeFragment)
    }

    override fun onDestroy() {
        _dataBinder?.unbind()
        _dataBinder = null
        _navController = null
        super.onDestroy()
    }
}
