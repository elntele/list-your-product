package com.br.jc.list_your_product.login.ui.login

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.PixelCopy.Request
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.base.Error
import com.br.jc.list_your_product.base.Loaded
import com.br.jc.list_your_product.databinding.FragmentLoginBinding
import com.br.jc.list_your_product.login.model.User
import com.br.jc.list_your_product.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.auth
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
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