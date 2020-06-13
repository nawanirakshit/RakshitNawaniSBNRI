package sbnri.rakshit.nawani.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * Created By: Rakshit Nawani
 * Date:  January 18 2020
 *
 * BaseRecyclerViewAdapter can be used as a base class for all the Recycler View's Adapter, it contains all the basic functions needed by the user, like
 * 1. Add Item
 * 2. Update Item
 * 3. Add List
 * 4. Remove Item
 * and many more according to needs.
 *
 */
abstract class BaseRecyclerViewAdapter<BD : ViewDataBinding, T>(
    @LayoutRes private var layoutId: Int, var mList: ArrayList<T> = ArrayList()
) :
    RecyclerView.Adapter<BaseRecyclerViewAdapter<BD, T>.BaseRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder =
        BaseRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )

    private var baseRecyclerViewItemListener: BaseRecyclerViewItemListener? = null

    fun addItem(t: T, position: Int? = null) {
        when (position) {
            null -> mList.add(t)
            else -> mList.add(position, t)
        }
        notifyItemInserted(position ?: mList.size - 1)
        baseRecyclerViewItemListener?.addItem(position ?: mList.size - 1)
    }

    open fun addItemAtBeginning(t: T) {
        mList.add(0, t)
        notifyItemInserted(0)
    }

    open fun getItem(position: Int): T = mList[position]

    fun findItem(predicate: (T) -> Boolean): T? = mList.find(predicate)

    fun itemPosition(t: T): Int = mList.indexOf(t)

    open fun removeItem(position: Int) {
        if (position < 0) return
        mList.removeAt(position)
        notifyItemRemoved(position)
        baseRecyclerViewItemListener?.removeItem(position)
    }

    fun updateItem(position: Int, t: T) {
        mList[position] = t
        notifyItemChanged(position)
        baseRecyclerViewItemListener?.changeItem(position)
    }

    fun addItems(t: List<T>) {
        var size = mList.size
        mList.addAll(t)
        var sizeNew = mList.size
//        notifyItemRangeChanged(size, sizeNew)
        notifyDataSetChanged()
        baseRecyclerViewItemListener?.addAllItems()
    }

    override fun getItemCount(): Int = mList.size

    fun getAllItems(): ArrayList<T> = mList

    open fun clear() {
        mList.clear()
        notifyDataSetChanged()
    }

    operator fun contains(item: T): Boolean = mList.contains(item)

    fun getPositionForItem(item: T) = when {
        mList.size > 0 -> mList.indexOf(item)
        else -> -1
    }

    fun moveItems(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    inner class BaseRecyclerViewHolder(var binding: BD) : RecyclerView.ViewHolder(binding.root)
}
