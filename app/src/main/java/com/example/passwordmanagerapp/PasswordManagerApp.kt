
package com.example.passwordmanagerapp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordManagerApp(viewModel: PasswordViewModel) {
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Password Manager") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        HomeScreen(
            viewModel = viewModel,
            onEditPassword = { password ->
                viewModel.setEditingPassword(password)
                showEditDialog = true
            }
        )
Spacer(modifier = Modifier.padding(it))
        if (showAddDialog) {
            AddPasswordDialog(
                viewModel = viewModel,
                onDismiss = { showAddDialog = false }
            )
        }

        if (showEditDialog) {
            EditPasswordDialog(
                viewModel = viewModel,
                onDismiss = {
                    showEditDialog = false
                    viewModel.clearEditingPassword()
                }
            )
        }
    }
}
