package com.paulus.project_uas_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.paulus.project_uas_anmp.R
import com.paulus.project_uas_anmp.databinding.FragmentLoginBinding
import com.paulus.project_uas_anmp.databinding.FragmentRegistBinding
import com.paulus.project_uas_anmp.model.User
import com.paulus.project_uas_anmp.viewmodel.UserViewModel

class LoginFragment : Fragment(), RegistClick, LoginClick {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onLoginClick(v: View, obj: User) {
        if (obj.password == "" || obj.username == "")
        {
            viewModel.login(obj.username, obj.password)
            Toast.makeText(v.context, "Welcome", Toast.LENGTH_LONG).show()
            val action = LoginFragmentDirections.actionLoginNewsListFragment(v.tag.toString())
            Navigation.findNavController(v).navigate(action)
        }
        else
        {
            Toast.makeText(v.context, "You should fill this blank", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRegistClick(v: View) {
        val action = LoginFragmentDirections.actionLoginRegistFragment()
        Navigation.findNavController(v).navigate(action)
    }
}