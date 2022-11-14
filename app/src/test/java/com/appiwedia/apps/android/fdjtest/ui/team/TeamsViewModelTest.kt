package com.appiwedia.apps.android.fdjtest.ui.team

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appiwedia.apps.android.fdjtest.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TeamsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: TeamsViewModel

    @Before
    fun setUp() {
        viewModel = TeamsViewModel(FakeTeamRepository())
    }

    @Test
    fun `load leagues and check if success`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            viewModel.getLeagues()
            assert(viewModel.leagues.value is Resource.Success)
        }
    }

    @Test
    fun `load teams from a league and check if success`() {
        mainCoroutineRule.testDispatcher.runBlockingTest {
            viewModel.getTeamLeagues("")
            assert(viewModel.teamLeague.value is Resource.Success)
        }
    }
}