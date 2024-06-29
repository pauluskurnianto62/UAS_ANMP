package com.paulus.project_uas_anmp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.paulus.project_uas_anmp.R
import com.paulus.project_uas_anmp.databinding.FragmentRegistBinding
import com.paulus.project_uas_anmp.model.User
import com.paulus.project_uas_anmp.viewmodel.UserViewModel

class RegistFragment : Fragment(), CreateAccountClick, BackLoginClick {
    private lateinit var viewModel: UserViewModel
    private lateinit var binding: FragmentRegistBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)
        binding.backListener = this
        binding.createaccountListener = this
    }

    override fun onBackLoginClick(v: View) {
        Navigation.findNavController(v).popBackStack()
    }

    override fun onCreateAccountClick(v: View, obj: User) {
        if (obj.password == binding.txtRetypePassword.toString())
        {
            val user = User(obj.username, obj.email, obj.password)
            val list = listOf(user)
            viewModel.addUser(list)
            Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(v).popBackStack()
        }
        else
        {
            Toast.makeText(v.context, "Oops, your retype password is not same as your password, please try again", Toast.LENGTH_SHORT).show()
        }
    }
}