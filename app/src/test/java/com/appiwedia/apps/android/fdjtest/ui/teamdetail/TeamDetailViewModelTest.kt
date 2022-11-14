package com.appiwedia.apps.android.fdjtest.ui.teamdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appiwedia.apps.android.fdjtest.ui.team.FakeTeamRepository
import com.appiwedia.apps.android.fdjtest.ui.team.MainCoroutineRule
import com.appiwedia.apps.android.fdjtest.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TeamDetailViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TeamDetailViewModel

    @Before
    fun setUp() {
        viewModel = TeamDetailViewModel(FakeTeamRepository())
    }

    @Test
    fun `load teams from a league and check if success`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            viewModel.getTeamDetail("")
            assert(viewModel.teamDetails.value is Resource.Success)
        }
    }
}