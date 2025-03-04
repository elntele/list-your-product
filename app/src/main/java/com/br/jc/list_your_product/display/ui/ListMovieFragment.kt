package com.br.jc.list_your_product.display.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.br.jc.list_your_product.base.BaseFragment
import com.br.jc.list_your_product.databinding.FragmentListMovieBinding
import com.br.jc.list_your_product.display.adapter.MovieDisplayAdapter
import com.br.jc.list_your_product.rest.model.Movie
import com.br.jc.list_your_product.rest.viewmodel.WaitRestViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ListMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListMovieFragment : BaseFragment() {

    private lateinit var biding: FragmentListMovieBinding
    private val waitRestViewModel: WaitRestViewModel by sharedViewModel()
    private lateinit var movieAdapter: MovieDisplayAdapter
    private lateinit var recyclerPrincipal: RecyclerView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        biding = FragmentListMovieBinding.inflate(inflater, container, false)
        recyclerPrincipal = biding.listMoviesPrincipal
        return biding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        waitRestViewModel.moviesFromDisCover.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                observeFromDiscover(movies as MutableList)
            }
        }



    }

    private fun observeFromDiscover(movies: MutableList<Movie>) {
        /* val movieList = mutableListOf(
             Movie(
                 1,
                 "english",
                 "dark side",
                 "filme mock",
                 0.3,
                 "www.123",
                 "10/12/2024",
                 "lado negro",
                 false,
                 0.3,
                 300
             ),
             Movie(
                 2,
                 "english",
                 "wonder woman",
                 "filme mock 2",
                 0.6,
                 "www.12345",
                 "10/10/2022",
                 "mulher maravilha",
                 false,
                 0.7,
                 1500
             )
         )*/

        movieAdapter = MovieDisplayAdapter(movies)

        // 1- Configurar o LayoutManager (Linear para lista vertical)
        recyclerPrincipal.layoutManager = LinearLayoutManager(requireContext())

        //2 - Associar o Adapter à RecyclerView
        recyclerPrincipal.adapter = movieAdapter

        //3 - (Opcional) Adicionar separação entre os itens
        recyclerPrincipal.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }


}