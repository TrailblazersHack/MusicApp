package ru.trailblazers.musicapp.ui.screens.bottomnav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

/**
 * @author nvoxel
 */
object FavoritesTab : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(image = Icons.Default.Favorite)

            return remember {
                TabOptions(
                    index = 0u,
                    title = "Избранное",
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Text(text = "Избранное")
    }
}
