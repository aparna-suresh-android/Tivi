package com.app.tivi.features.popular.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.tivi.R
import com.app.tivi.TiviApplication
import com.app.tivi.databinding.FragShowsListBinding
import com.app.tivi.features.mainactivity.MainActivity
import com.app.tivi.features.mainactivity.MainActivityViewModel
import com.app.tivi.features.popular.PopularTvViewModel
import javax.inject.Inject


class PopularFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(requireActivity() as MainActivity, mViewModelFactory)
            .get(MainActivityViewModel::class.java)
    }

    private lateinit var mBinding: FragShowsListBinding
    private lateinit var mAdapter: ShowListAdapter

    private val mShowClickListener: ShowClickListener = ShowClickListener { showListItem, view ->
        when (view.id) {
            R.id.showItemView -> {
                mActivityViewModel.onShowClicked(showListItem.id)
            }
            R.id.favourite -> {
                mAdapter.updateFavourite(showListItem)
                mViewModel.updateFavourite(showListItem)
            }
        }
    }

    private lateinit var mViewModel: PopularTvViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TiviApplication)
            .mAppComponent.getPopularTvComponent().create()
            .injectPopularTvFragment(this)
        setHasOptionsMenu(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setFragmentResultListener("removedFavShows") { requestKey, bundle ->
            val removedItemsKey = bundle.getLongArray("ids")
            mViewModel.updateShows(removedItemsKey)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragShowsListBinding.inflate(inflater)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mViewModel = ViewModelProvider(this, mViewModelFactory)
            .get(PopularTvViewModel::class.java)
        mBinding.viewModel = mViewModel
        (requireActivity() as MainActivity).supportActionBar?.title = "Popular"
        initRecyclerView()
       return mBinding.root
    }


    private fun initRecyclerView() {

        mAdapter = PopularTvAdapter(mShowClickListener)

        val linearLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL, false
        )
        mBinding.list.run {
            layoutManager = linearLayoutManager
            adapter = mAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favourites, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favourites -> {
                mActivityViewModel.showFavourites()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}

