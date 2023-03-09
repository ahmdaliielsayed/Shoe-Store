package com.udacity.shoestore.ui.details.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.Utils.ValidateInputs.isIntNumber
import com.udacity.shoestore.Utils.ValidateInputs.validName
import com.udacity.shoestore.Utils.setBaseActivityFragmentsToolbar
import com.udacity.shoestore.databinding.FragmentDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.details.viewmodel.DetailsShoeViewModel
import com.udacity.shoestore.ui.home.viewmodel.HomeShoeViewModel
import kotlinx.android.synthetic.main.common_toolbar.view.*
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private var _dataBinder: FragmentDetailsBinding? = null
    private val dataBinder: FragmentDetailsBinding
        get() = _dataBinder!!

    private lateinit var context: Context

    private val activityViewModel by activityViewModels<HomeShoeViewModel>()
    private val mViewModel by viewModels<DetailsShoeViewModel>()

    private var _navController: NavController? = null
    private val navController: NavController
        get() = _navController!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _dataBinder = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        dataBinder.detailsFragment = this
        dataBinder.lifecycleOwner = this
        dataBinder.viewModel = mViewModel
        return dataBinder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.context = view.context

        detailsToolbar.apply { setBaseActivityFragmentsToolbar(getString(R.string.menu_txt_main_detail_screen), toolbar, tvNameToolbar) }
        _navController = findNavController()

        dataBinder.etName.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dataBinder.etName.error = null
            }

            override fun afterTextChanged(s: Editable?) { }
        })
        dataBinder.etCompany.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dataBinder.etCompany.error = null
            }

            override fun afterTextChanged(s: Editable?) { }
        })
        dataBinder.etSize.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dataBinder.etSize.error = null
            }

            override fun afterTextChanged(s: Editable?) { }
        })
        dataBinder.etDec.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                dataBinder.etDec.error = null
            }

            override fun afterTextChanged(s: Editable?) { }
        })
    }

    fun onCancelClicked() {
//        onDestroyView()
        navController.navigate(R.id.action_detailsFragment_to_homeFragment)
    }

    fun onSaveClicked() {
        if (validationInput()) {
            Toast.makeText(context, getString(R.string.saved), Toast.LENGTH_LONG).show()
//        onDestroyView()
            navController.navigate(R.id.action_detailsFragment_to_homeFragment)
        }
    }

    private fun validationInput(): Boolean {
        val name = mViewModel.shoeName.value ?: ""
        val company = mViewModel.shoeCompany.value ?: ""
        val size = mViewModel.shoeSize.value ?: ""
        val description = mViewModel.shoeDescription.value ?: ""

        val status = when {
            name.isEmpty() || company.isEmpty() || size.isEmpty() || description.isEmpty() -> {
                if (name.isEmpty()) dataBinder.etName.error = getString(R.string.name_required)
                if (company.isEmpty()) dataBinder.etCompany.error = getString(R.string.company_required)
                if (size.isEmpty()) dataBinder.etSize.error = getString(R.string.size_required)
                if (description.isEmpty()) dataBinder.etDec.error = getString(R.string.description_required)
                false
            }
            !name.validName() -> {
                dataBinder.etName.error = getString(R.string.name_invalid)
                false
            }

            !company.validName() -> {
                dataBinder.etCompany.error = getString(R.string.company_invalid)
                false
            }

            !size.isIntNumber() -> {
                dataBinder.etSize.error = getString(R.string.size_invalid)
                false
            }
            size.length > 2 -> {
                dataBinder.etSize.error = getString(R.string.size_invalid_length)
                false
            }

            description.length > 250 -> {
                dataBinder.etDec.error = getString(R.string.description_invalid)
                false
            }

            else -> {
                activityViewModel.addNewShoe(Shoe(name = name, company = company, size = size.toDouble(), description = description))
                true
            }
        }
        return status
    }

    override fun onDestroy() {
        _dataBinder?.unbind()
        _dataBinder = null
        _navController = null
        super.onDestroy()
    }
}
