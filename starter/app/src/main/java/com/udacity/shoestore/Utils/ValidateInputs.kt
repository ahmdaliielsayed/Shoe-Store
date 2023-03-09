package com.udacity.shoestore.Utils

import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.udacity.shoestore.R
import java.util.regex.Pattern

object ValidateInputs {

    fun Fragment.validationAuthTextInputEditText(vararg inputs: Pair<Int, TextInputEditText>): Boolean {
        var isValid = true
        for (input in inputs) {
            when (input.first) {
                1 -> { // email
                    when {
                        input.second.text.isNullOrEmpty() -> {
                            input.second.error = getString(R.string.error_empty_field)
                            input.second.requestFocus()
                            isValid = false
                        }
                        input.second.text.toString().trim().length < 3 -> {
                            input.second.error = getString(R.string.error_invalid_field_less_character)
                            input.second.requestFocus()
                            isValid = false
                        }
                    }
                }
                2 -> { // password
                    when {
                        input.second.text.isNullOrEmpty() -> {
                            input.second.error = getString(R.string.error_empty_field)
                            input.second.requestFocus()
                            isValid = false
                        }
                        input.second.text.toString().trim().length < 6 -> {
                            input.second.error = getString(R.string.error_invalid_field_less_character)
                            input.second.requestFocus()
                            isValid = false
                        }
                    }
                }
            }
        }

        return isValid
    }

    fun String.validName(): Boolean {
        var valid = true

        // Name should contain at least one number
        var exp = ".*\\d.*"
        var pattern = Pattern.compile(exp, Pattern.CASE_INSENSITIVE)
        var matcher = pattern.matcher(this)
        if (matcher.matches()) {
            valid = false
        }

        exp = ".*[~!@#$%^&*()_=+|/,.\"';:{}<>?].*"
        pattern = Pattern.compile(exp)
        matcher = pattern.matcher(this)
        if (matcher.matches()) {
            valid = false
        }
        return valid
    }

    fun String.isIntNumber(): Boolean = try {
        this.toInt()
        true
    } catch (n: NumberFormatException) {
        false
    } catch (e: Exception) {
        false
    }
}
