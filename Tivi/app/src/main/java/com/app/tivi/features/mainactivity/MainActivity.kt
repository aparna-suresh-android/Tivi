package com.app.tivi.features.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.app.tivi.TiviApplication

import com.app.tivi.databinding.ActivityMainBinding
import com.app.tivi.di.ViewModelFactory
import com.app.tivi.features.popular.ui.PopularFragment
import com.app.tivi.features.tvDetails.ui.ShowDetailsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory : ViewModelProvider.Factory;

    val viewModel : MainActivityViewModel by lazy {
        ViewModelProvider(this,mViewModelFactory)
            .get(MainActivityViewModel::class.java)
    }

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as TiviApplication)
                .mAppComponent.getMainActivityComponent().create().inject(this);
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.lifecycleOwner = this



        mBinding.viewModel = viewModel;

        viewModel.mShowId.observe(this,{event ->
            event.getEventDataIfNotHandled()?.let{showId ->
                val txn = supportFragmentManager.beginTransaction()
                val frag = ShowDetailsFragment();
                val bundle = Bundle();
                bundle.putLong("showId", showId);
                frag.arguments = bundle
                txn.replace(mBinding.container.id, frag)
                txn.addToBackStack(null);
                txn.commit()
            }
        })

        if(savedInstanceState == null) {
            val txn = supportFragmentManager.beginTransaction()
            txn.replace(mBinding.container.id, PopularFragment(), "popular")
            txn.commit()
        }
    }

}