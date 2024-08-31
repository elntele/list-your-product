package com.br.jc.list_your_product.login.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.base.Error
import com.br.jc.list_your_product.base.Loaded
import com.br.jc.list_your_product.databinding.FragmentLoginBinding
import com.br.jc.list_your_product.login.model.User
import com.br.jc.list_your_product.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth
    private val loginViewModel: LoginViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        registerButton()
        loginButton()
        return binding.root

    }

    private fun loginButton() {

        binding.loginButton.setOnClickListener {
            val email = binding.emailLogin.text.toString()
            val senha = binding.passwordLogin.text.toString()
            loginViewModel.isFieldLoginEmpty(email, senha).observe(viewLifecycleOwner) { it ->
                when (it) {
                    is Loaded -> {// aqui fazer a chamada para logir
                         }
                    is Error -> { getSnackBar(it.message.toString())}
                    else -> {}
                }
            }
        }
    }

    private fun registerButton() {
        binding.registerButton.setOnClickListener {
            val action =
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    private fun getSnackBar(message: String) {
        val result = loginViewModel.getNumbers(message)
        val finalMessage= "${resources.getString(result.first)}"
        val snack =
            Snackbar.make(
                binding.root,
                finalMessage,
                Snackbar.LENGTH_INDEFINITE
            ).setMaxInlineActionWidth(128)
        snack.setAction("${resources.getString(result.second)}") {
        }
        snack.show()
    }

    private fun logger(user: User) {

    }

}