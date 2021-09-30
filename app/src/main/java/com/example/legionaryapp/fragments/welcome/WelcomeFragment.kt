package com.example.legionaryapp.fragments.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.legionaryapp.fragments.main.MainViewModel
import com.example.legionaryapp.navigation.Screen
import com.example.legionaryapp.navigation.navigate
import com.example.legionaryapp.ui.theme.LegionaryAppTheme

class WelcomeFragment : Fragment() {

    private val viewModel: WelcomeViewModel by viewModels { WelcomeViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateToEvent ->
            navigateToEvent.getContentIfNotHandled()?.let { navigateTo ->
                navigate(navigateTo, Screen.Welcome)
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                LegionaryAppTheme {
                    MainMenuScreen()
                }
            }
        }
    }
}
