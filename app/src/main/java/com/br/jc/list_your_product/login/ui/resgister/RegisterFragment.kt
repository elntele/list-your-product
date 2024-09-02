package com.br.jc.list_your_product.login.ui.resgister

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.base.Loaded
import com.br.jc.list_your_product.base.Error
import com.br.jc.list_your_product.databinding.FragmentRegisterBinding
import com.br.jc.list_your_product.login.viewmodel.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import org.koin.androidx.viewmodel.ext.android.viewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModel()
    private val auth = Firebase.auth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        buttonCreateUser()
        buttonClear()
        return binding.root
    }

    private fun buttonCreateUser() {
        binding.registerButtonScreenRegister.setOnClickListener {
            val name = binding.nameRegister.text.toString()
            val email = binding.emailRegister.text.toString()
            val password = binding.registerPassword.text.toString()
            registerViewModel.isFieldLoginEmpty(name, email, password)
                .observe(viewLifecycleOwner) { it ->
                    when (it) {
                        is Loaded -> {
                            auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Activity()) { task ->
                                    if (task.isSuccessful) {
                                        getSnackBar("createUserWithEmail:success")
                                    } else {
                                        registerViewModel.getExceptionCause(task.exception!!).observe(viewLifecycleOwner){ exception->
                                            when(exception){
                                                is Error ->{
                                                    exception.message?.let {
                                                        getSnackBar(exception.message)
                                                    }
                                                }
                                                else->{}
                                            }
                        }}}}

                        is Error -> {
                            it.message?.let { it1 -> getSnackBar(it1) }
                        }

                        else -> {}
                    }

                }
        }

    }

    private fun buttonClear() {
        binding.clearButtonScreenRegister.setOnClickListener {
            binding.nameRegister.text.clear()
            binding.emailRegister.text.clear()
            binding.registerPassword.text.clear()
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