package org.irq3

import java.io.BufferedReader
import java.io.InputStreamReader

class TokenGetter {
    fun taketoken(source: String): String? {
        return this::class.java.getResourceAsStream(source)?.use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                reader.readLine()
            }
        }
    }
}