package com.example.sampletestapp.dagger.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletestapp.databinding.ItemCommitListBinding
import javax.inject.Inject

class CommitListAdapter @Inject constructor(): RecyclerView.Adapter<CommitViewHolder>() {
    private var itemViewModelsList = listOf<CommitItemViewModel>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CommitViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemCommitListBinding.inflate(layoutInflater, viewGroup, false)
        return CommitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommitViewHolder, position: Int) {
        val itemViewModel = itemViewModelsList[position]
        holder.bind(itemViewModel)
    }

    override fun getItemCount(): Int {
        return itemViewModelsList.size
    }

    fun setData(itemViewModelList: List<CommitItemViewModel>) {
        this.itemViewModelsList = itemViewModelList
        notifyDataSetChanged()
    }
}