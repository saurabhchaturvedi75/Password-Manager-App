package com.example.passwordmanagerapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PasswordViewModel(private val repository: PasswordRepository) : ViewModel() {
    val passwords = repository.getAllPasswords()
    private val _editingPassword = MutableStateFlow<Password?>(null)
    val editingPassword: StateFlow<Password?> get() = _editingPassword

    fun addPassword(accountType: String, username: String, password: String) {
        viewModelScope.launch {
            val encryptedPassword = EncryptionHelper.encrypt(password)
            repository.addPassword(Password(accountType = accountType, username = username, encryptedPassword = encryptedPassword))
        }
    }

    fun updatePassword(password: Password) {
        viewModelScope.launch {
            repository.updatePassword(password)
        }
    }

    fun deletePassword(password: Password) {
        viewModelScope.launch {
            repository.deletePassword(password)
        }
    }

    fun setEditingPassword(password: Password?) {
        _editingPassword.value = password
    }

    fun clearEditingPassword() {
        _editingPassword.value = null
    }
}
