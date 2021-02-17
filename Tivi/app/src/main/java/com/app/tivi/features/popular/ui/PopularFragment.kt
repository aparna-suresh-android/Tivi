package com.app.tivi.features.popular.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.tivi.TiviApplication
import com.app.tivi.databinding.FragPopularTvBinding
import com.app.tivi.features.mainactivity.MainActivity
import com.app.tivi.features.mainactivity.MainActivityViewModel
import com.app.tivi.features.popular.PopularTvViewModel
import javax.inject.Inject


class PopularFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    val mActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(requireActivity() as MainActivity, mViewModelFactory)
            .get(MainActivityViewModel::class.java)
    }

    private lateinit var mAdapter: PopularTvAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TiviApplication)
            .mAppComponent.getPopularTvComponent().create()
            .injectPopularTvFragment(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragPopularTvBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel = ViewModelProvider(this, mViewModelFactory)
            .get(PopularTvViewModel::class.java)
        binding.viewModel = viewModel


        val linearLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        mAdapter = PopularTvAdapter(ShowClickListener { showId ->
            mActivityViewModel.onShowClicked(showId)
        })


        binding.list.run {
            layoutManager = linearLayoutManager
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
        return binding.root
    }
}

