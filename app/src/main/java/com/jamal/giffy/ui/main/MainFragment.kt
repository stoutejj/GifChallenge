package com.jamal.giffy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.jamal.giffy.R
import com.jamal.giffy.data.model.GiffyImage
import com.jamal.giffy.data.network.WebServices
import com.jamal.giffy.data.repository.GiffyRepositoryImpl
import com.jamal.giffy.ui.imagedetail.ImageDetailFragment
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GifAdapter
    private val viewModelFactory = MainViewModelFactory(
        GiffyRepositoryImpl(
            WebServices.instance
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }


    private fun setupObservers() {
        viewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is MainViewModel.MainViewState.Loading -> showProgressBar()
                is MainViewModel.MainViewState.Success -> showData(state.images)
                is MainViewModel.MainViewState.Error -> showErrorMessage(state.errorMessage)
            }
        })
    }

    private fun setupRecyclerView() {
        rv_images.layoutManager = GridLayoutManager(context, 2)
        adapter = GifAdapter(mutableListOf()) { giffyImage ->
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, ImageDetailFragment.newInstance(giffyImage))
            transaction?.addToBackStack("")
            transaction?.commit()
        }
        rv_images.adapter = adapter
    }

    private fun showErrorMessage(errorMessage: String) {
        progress_circular.visibility = View.GONE
        tv_message.visibility = View.VISIBLE
        btn_retry.visibility = View.VISIBLE
        rv_images.visibility = View.GONE
        tv_message.text = errorMessage
    }

    private fun showData(images: List<GiffyImage>) {
        progress_circular.visibility = View.GONE
        tv_message.visibility = View.GONE
        btn_retry.visibility = View.GONE
        rv_images.visibility = View.VISIBLE
        adapter.updateImages(images)
    }

    private fun showProgressBar() {
        progress_circular.visibility = View.VISIBLE
        tv_message.visibility = View.GONE
        btn_retry.visibility = View.GONE
        rv_images.visibility = View.GONE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
        viewModel.fetchTrendingGifs()
    }

    private fun setupClickListeners() {
        btn_retry.setOnClickListener { viewModel.fetchTrendingGifs() }
    }

}