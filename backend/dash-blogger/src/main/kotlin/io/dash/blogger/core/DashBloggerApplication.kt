package io.dash.blogger.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DashBloggerApplication

fun main(args: Array<String>) {
    runApplication<DashBloggerApplication>(*args)
}
