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
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import sbnri.rakshit.nawani.core.BindingActivity

/**
 * Created By: Rakshit Nawani
 * Date:  January 18 2020
 *
 * BindingFragment can be used as a base class for all the Fragments, it contains all the basic functions needed by the user
 * which will be connected with the BindingActivity
 *
 * In a Fragment extend it with BindingFragment<Data Binding Class of that class>
 * After extending Fragment we need to override methods which is getLayoutResId, implement it like below
 *
 * @LayoutRes
 * override fun getLayoutResId() = R.layout.your_layout_name_here
 */
abstract class BindingFragment<T : ViewDataBinding> : Fragment() {
    @LayoutRes
    abstract fun getLayoutResId(): Int

    private var loadingDialog: AlertDialog? = null

    val sharedPreferences: SharedPreferences by inject()
    val mContext: Context by inject()

    protected lateinit var binding: T private set

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as BindingActivity<*>).hideKeyboard()
        (activity as BindingActivity<*>).printLog(javaClass.simpleName)

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
    open fun showToast(
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
            (activity as BindingActivity<*>).hideLoading()
            (activity as BindingActivity<*>).showLoading(context)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyboard() {
        (activity as BindingActivity<*>).hideKeyboard()
    }


    /**
     * For hiding the loading
     */
    fun hideLoading() {
        (activity as BindingActivity<*>).hideLoading()
    }
}