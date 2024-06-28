package com.paulus.project_uas_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulus.project_uas_anmp.R
import com.paulus.project_uas_anmp.databinding.FragmentNewsDetailBinding
import com.paulus.project_uas_anmp.databinding.FragmentNewsListBinding
import com.paulus.project_uas_anmp.viewmodel.NewsDetailViewModel
import com.paulus.project_uas_anmp.viewmodel.NewsListViewModel

class NewsDetailFragment : Fragment(), BackNewsListClick {
    private lateinit var binding: FragmentNewsDetailBinding
    private lateinit var viewModel: NewsDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsDetailViewModel::class.java)
        binding.backListener = this
    }

    override fun onBackNewsListClick(v: View) {
        Navigation.findNavController(v).popBackStack()
    }
}