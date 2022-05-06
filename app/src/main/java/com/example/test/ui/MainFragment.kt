package com.example.test.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.test.R
import com.example.test.data.Items
import com.example.test.databinding.MainFragmentBinding
import com.example.test.utils.ItemAdapter
import com.example.test.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var mainFragmentBinding: MainFragmentBinding
    private val mainViewModel:MainViewModel by viewModels()
    private lateinit var itemadapater:ItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        mainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,container,false)
        mainFragmentBinding.lifecycleOwner = this
        display()
        setHasOptionsMenu(true)
        return mainFragmentBinding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {
              mainViewModel.Items.observe(viewLifecycleOwner){
                  Collections.sort(it,Items.sortByName)
              }
                itemadapater.notifyDataSetChanged()
            }
        }
        return true
    }

    private fun display() {
        itemadapater = ItemAdapter()
        mainFragmentBinding.itemsRecyclerView.adapter = itemadapater
        mainViewModel.Items.observe(viewLifecycleOwner) {
            it.let {
                itemadapater.submitList(it)
            }
        }
    }


}