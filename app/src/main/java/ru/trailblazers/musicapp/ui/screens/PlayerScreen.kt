package ru.trailblazers.musicapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import ru.trailblazers.musicapp.R
import ru.trailblazers.musicapp.data.models.ChatMessage
import ru.trailblazers.musicapp.data.models.Room
import ru.trailblazers.musicapp.data.models.Track
import ru.trailblazers.musicapp.ui.utils.TrackUtils

/**
 * @author nvoxel
 */
class PlayerScreen(
    private val room: Room
) : Screen {

    private val currentTrack = Track(id = 0L, name = "ASHPHALT 8", artist = "MACAN", durationMillis = 200000L)
    private val isHost = false
    private val chatMessages = listOf(
        ChatMessage(sender = "Никнейм 1", text = "Текст сообщения"),
        ChatMessage(sender = "Никнейм 2", text = "Текст сообщения"),
        ChatMessage(sender = "Никнейм 3", text = "Текст сообщения"),
        ChatMessage(sender = "Никнейм 4", text = "Текст сообщения"),
    )

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AppBar()
            SongInfo()
            ProgressBar()
            if (isHost) MediaControls()
            ParticipantsButton()
            ChatWindow()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AppBar() {
        val navigator = LocalNavigator.current

        TopAppBar(
            title = { Text(text = room.name) },
            navigationIcon = {
                IconButton(onClick = { navigator?.pop() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Назад")
                }
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Пригласить в комнату")
                }
            },
        )
    }

    @Composable
    private fun SongInfo() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            OutlinedCard(
                modifier = Modifier
                    .size(size = 100.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(48.dp),
                        imageVector = Icons.Default.LibraryMusic,
                        contentDescription = "Музыка"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
            ) {
                Text(text = currentTrack.name, style = MaterialTheme.typography.titleLarge)
                Text(text = currentTrack.artist, style = MaterialTheme.typography.titleMedium)
            }
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    private fun ProgressBar() {
        var currentProgress by rememberSaveable { mutableFloatStateOf(0.5f) }

        if (isHost)
            Slider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = currentProgress,
                valueRange = 0f..(currentTrack.durationMillis / 1000).toFloat(),
                onValueChange = { value ->
                    currentProgress = value
                }
            )
        else
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                progress = currentProgress
            )

        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = TrackUtils.songDurationToString(currentProgress), style = MaterialTheme.typography.bodyMedium)
            Text(text = TrackUtils.songDurationToString(currentTrack.durationMillis), style = MaterialTheme.typography.bodyMedium)
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    private fun MediaControls() {
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 64.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilledIconButton(
                modifier = Modifier
                    .size(48.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier.size(size = 24.dp),
                    imageVector = Icons.Default.SkipPrevious,
                    contentDescription = "Назад"
                )
            }
            PlayButton()
            FilledIconButton(
                modifier = Modifier
                    .size(48.dp),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    modifier = Modifier.size(size = 24.dp),
                    imageVector = Icons.Default.SkipNext,
                    contentDescription = "Вперёд"
                )
            }
        }
    }

    @Composable
    private fun PlayButton() {
        var isPlaying by rememberSaveable { mutableStateOf(true) }
        FilledIconButton(
            modifier = Modifier
                .size(48.dp),
            onClick = {
                isPlaying = !isPlaying
            }
        ) {
            Icon(
                modifier = Modifier.size(size = 24.dp),
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Пауза" else "Воспроизведение"
            )
        }
    }

    @Composable
    private fun ParticipantsButton() {
        TextButton(
            modifier = Modifier.padding(start = 16.dp),
            onClick = { /*TODO*/ }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Участники")
                Text(text = "${room.participants} ${pluralStringResource(id = R.plurals.participants, count = room.participants)}")
            }
        }
    }

    @Composable
    private fun ChatWindow() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            verticalArrangement = remember {
                object : Arrangement.Vertical {
                    override fun Density.arrange(
                        totalSize: Int,
                        sizes: IntArray,
                        outPositions: IntArray
                    ) {
                        var currentOffset = 0
                        sizes.forEachIndexed { index, size ->
                            if (index == sizes.lastIndex) {
                                outPositions[index] = totalSize - size
                            } else {
                                outPositions[index] = currentOffset
                                currentOffset += size
                            }
                        }
                    }
                }
            }
        ) {
            items(chatMessages) { chatMessage ->
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    text = "${chatMessage.sender}   ${chatMessage.text}"
                )
            }

            item {
                ReviewInput()
            }
        }
    }

    @Composable
    fun ReviewInput() {
        var reviewText by rememberSaveable { mutableStateOf("") }
        var showError by rememberSaveable { mutableStateOf(false) }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = reviewText,
            onValueChange = { reviewText = it },
            placeholder = { Text(text = "Введите сообщение") },
            isError = showError,
            supportingText = {
                if (showError)
                    Text(
                        text = "Сообщение не может быть пустым",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.error
                    )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (reviewText.isNotEmpty()) {

                            reviewText = ""
                            showError = false
                        } else showError = true
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Send, contentDescription = "Отправить сообщение")
                }
            }
        )
    }
}
