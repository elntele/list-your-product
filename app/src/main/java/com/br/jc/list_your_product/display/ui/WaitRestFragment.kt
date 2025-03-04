package com.br.jc.list_your_product.display.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.br.jc.list_your_product.base.BaseFragment
import com.br.jc.list_your_product.databinding.FragmentWaitRestBinding
import com.br.jc.list_your_product.rest.viewmodel.WaitRestViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WaitRestFragment : BaseFragment() {
    private lateinit var binding: FragmentWaitRestBinding
    private val waitRestViewModel: WaitRestViewModel by sharedViewModel()
    private val args: WaitRestFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWaitRestBinding.inflate(inflater, container, false)

        callData()
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val goToList = args.list
        when (goToList) {
            true -> {
                waitRestViewModel.getDiscoverMovieList()
            }

            else -> {
                val name = args.movieName
                waitRestViewModel.searchMovie(name)
            }
        }
    }

    private fun callData() {
        //  waitRestViewModel.getIdsList()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        waitRestViewModel.moviesFromDisCover.observe(viewLifecycleOwner) {
            val action = WaitRestFragmentDirections.actionWaitRestFragmentToListMovieFragment()
            findNavController().navigate(action)
        }

        waitRestViewModel.moviesFromSearch.observe(viewLifecycleOwner) {
            waitRestViewModel.setMoviesFromSearch(it)
            val action = WaitRestFragmentDirections.actionWaitRestFragmentToListMovieFragment()
            findNavController().navigate(action)
        }
    }

    private fun observeIdData() {
        waitRestViewModel.idsReferences.observe(viewLifecycleOwner) {

        }
    }

}