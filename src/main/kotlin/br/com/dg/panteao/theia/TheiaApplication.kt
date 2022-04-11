package br.com.dg.panteao.theia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TheiaApplication

fun main(args: Array<String>) {
	runApplication<TheiaApplication>(*args)
}
