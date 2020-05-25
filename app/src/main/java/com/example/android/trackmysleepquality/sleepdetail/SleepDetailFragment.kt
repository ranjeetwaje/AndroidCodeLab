package com.example.android.trackmysleepquality.sleepdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.database.SleepDatabase
import com.example.android.trackmysleepquality.databinding.FragmentSleepDetailBinding
import com.example.android.trackmysleepquality.sleepquality.SleepQualityFragmentArgs

class SleepDetailFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentSleepDetailBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_sleep_detail, container, false)

        val application = requireNotNull(this.activity).application
        val args = SleepQualityFragmentArgs.fromBundle(arguments!!)

        val dataSource = SleepDatabase.getInstnace(application).sleepDatabaseDao
        val viewModelFactory = SleepDetailViewModelFactory(args.sleepNightKey, dataSource)
        val sleepDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SleepDetailViewModel::class.java)

        binding.sleepDetailViewModel = sleepDetailViewModel
        binding.setLifecycleOwner(this)
        sleepDetailViewModel.navigateToSleepTracker.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                this.findNavController().navigate(SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment())
                sleepDetailViewModel.doneNavigating()
            }
        })

        return binding.root
    }
}