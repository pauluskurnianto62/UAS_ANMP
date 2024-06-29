package com.paulus.project_uas_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.paulus.project_uas_anmp.databinding.FragmentCreateNewsBinding
import com.paulus.project_uas_anmp.databinding.FragmentProfileBinding
import com.paulus.project_uas_anmp.model.News
import com.paulus.project_uas_anmp.model.User
import com.paulus.project_uas_anmp.viewmodel.NewsDetailViewModel
import com.paulus.project_uas_anmp.viewmodel.NewsListViewModel
import com.paulus.project_uas_anmp.viewmodel.UserViewModel

class CreateNewsFragment : Fragment(), CreatedNewsClick, CancelCreateClick {
    private lateinit var binding: FragmentCreateNewsBinding
    private lateinit var viewModel: NewsDetailViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNewsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsDetailViewModel::class.java)
        binding.cancelListener = this
        binding.createdListener= this
        observeViewModel()
    }

    fun observeViewModel() {
        userViewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.user = it
            binding.txtNewAuthor.setText(it.username)
        })
    }

    override fun onCreatedNewsClick(v: View, obj: News) {
        val news = News(obj.title, obj.author, obj.short_desc, obj.article, obj.image)
        val list = listOf(news)
        viewModel.addNews(list)
        Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
        Navigation.findNavController(v).popBackStack()
    }

    override fun onCancelCreateClick(v: View) {
        Navigation.findNavController(v).popBackStack()
    }
}