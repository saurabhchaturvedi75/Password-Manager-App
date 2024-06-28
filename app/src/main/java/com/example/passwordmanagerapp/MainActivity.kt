package com.example.passwordmanagerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.passwordmanagerapp.ui.theme.PasswordManagerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordManagerAppTheme {
                val appDatabase = AppDatabase.getDatabase(this)
                val passwordRepository = PasswordRepository(appDatabase.passwordDao())

                val viewModelFactory = PasswordViewModelFactory(passwordRepository)
                val viewModel: PasswordViewModel = viewModel(
                    factory = viewModelFactory
                )

                PasswordManagerApp(viewModel)
            }
        }
    }
}
