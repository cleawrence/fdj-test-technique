package com.appiwedia.apps.android.fdjtest.ui.teamdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import coil.load
import com.appiwedia.apps.android.fdjtest.common.ViewBindingFragment
import com.appiwedia.apps.android.fdjtest.databinding.FragmentTeamDetailBinding
import com.appiwedia.apps.android.fdjtest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : ViewBindingFragment<FragmentTeamDetailBinding>() {

    private val viewModel: TeamDetailViewModel by viewModels()
    private var streamName: String? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamDetailBinding
        get() = FragmentTeamDetailBinding::inflate

    override fun setup() {
        binding.toolbarTeamDetail.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))

        streamName = arguments?.getString(teamName)
        streamName?.let { viewModel.getTeamDetail(it) }

        viewModel.teamDetails.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Error -> {
                    Toast.makeText(activity, "${state.error}", Toast.LENGTH_SHORT).show()
                }
                Resource.Loading -> {
                    Toast.makeText(activity, "Loading...", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    val team = state.data?.teams?.get(0)
                    binding.bannerImage.load(team?.strTeamBanner) {
                        crossfade(true)
                        crossfade(300)
                    }

                    binding.teamCountry.text = team?.strCountry
                    binding.teamLeague.text = team?.strLeague
                    binding.teamDescription.text = team?.strDescriptionEN
                    binding.titleToolbarTeamInfo.text = team?.strTeam
                }
            }
        }
    }

    companion object {
        const val teamName = "teamName"
    }
}