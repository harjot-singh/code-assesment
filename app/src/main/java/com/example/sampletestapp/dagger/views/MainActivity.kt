package com.example.sampletestapp.dagger.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletestapp.R
import com.example.sampletestapp.dagger.di.App
import com.example.sampletestapp.databinding.ActivityScreenBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        val viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        val viewModelBinding: ActivityScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_screen)
        viewModelBinding.viewmodel = viewModel
        lifecycle.addObserver(viewModel)
        val dividerItemDecoration = DividerItemDecoration(this, 1)
        viewModelBinding.commitList.addItemDecoration(dividerItemDecoration)
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}