package com.appiwedia.apps.android.fdjtest.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appiwedia.apps.android.fdjtest.R

class TeamDetailFragment : Fragment() {

    companion object {
        fun newInstance() = TeamDetailFragment()
    }

    private lateinit var viewModel: TeamDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}