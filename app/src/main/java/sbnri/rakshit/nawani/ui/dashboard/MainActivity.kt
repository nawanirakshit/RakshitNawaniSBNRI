package sbnri.rakshit.nawani.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.no_data_available.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import sbnri.rakshit.nawani.R
import sbnri.rakshit.nawani.core.BindingActivity
import sbnri.rakshit.nawani.databinding.ActivityMainBinding
import sbnri.rakshit.nawani.utils.PaginationScrollListener

class MainActivity : BindingActivity<ActivityMainBinding>() {

    lateinit var viewModel: MainViewModel

    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = getViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initializeToolBar()

        viewModel.getData(page)

        attachListener()

        observeData()
    }

    private fun observeData() {
        viewModel.showLoading().observe(this, Observer {

            if (it) {
                isLoading = true
                showLoading(this)
            } else {
                isLoading = false
                hideLoading()
            }
        })

        viewModel.noMoreDataObserver().observe(this, Observer {
            if (it) {
                isLastPage = true
                showToast("No more data available")
            }
        })

        viewModel.noDataAvailableObserver().observe(this, Observer {
            if (it) {
                recycler_repositories.visibility = View.GONE
                card_no_data.visibility = View.VISIBLE
            } else {
                recycler_repositories.visibility = View.VISIBLE
                card_no_data.visibility = View.GONE
            }
        })

        viewModel.errorMessageObserver().observe(this, Observer {
            showToast(message = it)
        })

        btn_try_again.setOnClickListener {
            viewModel.getData(page)
        }

    }

    var page = 1

    private fun attachListener() {

        val recyclerView = recycler_repositories

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager

        recyclerView.addOnScrollListener(object : PaginationScrollListener(manager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {

                if (isConnectedToInternet()) {
                    viewModel.getData(++page)
                } else {
                    showToast(message = getString(R.string.no_internet))
                }

            }
        })

    }

    /**
     * Initialization of ToolBar
     */
    private fun initializeToolBar() {

        val tBar = t_bar
        setSupportActionBar(tBar)


        val collapsingToolbar = c_tb
        collapsingToolbar.isTitleEnabled = false;

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayShowHomeEnabled(false)
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setDisplayShowTitleEnabled(true);

            supportActionBar!!.title = getString(R.string.app_name)

        }
    }
}