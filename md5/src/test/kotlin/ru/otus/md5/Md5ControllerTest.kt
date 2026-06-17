package ru.otus.md5

import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.multipart
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class Md5ControllerTest(
    @Autowired private val mockMvc: MockMvc,
) {
    @Test
    fun `returns md5 for uploaded file`() {
        val file = MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "hello world".toByteArray())

        mockMvc.multipart("/api/v1/md5") {
            file(file)
        }
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.md5", equalTo("5eb63bbbe01eeed093cb22bb8f5acdc3")))
            .andExpect(jsonPath("$.error").doesNotExist())
    }

    @Test
    fun `rejects empty file`() {
        val file = MockMultipartFile("file", "empty.txt", MediaType.TEXT_PLAIN_VALUE, ByteArray(0))

        mockMvc.multipart("/api/v1/md5") {
            file(file)
        }
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error", equalTo("File is empty")))
            .andExpect(jsonPath("$.md5").doesNotExist())
    }
}
