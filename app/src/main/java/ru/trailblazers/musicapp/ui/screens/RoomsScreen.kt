package ru.trailblazers.musicapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import ru.trailblazers.musicapp.R
import ru.trailblazers.musicapp.data.models.Room
import ru.trailblazers.musicapp.data.models.User

/**
 * @author nvoxel
 */
class RoomsScreen : Screen {

    private val roomsList = listOf(
        Room(id = 0L, name = "Название комнаты", participants = 10, host = User(id = 0)),
        Room(id = 1L, name = "Я комната", participants = 20, host = User(id = 0)),
        Room(id = 2L, name = "Название", participants = 5, host = User(id = 0)),
        Room(id = 3L, name = "Комнаты", participants = 15, host = User(id = 0)),
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val openAlertDialog = rememberSaveable { mutableStateOf(false) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Поиск")
                        }
                    },
                )
            },
            content = { paddingValues ->
                RoomsList(paddingValues = paddingValues)

                if (openAlertDialog.value)
                    CreateRoomAlertDialog(
                        onDismissCallback = { openAlertDialog.value = false },
                        onContinueCallback = { /*TODO*/ }
                    )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        openAlertDialog.value = true
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Создать комнату")
                }
            }
        )
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    private fun RoomsList(paddingValues: PaddingValues) {
        val navigator = LocalNavigator.current

        LazyColumn(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
        ) {
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
                                text = "${room.participants} ${
                                    pluralStringResource(
                                        id = R.plurals.participants,
                                        count = room.participants
                                    )
                                }"
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun CreateRoomAlertDialog(onDismissCallback: () -> Unit, onContinueCallback: (String) -> Unit) {
        Dialog(
            onDismissRequest = { onDismissCallback() }
        ) {
            var roomName by rememberSaveable { mutableStateOf("") }

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Введите название комнаты")
                    OutlinedTextField(
                        modifier = Modifier.padding(vertical = 16.dp),
                        value = roomName,
                        onValueChange = { value -> roomName = value },
                        label = { Text("Название комнаты") },
                        maxLines = 1
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        TextButton(onClick = { onDismissCallback() }) {
                            Text(text = "Отмена")
                        }
                        TextButton(onClick = {
                            if (roomName.isNotEmpty()) onContinueCallback(roomName)
                        }) {
                            Text(text = "Продолжить")
                        }
                    }
                }
            }
        }
    }
}
