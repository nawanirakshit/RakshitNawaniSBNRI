package sbnri.rakshit.nawani.ui.dashboard

import android.view.View
import sbnri.rakshit.nawani.R
import sbnri.rakshit.nawani.core.BaseRecyclerViewAdapter
import sbnri.rakshit.nawani.databinding.MainListItemBinding
import sbnri.rakshit.nawani.model.SbnriModel

class MainAdapter :
    BaseRecyclerViewAdapter<MainListItemBinding, SbnriModel>(R.layout.main_list_item) {

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder, position: Int) {

        val data = getItem(position)

        holder.binding.data = data
        holder.binding.root.setOnClickListener {
            listener?.itemClick(getItem(holder.adapterPosition))
        }

        var licence = "Licence NA"

        if (data.license != null) {
            licence = "${data.license!!.spdxId} (${data.license!!.name})"
        }
        var description = "NA"
        if (data.description != null) {
            description = data.description!!
        }

        holder.binding.tvDescription.text = description

        holder.binding.tvOpenIssuesCount.text = "Open Issues: ${data.openIssuesCount}"
        holder.binding.tvLicence.text = licence

        val permission = data.permissions

        if (permission != null) {

            if (permission.admin) {
                holder.binding.tvAdmin.visibility = View.VISIBLE
            } else holder.binding.tvAdmin.visibility = View.GONE

            if (permission.pull) {
                holder.binding.tvPull.visibility = View.VISIBLE
            } else holder.binding.tvPull.visibility = View.GONE

            if (permission.push) {
                holder.binding.tvPush.visibility = View.VISIBLE
            } else holder.binding.tvPush.visibility = View.GONE
        }
    }

    var listener: DataListListener? = null

    interface DataListListener {
        fun itemClick(data: SbnriModel)
    }
}