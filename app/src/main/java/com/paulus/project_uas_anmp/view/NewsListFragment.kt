package com.paulus.project_uas_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.paulus.project_uas_anmp.R
import com.paulus.project_uas_anmp.databinding.FragmentNewsListBinding
import com.paulus.project_uas_anmp.model.User
import com.paulus.project_uas_anmp.viewmodel.NewsListViewModel

class NewsListFragment : Fragment() {
    private lateinit var binding: FragmentNewsListBinding
    private lateinit var viewModel: NewsListViewModel
    private val newsListAdapter = NewsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        viewModel.refresh()
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = newsListAdapter

        binding.fobProfile.setOnClickListener {
            val action = NewsListFragmentDirections.actionNewsListProfileFragment("x")
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            newsListAdapter.updateNewsList(it)
            if(it.isEmpty()) {
                binding.recView?.visibility = View.GONE
                binding.txtError.setText("Your news still empty.")
            } else {
                binding.recView?.visibility = View.VISIBLE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.progressLoad?.visibility = View.GONE
            } else {
                binding.progressLoad?.visibility = View.VISIBLE
            }
        })

        viewModel.todoLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == false) {
                binding.txtError?.visibility = View.GONE
            } else {
                binding.txtError?.visibility = View.VISIBLE
            }
        })
    }
}