package ru.otus.md5

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Md5Application

fun main(args: Array<String>) {
    runApplication<Md5Application>(*args)
}
