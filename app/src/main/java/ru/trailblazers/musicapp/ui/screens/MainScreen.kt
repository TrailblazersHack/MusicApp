package ru.trailblazers.musicapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import ru.trailblazers.musicapp.ui.screens.bottomnav.FavoritesTab
import ru.trailblazers.musicapp.ui.screens.bottomnav.MainTab
import ru.trailblazers.musicapp.ui.screens.bottomnav.ProfileTab

/**
 * @author nvoxel
 */
class MainScreen : Screen {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalVoyagerApi::class, ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    @Composable
    override fun Content() {
        TabNavigator(
            tab = MainTab,
            tabDisposable = {
                TabDisposable(
                    navigator = it,
                    tabs = listOf(FavoritesTab, MainTab, ProfileTab)
                )
            }
        ) { tabNavigator ->
            val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
            bottomBarState.value = true // todo: hide bottom nav bar when player screen is opened

            Scaffold(
                content = { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .padding(
                                top = paddingValues.calculateTopPadding(),
                                bottom = paddingValues.calculateBottomPadding(),
                            )
                            .consumeWindowInsets(paddingValues)
                            .fillMaxSize()
                    ) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = bottomBarState.value,
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it })
                    ) {
                        NavigationBar {
                            TabNavigationItem(tab = FavoritesTab)
                            TabNavigationItem(tab = MainTab)
                            TabNavigationItem(tab = ProfileTab)
                        }
                    }
                }
            )
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) },
            label = { Text(text = tab.options.title) },
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
        )
    }
}
