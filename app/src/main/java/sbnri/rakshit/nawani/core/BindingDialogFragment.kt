package sbnri.rakshit.nawani.core

import android.content.Context
import android.content.SharedPreferences
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
import androidx.fragment.app.DialogFragment
import org.koin.android.ext.android.inject

abstract class BindingDialogFragment<T : ViewDataBinding> : DialogFragment() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    private var loadingDialog: AlertDialog? = null

    val sharedPreferences: SharedPreferences by inject()
    val mContext: Context by inject()

    protected lateinit var binding: T
        private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

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
        gravity: Int = Gravity.BOTTOM or Gravity.CENTER_VERTICAL,
        @DimenRes x: Int = 0,
        @DimenRes y: Int = 0,
        cancelPrevious: Boolean = false
    ) {
        if (cancelPrevious) toast?.cancel()

        // set custom view
        when {
            view != null -> {
                toast = Toast(context)
                toast?.view = view
            }
            else -> toast = Toast.makeText(context, message, duration)
        }
        // set duration
        toast?.duration = duration
        // set position
        var marginY = 0
        var marginX = 0
        if (y != 0) marginY = resources.getDimensionPixelSize(y)
        if (x != 0) marginX = resources.getDimensionPixelSize(x)
        if (marginX > 0 || marginY > 0) toast?.setGravity(gravity, marginX, marginY)
        // show toast
        toast?.show()
    }

    /**
     * For Displaying the loading when an API is hit
     */
    fun showLoading(context: Context) {

        (activity as BindingActivity<*>).showLoading(context)

//        try {
//            (activity as BindingActivity<*>).hideKeyboard()
//            if (loadingDialog == null) {
//                val factory: LayoutInflater = LayoutInflater.from(context)
//                val deleteDialogView: View = factory.inflate(R.layout.loading_screen, null)
//                loadingDialog =
//                    AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle).create()
//                loadingDialog!!.setView(deleteDialogView)
//                loadingDialog!!.setCanceledOnTouchOutside(false)
//                loadingDialog!!.setCancelable(false)
//            }
//
//            loadingDialog!!.show()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    /**
     * For hiding the loading
     */
    fun hideLoading() {
        try {
            (activity as BindingActivity<*>).hideKeyboard()
            (activity as BindingActivity<*>).hideLoading()
//            loadingDialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyboard() {
        (activity as BindingActivity<*>).hideKeyboard()
    }


}