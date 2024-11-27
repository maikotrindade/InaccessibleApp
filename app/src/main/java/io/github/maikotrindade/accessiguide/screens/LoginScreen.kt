package io.github.maikotrindade.accessiguide.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import io.github.maikotrindade.accessiguide.R

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberEmail by remember { mutableStateOf(false) }

    val isEmailValid = email.contains("@") && email.contains(".")

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.weather_logo),
                    contentDescription = "Image weather logo",
                    modifier = Modifier.size(192.dp)
                )
                TextField(
                    value = email,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = { email = it },
                    label = {
                        Text(
                            stringResource(R.string.login_screen_email_label)
                        )
                            },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    colors = TextFieldDefaults.colors().copy(
                        focusedIndicatorColor = if (!isEmailValid && email.isNotEmpty()) Color.Red else Color.Blue,
                        unfocusedIndicatorColor = if (!isEmailValid && email.isNotEmpty()) Color.Red else Color.Gray,
                )
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(stringResource(R.string.login_screen_password_label)) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Checkbox(
                        checked = rememberEmail,
                        onCheckedChange = { rememberEmail = it }
                    )
                    Text(
                        text = stringResource(R.string.login_screen_remember_my_email_label),
                        fontSize = 11.sp
                    )
                }
                Button(
                    onClick = { navController.navigate("cities") },
                ) {
                    Image(
                        painter = painterResource(R.drawable.log_in),
                        contentDescription = null,
                        modifier = Modifier.size(46.dp)
                    )
                }
            }
        }
    }
}