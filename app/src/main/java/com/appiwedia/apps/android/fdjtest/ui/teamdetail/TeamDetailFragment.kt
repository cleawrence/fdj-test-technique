package com.appiwedia.apps.android.fdjtest.ui.teamdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.appiwedia.apps.android.fdjtest.common.ViewBindingFragment
import com.appiwedia.apps.android.fdjtest.databinding.FragmentTeamsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : ViewBindingFragment<FragmentTeamsBinding>() {

    private lateinit var viewModel: TeamDetailViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamsBinding
        get() = FragmentTeamsBinding::inflate

    override fun setup() {
        viewModel = ViewModelProvider(this).get(TeamDetailViewModel::class.java)
    }

}