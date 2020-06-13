package sbnri.rakshit.nawani.core

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BindingBottomSheet<T : ViewDataBinding> : BottomSheetDialogFragment() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    private var loadingDialog: AlertDialog? = null

    protected lateinit var binding: T
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as BindingActivity<*>).hideKeyboard()

        setHasOptionsMenu(true)
        return DataBindingUtil.inflate<T>(inflater, getLayoutResId(), container, false)
            .apply { binding = this }.root
    }

    private var toast: Toast? = null
    /**
     * showToast with message
     *  @view: for custom toast provide view perform inflater and other task at your end
     *  @gravity: provide gravity
     */
    fun showToast(
        message: String?,
        view: View? = null,
        duration: Int = Toast.LENGTH_LONG,
        gravity: Int = Gravity.BOTTOM or Gravity.CENTER_VERTICAL, @DimenRes x: Int = 0, @DimenRes y: Int = 0,
        cancelPrevious: Boolean = false
    ) {
        (activity as BindingActivity<*>).showToast(
            message = message,
            view = view,
            duration = duration,
            gravity = gravity,
            cancelPrevious = cancelPrevious
        )
    }

    /**
     * For Displaying the loading when an API is hit
     */
    fun showLoading(context: Context) {

        try {
            (activity as BindingActivity<*>).hideKeyboard()
            (activity as BindingActivity<*>).hideLoading()
            (activity as BindingActivity<*>).showLoading(context)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * For hiding the loading
     */
    fun hideLoading() {
        (activity as BindingActivity<*>).hideKeyboard()
        (activity as BindingActivity<*>).hideLoading()
    }

    /**
     * For Hiding the keyboard
     */
    open fun hideKeyboard() {
        (activity as BindingActivity<*>).hideKeyboard()
    }

}