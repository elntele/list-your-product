package com.br.jc.list_your_product.login.ui.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.common.Error
import com.br.jc.list_your_product.common.Loaded
import com.br.jc.list_your_product.databinding.FragmentLoginBinding
import com.br.jc.list_your_product.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModel()
    private val auth = Firebase.auth

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
        buttonExit()
        return binding.root

    }

    private fun loginButton() {

        binding.loginButton.setOnClickListener {
            val email = binding.emailLogin.text.toString()
            val password = binding.passwordLogin.text.toString()
            loginViewModel.isFieldLoginEmpty(email, password).observe(viewLifecycleOwner) { it ->
                when (it) {
                    is Loaded -> {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Activity()) { task ->
                                if (task.isSuccessful) {
                                    getSnackBar("createUserWithEmail:success")
                                } else {
                                   loginViewModel.getExceptionCause(task.exception!!).observe(viewLifecycleOwner){ exception->
                                        when(exception){
                                            is Error ->{
                                                exception.message?.let {
                                                    getSnackBar(exception.message)
                                                }
                                            }
                                            else->{}
                                        }

                                    }
                                }
                            }
                    }

                    is Error -> {
                        getSnackBar(it.message.toString())
                    }

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

    private fun buttonExit() {
        binding.exitImageScreenLogin.setOnClickListener {
            activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.setMessage("Deseja Sair do Aplicativo?")
                builder.setCancelable(true)
                builder.setPositiveButton(
                    "Sim"
                ) { dialogInterface, i ->
                    it.finish()
                    it.finishAffinity()
                    System.exit(0)
                }
                builder.setNegativeButton(
                    "Não"
                ) { dialogInterface, i -> dialogInterface.cancel() }
                val alertDialog = builder.create()
                alertDialog.show()
            }
        }
    }

    private fun getSnackBar(message: String) {
        var result = ""
        try {
            result = "${resources.getString(message.toInt())}"
        } catch (e: Exception) {
            result = message
        }
        val snack =
            Snackbar.make(
                binding.root,
                result,
                Snackbar.LENGTH_INDEFINITE
            ).setMaxInlineActionWidth(128)
        snack.setAction(R.string.click_to_continue) {
        }
        snack.show()
    }

}