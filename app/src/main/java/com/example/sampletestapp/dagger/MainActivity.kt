package com.example.sampletestapp.dagger

import android.icu.lang.UCharacter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.sampletestapp.R
import com.example.sampletestapp.databinding.ActivityMain3Binding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var bindinacg: ActivityMain3Binding
    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        val viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        val viewModelBinding: ActivityMain3Binding = DataBindingUtil.setContentView(this, R.layout.activity_main3)
        viewModelBinding.viewmodel = viewModel
        val dividerItemDecoration = DividerItemDecoration(this,1)
        viewModelBinding.commitList.addItemDecoration(dividerItemDecoration)
        //viewModelBinding.commitList.addItemDecoration()
        /*binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)*/
    }
}

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
    //val dividerItemDecoration = DividerItemDecoration(view.context,1)
    //view.addItemDecoration(dividerItemDecoration)
}