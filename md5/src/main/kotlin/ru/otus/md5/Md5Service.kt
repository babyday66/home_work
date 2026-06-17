package ru.otus.md5

import org.springframework.stereotype.Service
import java.io.InputStream
import java.security.MessageDigest

@Service
class Md5Service {
    fun calculate(inputStream: InputStream): String {
        val digest = MessageDigest.getInstance("MD5")
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)

        inputStream.use { stream ->
            while (true) {
                val bytesRead = stream.read(buffer)
                if (bytesRead == -1) break
                digest.update(buffer, 0, bytesRead)
            }
        }

        return digest.digest().joinToString(separator = "") { byte -> "%02x".format(byte) }
    }

    private companion object {
        const val DEFAULT_BUFFER_SIZE = 8192
    }
}
