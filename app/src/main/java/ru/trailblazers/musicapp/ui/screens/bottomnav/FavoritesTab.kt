package ru.trailblazers.musicapp.ui.screens.bottomnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ru.trailblazers.musicapp.R
import ru.trailblazers.musicapp.data.models.Track
import ru.trailblazers.musicapp.ui.utils.TrackUtils.Companion.songDurationToString

/**
 * @author nvoxel
 */
object FavoritesTab : Tab {

    private val favoritesList = listOf(
        Track(id = 0L, name = "Песня 1", artist = "Исполнитель 1", durationMillis = 180000),
        Track(id = 1L, name = "Песня 2", artist = "Исполнитель 2", durationMillis = 180000),
        Track(id = 2L, name = "Песня 3", artist = "Исполнитель 3", durationMillis = 180000),
        Track(id = 3L, name = "Песня 4", artist = "Исполнитель 4", durationMillis = 180000),
    )

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

    @OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        LazyColumn {
            item {
                TopAppBar(
                    title = {},
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Поиск")
                        }
                    },
                )
            }

            items(favoritesList) { favorite ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            modifier = Modifier
                                .size(size = 40.dp),
                            painter = painterResource(id = R.drawable.track),
                            contentDescription = favorite.name
                        )

                        Column {
                            Text(text = "${favorite.name} – ${favorite.artist}")
                            Text(text = songDurationToString(favorite.durationMillis))
                        }

                        IconButton(
                            onClick = {

                            },
                        ) {
                            Icon(imageVector = Icons.Filled.Star, contentDescription = "Убрать из изрбанного")
                        }
                    }
                }
            }
        }
    }
}
