package com.udacity.shoestore.ui.home.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.Utils.AppConstants.IS_LOGIN
import com.udacity.shoestore.Utils.AppConstants.SETTING_FILE
import com.udacity.shoestore.Utils.AppSharedPref
import com.udacity.shoestore.Utils.setBaseActivityFragmentsToolbar
import com.udacity.shoestore.databinding.FragmentHomeBinding
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.ui.home.viewmodel.HomeShoeViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private var _dataBinder: FragmentHomeBinding? = null
    private val dataBinder: FragmentHomeBinding
        get() = _dataBinder!!

    private lateinit var context: Context

    private var _navController: NavController? = null
    private val navController: NavController
        get() = _navController!!

    private val mViewModel by activityViewModels<HomeShoeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _dataBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        dataBinder.homeFragment = this
        dataBinder.lifecycleOwner = this
        return dataBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.context = view.context
        _navController = findNavController()

        homeToolbar.apply { setBaseActivityFragmentsToolbar(getString(R.string.menu_txt_main_screen), toolbar, tvNameToolbar) }
        mViewModel.shoesLiveData.observe(viewLifecycleOwner) {shoes ->
            for (item in shoes) {
                val itemShoeBinding = DataBindingUtil.inflate(
                    LayoutInflater.from(getContext()),
                    R.layout.item_shoe,
                    null,
                    false
                ) as ItemShoeBinding
                itemShoeBinding.shoeItem = item
                dataBinder.listShoes.addView(itemShoeBinding.root)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.header_menu_dashboard, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuLogOut -> requestLogout()
        }
        return true
    }

    private fun requestLogout() {
        AppSharedPref.getInstance(context, SETTING_FILE).setValue(IS_LOGIN, false)
        navController.navigate(R.id.action_homeFragment_to_loginFragment)
    }

    fun onAddShoeClick() = navController.navigate(R.id.action_homeFragment_to_detailsFragment)

    override fun onDestroy() {
        _dataBinder?.unbind()
        _dataBinder = null
        _navController = null
        super.onDestroy()
    }
}
