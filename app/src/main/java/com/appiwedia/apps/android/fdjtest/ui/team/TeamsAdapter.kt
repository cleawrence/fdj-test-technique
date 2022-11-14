package com.appiwedia.apps.android.fdjtest.ui.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.appiwedia.apps.android.fdjtest.databinding.LayoutItemTeamBinding
import com.appiwedia.apps.android.fdjtest.models.team.Team

class TeamsAdapter(
    private val OnClick: (Team) -> Unit,
) : RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(
        private val binding: LayoutItemTeamBinding, onClick: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { onClick(adapterPosition) }
        }

        fun bind(team: Team) {
            binding.imageLogoTeam.load(team.strTeamBadge) {
                crossfade(true)
                crossfade(300)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(LayoutItemTeamBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)) { position ->
            teams?.get(position)?.let { OnClick(it) }
        }
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams?.get(position)
        team?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return teams!!.size
    }

    private val differCallBack = object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.idTeam == newItem.idTeam
        }

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }

    var teams: List<Team?>?
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    private val differ = AsyncListDiffer(this, differCallBack)
}