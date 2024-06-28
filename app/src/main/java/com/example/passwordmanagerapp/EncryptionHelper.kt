
package com.example.passwordmanagerapp

import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec
import javax.crypto.spec.IvParameterSpec

object EncryptionHelper {
    private const val SECRET_KEY = "100" // Use a securely generated key
    private const val SALT = "100" // Use a securely generated salt
    private const val ITERATION_COUNT = 10000
    private const val KEY_LENGTH = 256
    private const val TRANSFORMATION = "AES/CBC/PKCS7Padding"
    private const val ALGORITHM = "AES"

    private fun generateKey(): SecretKey {
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(SECRET_KEY.toCharArray(), SALT.toByteArray(), ITERATION_COUNT, KEY_LENGTH)
        return SecretKeySpec(factory.generateSecret(spec).encoded, ALGORITHM)
    }

    private fun getCipher(mode: Int): Cipher {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = ByteArray(cipher.blockSize)
        val ivParams = IvParameterSpec(iv)
        cipher.init(mode, generateKey(), ivParams)
        return cipher
    }

    fun encrypt(data: String): String {
        val cipher = getCipher(Cipher.ENCRYPT_MODE)
        val encryptedValue = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
    }

    fun decrypt(data: String): String {
        val cipher = getCipher(Cipher.DECRYPT_MODE)
        val decodedValue = Base64.decode(data, Base64.DEFAULT)
        val decryptedValue = cipher.doFinal(decodedValue)
        return String(decryptedValue)
    }
}
