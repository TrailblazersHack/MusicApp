package ru.trailblazers.musicapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import ru.trailblazers.musicapp.R

/**
 * @author nvoxel
 */
class AuthScreen : Screen {

    @Composable
    override fun Content() {
        var signIn by rememberSaveable { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 32.dp),
                text = "Music App",
                style = MaterialTheme.typography.headlineLarge
            )

            CredentialsCard(signIn)

            OutlinedButton(
                modifier = Modifier.padding(top = 32.dp),
                onClick = { signIn = !signIn }
            ) {
                Text(text = if (signIn) "Регистрация" else "Вход")
            }
        }
    }

    @Composable
    private fun CredentialsCard(signIn: Boolean) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val navigator = LocalNavigator.current

            var nickname by rememberSaveable { mutableStateOf("") }
            var email by rememberSaveable { mutableStateOf("") }
            var password by rememberSaveable { mutableStateOf("") }
            var passwordVisible by rememberSaveable { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .padding(all = 32.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!signIn) {
                    OutlinedTextField(
                        modifier = Modifier.padding(all = 8.dp),
                        value = nickname,
                        onValueChange = { text -> nickname = text },
                        label = { Text("Никнейм") },
                        maxLines = 1
                    )
                }

                OutlinedTextField(
                    modifier = Modifier.padding(all = 8.dp),
                    value = email,
                    onValueChange = { text -> email = text },
                    label = { Text("Почта") },
                    maxLines = 1
                )

                OutlinedTextField(
                    modifier = Modifier.padding(all = 8.dp),
                    value = password,
                    onValueChange = { text -> password = text },
                    label = { Text("Пароль") },
                    maxLines = 1,
                    visualTransformation =
                    if (passwordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.VisibilityOff
                        else Icons.Filled.Visibility

                        val description =
                            if (passwordVisible) "Скрыть пароль"
                            else "Показать пароль"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    }
                )

                Button(
                    modifier = Modifier.padding(all = 8.dp),
                    onClick = {
                        navigator?.replace(MainScreen())
                    }
                ) {
                    Text(text = if (signIn) "Войти" else "Зарегистрироваться")
                }

                if (signIn) OauthSection()
            }
        }
    }

    @Composable
    private fun OauthSection() {
        Column(
            Modifier.padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Войти с помощью:")

            IconButton(
                modifier = Modifier.size(size = 64.dp),
                onClick = { }
            ) {
                Icon(
                    modifier = Modifier.size(size = 48.dp),
                    painter = painterResource(id = R.drawable.vk),
                    contentDescription = "VK",
                    tint = Color.Unspecified,
                )
            }
        }
    }
}
