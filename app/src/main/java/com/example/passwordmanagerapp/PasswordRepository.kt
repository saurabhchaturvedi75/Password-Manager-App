
package com.example.passwordmanagerapp

import kotlinx.coroutines.flow.map

class PasswordRepository(private val dao: PasswordDao) {
    fun getAllPasswords() = dao.getAllPasswords()

    suspend fun addPassword(password: Password) = dao.insert(password)

    suspend fun updatePassword(password: Password) = dao.update(password)

    suspend fun deletePassword(password: Password) = dao.delete(password)

 }
