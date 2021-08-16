package com.example.sampletestapp.dagger

import androidx.recyclerview.widget.RecyclerView
import com.example.sampletestapp.databinding.ItemCommitListBinding

class CommitViewHolder(private val itemCommitListBinding: ItemCommitListBinding): RecyclerView.ViewHolder(itemCommitListBinding.root) {
    init {
        itemCommitListBinding.executePendingBindings()
    }

    fun bind(itemViewModel: CommitItemViewModel) {
        itemCommitListBinding.viewmodel = itemViewModel
    }

}