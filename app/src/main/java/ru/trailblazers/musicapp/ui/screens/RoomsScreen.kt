package ru.trailblazers.musicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import ru.trailblazers.musicapp.R
import ru.trailblazers.musicapp.data.models.Room

/**
 * @author nvoxel
 */
class RoomsScreen : Screen {

    private val roomsList = listOf(
        Room(id = 0L, name = "Название комнаты", participants = 10),
        Room(id = 1L, name = "Я комната", participants = 20),
        Room(id = 2L, name = "Название", participants = 5),
        Room(id = 3L, name = "Комнаты", participants = 15),
    )

    @OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

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

            items(roomsList) { room ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth()
                        .clickable {
                            navigator?.push(PlayerScreen(room))
                        }
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Image(
                            modifier = Modifier
                                .size(size = 40.dp),
                            painter = painterResource(id = R.drawable.room),
                            contentDescription = room.name
                        )

                        Column {
                            Text(text = room.name)
                            Text(
                                text = "${room.participants} ${pluralStringResource(id = R.plurals.participants, count = room.participants)}"
                            )
                        }
                    }
                }
            }
        }
    }
}
