package com.app.tivi.features.favourites.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.tivi.R
import com.app.tivi.TiviApplication
import com.app.tivi.databinding.FragFavShowsListBinding
import com.app.tivi.features.favourites.FavouriteShowsViewModel
import com.app.tivi.features.mainactivity.MainActivity
import com.app.tivi.features.mainactivity.MainActivityViewModel
import com.app.tivi.features.popular.ui.ShowClickListener
import com.app.tivi.features.popular.ui.ShowListAdapter
import javax.inject.Inject


class FavouriteShowsFragment : Fragment() {
    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(requireActivity() as MainActivity, mViewModelFactory)
            .get(MainActivityViewModel::class.java)
    }

    private lateinit var mBinding: FragFavShowsListBinding
    private lateinit var mAdapter: ShowListAdapter


    private val mShowClickListener: ShowClickListener = ShowClickListener { showListItem, view ->

        when (view.id) {
            R.id.showItemView -> {

                mActivityViewModel.onShowClicked(showListItem.id)
            }
            R.id.favourite -> {
                showListItem.isFavourite = false;
//                mActivityViewModel.mFavItemsRemoved.value?.add(showListItem.id);
                mViewModel.updateFavourite(showListItem)
            }
        }
    }

    private lateinit var mViewModel: FavouriteShowsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TiviApplication)
            .mAppComponent.getFavShowsComponent().create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = FragFavShowsListBinding.inflate(inflater)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mViewModel = ViewModelProvider(this, mViewModelFactory)
            .get(FavouriteShowsViewModel::class.java)

        mBinding.viewModel = mViewModel
        (requireActivity() as MainActivity).supportActionBar?.title = "Favourites";
        initRecyclerView()

        mViewModel.removedItemIds.observe(viewLifecycleOwner,{

            setFragmentResult("removedFavShows", bundleOf("ids" to it.toLongArray()))
        })
        return mBinding.root
    }


    private fun initRecyclerView() {

        mAdapter = FavouriteShowsAdapter(mShowClickListener)

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


}