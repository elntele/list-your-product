package com.br.jc.list_your_product.rest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.br.jc.list_your_product.databinding.FragmentWaitRestBinding
import com.br.jc.list_your_product.rest.viewmodel.WaitRestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WaitRestFragment : Fragment() {
    private lateinit var binding: FragmentWaitRestBinding
    private val waitRestViewModel: WaitRestViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWaitRestBinding.inflate(inflater, container, false)
        callData()
        return binding.root
    }

    private fun callData(){
      //  waitRestViewModel.getIdsList()
       // waitRestViewModel.getMovieList()
        waitRestViewModel.searchMovie("star wars")

    }

    private fun observeIdData(){
        waitRestViewModel.idsReferences.observe(viewLifecycleOwner){

        }
    }

}