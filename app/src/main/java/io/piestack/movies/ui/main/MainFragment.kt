package io.piestack.movies.ui.main

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import dagger.android.support.DaggerFragment
import io.piestack.movies.model.Movie
import io.piestack.movies.model.Order
import io.piestack.movies.util.GridItemDecoration
import io.piestack.movies.util.RecyclerItemClickListener
import io.piestack.movies.util.activityViewModelProvider
import io.piestack.movies.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject
import androidx.appcompat.app.AlertDialog
import io.piestack.movies.R


class MainFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val movieViewModel: MovieViewModel by lazy {
        activityViewModelProvider(viewModelFactory) as MovieViewModel
    }

    private val movieListAdapter = MovieListGridRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        movieViewModel.fetchMovies(Order.Popularity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()

        movieViewModel.getMovies()
        movieViewModel.movies.observe(this, Observer {
            movieListAdapter.setMovieList(it)
        })
    }

    private fun initView() {

        recyclerViewMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = movieListAdapter
            addItemDecoration(GridItemDecoration(10, 2))
            addOnItemTouchListener(
                RecyclerItemClickListener(
                    context,
                    recyclerViewMovies,
                    object : RecyclerItemClickListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            navigate(position.toString(), movieListAdapter.getItem(position))
                        }

                        override fun onLongItemClick(view: View, position: Int) {
                            // do whatever
                        }
                    })
            )
        }
    }

    private fun navigate(position: String, movie: Movie) {
        val action = MainFragmentDirections.actionNavigationHomeToNavigationDetail(movie)
        action.position = position
        action.movie = movie
        view?.let { Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(action) }
    }

    private fun displayDialog(){
        val listItems = arrayOf(Order.Popularity.name, Order.Rating.name)
        val mBuilder = AlertDialog.Builder(requireContext())
        mBuilder.setTitle("Sort according to")
        mBuilder.setSingleChoiceItems(
            listItems, -1
        ) { dialogInterface, i ->
            movieViewModel.fetchMovies(Order.valueOf(listItems[i]))
            dialogInterface.dismiss()
        }

        val mDialog = mBuilder.create()
        mDialog.show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null) {
            when (item.itemId) {
                R.id.sort -> displayDialog()
            }
        }
        return true
    }

}
