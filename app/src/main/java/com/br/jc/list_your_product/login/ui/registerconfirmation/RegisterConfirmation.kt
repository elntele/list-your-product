package com.br.jc.list_your_product.login.ui.registerconfirmation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.databinding.FragmentLoginBinding
import com.br.jc.list_your_product.databinding.FragmentRegisterBinding
import com.br.jc.list_your_product.databinding.FragmentRegisterConfirmationBinding
import com.br.jc.list_your_product.login.ui.resgister.RegisterFragment
import com.br.jc.list_your_product.login.viewmodel.LoginViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterConfirmation : Fragment() {
    private lateinit var binding: FragmentRegisterConfirmationBinding
    private val loginViewModel: LoginViewModel by viewModel()
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRegisterConfirmationBinding.inflate(inflater, container, false)
        backToTheLogin()
        return binding.root
        return inflater.inflate(R.layout.fragment_register_confirmation, container, false)
    }

    private fun backToTheLogin(){
        binding.backToLoginButton.setOnClickListener{

            /*al action =
            LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
            */
            val action= RegisterConfirmationDirections.actionRegisterConfirmationToLoginFragment()
            findNavController().navigate(action)
        }
    }

}