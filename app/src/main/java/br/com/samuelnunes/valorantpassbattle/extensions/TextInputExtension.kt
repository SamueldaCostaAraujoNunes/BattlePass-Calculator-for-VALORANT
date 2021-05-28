package br.com.samuelnunes.valorantpassbattle.extensions

import androidx.databinding.BindingAdapter
import br.com.samuelnunes.valorantpassbattle.util.ViewModelString
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun TextInputLayout.error(viewModelString: ViewModelString?) {
    if (viewModelString == null) {
        isErrorEnabled = false
        this.error = null
    } else {
        isErrorEnabled = true
        this.error = viewModelString.resolve(context)
    }
}

