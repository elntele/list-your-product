package com.br.jc.list_your_product.display.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.br.jc.list_your_product.databinding.FragmentInitialScreenBinding
import com.br.jc.list_your_product.databinding.FragmentLoginBinding
import com.br.jc.list_your_product.login.ui.login.LoginFragmentDirections



class FragmentInitialScreen : Fragment() {
    lateinit var binding: FragmentInitialScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInitialScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonListInitialScreen.setOnClickListener {
            val list=true;
                val action =
                    FragmentInitialScreenDirections.actionFragmentInitialScreenToWaitRestFragment22(
                        list, ""

                        )
                findNavController().navigate(action)

        }

        binding.searchMovieButton.setOnClickListener{
            val action = FragmentInitialScreenDirections.actionFragmentInitialScreenToFragmentSearchMovie()
            findNavController().navigate(action)
        }
    }


}