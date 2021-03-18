package com.hyperclock.instant.keepfit.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyperclock.instant.keepfit.ActivityViewModel
import com.hyperclock.instant.keepfit.R
import com.hyperclock.instant.keepfit.adapters.ActivitiesAdapter
import com.hyperclock.instant.keepfit.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_activity.*
import kotlinx.android.synthetic.main.fragment_activity.view.*

@AndroidEntryPoint
class ActivityFragment : Fragment() {

    private val activityViewModel by viewModels<ActivityViewModel>()
    private val adapter : ActivitiesAdapter by lazy { ActivitiesAdapter(requireActivity()) }
    private val args: ActivityFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_activity, container, false)

        activityViewModel.authData = args.authData
        Log.d("ActivityFragment", "auth values : "+args.authData.tokenType + " " + args.authData.accessToken)

        rootView.recycler_view.adapter = adapter
        rootView.recycler_view.layoutManager = LinearLayoutManager(requireContext())
        activityViewModel.getActivities(activityViewModel.applyQueries(), activityViewModel.applyHeader())
        activityViewModel.activitiesResponse.observe(viewLifecycleOwner, { response ->
            when(response) {
                is NetworkResult.Loading -> progress_meter.visibility = View.VISIBLE
                is NetworkResult.Error -> {
                    progress_meter.visibility = View.GONE
                    error_text_headline.visibility = View.VISIBLE
                    error_text_sub_headline.visibility = View.VISIBLE
                    error_text_sub_headline.text = response.message
                }
                is NetworkResult.Success -> {
                    progress_meter.visibility = View.GONE
                    error_text_headline.visibility = View.GONE
                    error_text_sub_headline.visibility = View.GONE
                    response.data?.let { adapter.setData(it.activities) }
                    adapter.setData(response.data!!.activities)
                }
            }
        })

        // Inflate the layout for this fragment
        return rootView
    }

}