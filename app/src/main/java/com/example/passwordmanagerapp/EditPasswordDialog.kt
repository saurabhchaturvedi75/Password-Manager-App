package com.example.passwordmanagerapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun EditPasswordDialog(viewModel: PasswordViewModel, onDismiss: () -> Unit) {
    val editingPassword by viewModel.editingPassword.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    var accountType by remember { mutableStateOf(editingPassword?.accountType ?: "") }
    var username by remember { mutableStateOf(editingPassword?.username ?: "") }
    var passwordVisible by remember { mutableStateOf(false) }

    var newEncryptedPassword = editingPassword?.encryptedPassword
    val newDecryptedPassword = newEncryptedPassword?.let { EncryptionHelper.decrypt(it) }.toString()
    var passwordText by remember { mutableStateOf(newEncryptedPassword ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Password") },
        text = {
            Column {

                OutlinedTextField(
                    value = accountType,
                    onValueChange = {
                        accountType = it
                    },
                    label = { Text("Account Type") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(8.dp))
//
                OutlinedTextField(
                    value = passwordText,
                    onValueChange = { passwordText = it },
                    label = { Text("Password") },
                    singleLine = true,
          //                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    trailingIcon = {
                        val image =
                            if (!passwordVisible) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp
                        IconButton(
                            onClick = {
                                passwordVisible = !passwordVisible

                                passwordText =
                                    if (passwordVisible) newDecryptedPassword else newEncryptedPassword.toString()

//
                            }

                        ) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    },
                    enabled = passwordVisible
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                coroutineScope.launch {
                    editingPassword?.let {
                        viewModel.updatePassword(
                            it.copy(
                                accountType = accountType,
                                username = username,
                              //  encryptedPassword = passwordText
                                encryptedPassword = EncryptionHelper.encrypt(passwordText)
                            )
                        )
                    }
                    onDismiss()
                }
            }) {
                Text("Update")
            }
        },
        dismissButton = {
            Button(onClick = {
                coroutineScope.launch {
                    editingPassword?.let {
                        viewModel.deletePassword(it)
                    }
                    onDismiss()
                }
            }) {
                Text("Delete")
            }
        }
    )
}
