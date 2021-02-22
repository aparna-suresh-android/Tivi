package com.app.tivi.features.tvDetails.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.tivi.TiviApplication
import com.app.tivi.databinding.FragTvDetailsBinding
import com.app.tivi.di.ViewModelFactory
import com.app.tivi.features.tvDetails.ShowDeatilsViewModel
import javax.inject.Inject


class ShowDetailsFragment : Fragment() {
    @Inject
    lateinit var mViewModelFactory : ShowDeatilsViewModel.ShowDeatilsViewModelFactoryI;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as TiviApplication)
                .mAppComponent
                .getShowDetails()
                .create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragTvDetailsBinding.inflate(inflater);
        binding.lifecycleOwner = viewLifecycleOwner

        val viewModel = ViewModelProvider(this,ViewModelFactory(mViewModelFactory,
            arguments))
            .get(ShowDeatilsViewModel::class.java);
        binding.showDeatilsViewModel = viewModel;

        val linearLayoutManager =
            LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);

        binding.similarShowsList.run {
            layoutManager = linearLayoutManager
            adapter = SimilarShowsAdapter()
        }
        val linearLayoutManager2 =
                LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        binding.castList.run {
            layoutManager = linearLayoutManager2
            adapter = CastItemAdapter()
        }

        return binding.root
    }
}