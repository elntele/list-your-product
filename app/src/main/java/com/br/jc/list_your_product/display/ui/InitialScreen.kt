package com.br.jc.list_your_product.display.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.jc.list_your_product.R
import com.br.jc.list_your_product.databinding.FragmentLoginBinding
import com.br.jc.list_your_product.databinding.FragmentWaitRestBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InitialScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class InitialScreen : Fragment() {
    lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


}