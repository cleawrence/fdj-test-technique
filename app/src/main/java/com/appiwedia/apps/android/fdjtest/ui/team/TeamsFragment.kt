package com.appiwedia.apps.android.fdjtest.ui.team

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.appiwedia.apps.android.fdjtest.common.ViewBindingFragment
import com.appiwedia.apps.android.fdjtest.databinding.FragmentTeamsBinding
import com.appiwedia.apps.android.fdjtest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TeamsFragment : ViewBindingFragment<FragmentTeamsBinding>() {

    private val viewModel: TeamsViewModel by viewModels()
    private lateinit var teamsAdapter: TeamsAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTeamsBinding
        get() = FragmentTeamsBinding::inflate

    override fun setup() {
        setUpRecyclerView()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.leagues.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is Resource.Error -> {
                        Toast.makeText(activity, "${state.error}", Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Success -> {
                        val adapter = ArrayAdapter(requireContext(),
                            androidx.appcompat.R.layout.select_dialog_item_material,
                            viewModel.getListLeagues(state.data?.leagues))
                        binding.txtAutocomplete.setAdapter(adapter)
                        binding.txtAutocomplete.threshold = 2
                    }
                    Resource.Loading -> {
                        Timber.tag("Chargement").i("loading....")
                    }
                }
            }
        }
        binding.txtAutocomplete.setOnItemClickListener { adapterView, _, i, _ ->
            val inputMethodManager =
                adapterView?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(adapterView.applicationWindowToken, 0)
            findListTeams(adapterView.adapter.getItem(i).toString())
        }
    }

    private fun findListTeams(nameLeague: String) {
        viewModel.getTeamLeagues(nameLeague)
        viewModel.teamLeague.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Error -> {
                    Toast.makeText(activity, "${state.error}", Toast.LENGTH_SHORT).show()
                }
                Resource.Loading -> {
                    Timber.tag("Chargement")
                        .i("team league : $nameLeague")
                }
                is Resource.Success -> {
                    teamsAdapter.teams = viewModel.reverse(state.data?.teams)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        teamsAdapter = TeamsAdapter { team ->
            val action =
                TeamsFragmentDirections.actionTeamsFragmentToTeamDetailFragment(teamName = team.strTeam)
            findNavController().navigate(action)
        }
        binding.rvTeamsLeague.apply {
            adapter = teamsAdapter
            layoutManager = GridLayoutManager(activity, 2)
        }
        binding.rvTeamsLeague.isNestedScrollingEnabled = false
    }
}