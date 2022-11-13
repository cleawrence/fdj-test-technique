package com.appiwedia.apps.android.fdjtest.ui.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.appiwedia.apps.android.fdjtest.common.ViewBindingFragment
import com.appiwedia.apps.android.fdjtest.databinding.FragmentTeamsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : ViewBindingFragment<FragmentTeamsBinding>() {

    private val viewModel: TeamsViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamsBinding
        get() = FragmentTeamsBinding::inflate

    override fun setup() {

    }

}