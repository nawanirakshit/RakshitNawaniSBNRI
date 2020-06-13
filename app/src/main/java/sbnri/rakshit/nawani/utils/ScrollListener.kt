package sbnri.rakshit.nawani.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Pagination class to add more items to the list when reach the last item.
 */
abstract class PaginationScrollListener
/**
 * Supporting only LinearLayoutManager for now.
 *
 * @param layoutManager
 */
    (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()


        if (!isLoading() && !isLastPage()) {

            println("visibleItemCount >>> $visibleItemCount ")
            println("totalItemCount >>> $totalItemCount ")
            println("firstVisibleItemPosition >>> $firstVisibleItemPosition ")

            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                println("LOAD MORE >>>")
                loadMoreItems()
            } else {
                println("Load None >>>")
            }
        }
    }

    abstract fun loadMoreItems()
}