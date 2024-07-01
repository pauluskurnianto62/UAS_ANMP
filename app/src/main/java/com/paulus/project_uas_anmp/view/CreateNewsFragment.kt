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

class CreateNewsFragment : Fragment(), CancelCreateClick {
    private lateinit var binding: FragmentCreateNewsBinding
    private lateinit var viewModel: NewsDetailViewModel

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
        binding.btnSubmit.setOnClickListener {
            if (binding.txtNewTitle.text.toString() != ""
                && binding.txtNewAuthor.text.toString() != ""
                && binding.txtNewShortDesc.text.toString() != ""
                && binding.txtNewArticle.text.toString() != ""
                && binding.txtNewImage.text.toString() != "")
            {
                val news = News(binding.txtNewTitle.text.toString(), binding.txtNewAuthor.text.toString(), binding.txtNewShortDesc.text.toString(), binding.txtNewImage.text.toString(), binding.txtNewArticle.text.toString())
                val list = listOf(news)
                viewModel.addNews(list)
                Toast.makeText(it.context, "Data added", Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).popBackStack()

            }
            else
            {
                Toast.makeText(it.context, "You should fill in all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCancelCreateClick(v: View) {
        Navigation.findNavController(v).popBackStack()
    }
}