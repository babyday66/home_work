package ru.otus.md5

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/md5")
class Md5Controller(
    private val md5Service: Md5Service,
) {
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun calculate(@RequestPart("file") file: MultipartFile): ResponseEntity<Md5Response> {
        if (file.isEmpty) {
            return ResponseEntity.badRequest().body(Md5Response(error = "File is empty"))
        }

        return ResponseEntity.ok(Md5Response(md5 = md5Service.calculate(file.inputStream)))
    }
}

data class Md5Response(
    val md5: String? = null,
    val error: String? = null,
)
