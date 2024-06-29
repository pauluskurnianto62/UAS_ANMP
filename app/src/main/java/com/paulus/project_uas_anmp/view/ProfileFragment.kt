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
import com.paulus.project_uas_anmp.R
import com.paulus.project_uas_anmp.databinding.FragmentProfileBinding
import com.paulus.project_uas_anmp.model.User
import com.paulus.project_uas_anmp.viewmodel.UserViewModel

class ProfileFragment : Fragment(), ProfileChangeClick, LogoutClick, BackMainClick {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.updateListener = this
        binding.logoutListener = this
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.user = it
            binding.txtInfoUsername.setText(it.username)
            binding.txtInfoEmail.setText(it.email)
        })
    }

    override fun onBackMainClick(v: View) {
        Navigation.findNavController(v).popBackStack()
    }

    override fun onLogoutClick(v: View) {
        val action = ProfileFragmentDirections.actionLogout()
        Navigation.findNavController(v).navigate(action)
    }

    override fun onProfileChangeClick(v: View, obj: User) {
        if (obj.password == binding.txtEditRetypePassword.toString())
        {
            viewModel.update(obj.password, obj.username)
            Toast.makeText(v.context, "Your Password Has Been Updated", Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(v.context, "Oops, your retype password is not same as your password, please try again", Toast.LENGTH_SHORT).show()
        }
    }
}