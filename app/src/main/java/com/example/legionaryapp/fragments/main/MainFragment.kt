package com.example.legionaryapp.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.legionaryapp.navigation.Screen
import com.example.legionaryapp.navigation.navigate
import com.example.legionaryapp.ui.theme.LegionaryAppTheme

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateToEvent ->
            navigateToEvent.getContentIfNotHandled()?.let { navigateTo ->
                navigate(navigateTo, Screen.Main)
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                LegionaryAppTheme {
                    MainScreen()
                }
            }
        }

    }
}