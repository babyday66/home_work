package ru.otus.md5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Md5ServiceTest {
    private val md5Service = Md5Service()

    @Test
    fun `calculates md5 hash for input stream`() {
        val result = md5Service.calculate("hello world".byteInputStream())

        assertThat(result).isEqualTo("5eb63bbbe01eeed093cb22bb8f5acdc3")
    }
}
