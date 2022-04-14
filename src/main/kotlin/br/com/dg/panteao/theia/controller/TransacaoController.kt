package br.com.dg.panteao.theia.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/transacao")
class TransacaoController {

    @PostMapping("/upload")
    fun importar(@RequestParam("file") file: MultipartFile) {
        println("""
                ${file.originalFilename}
                ${file.size}    
            """.trimIndent())
    }

    @PostMapping("/uploadMultiple")
    fun importar(@RequestParam("file") files: Array<MultipartFile>) {
        files.forEach { file ->
            println("""
                ${file.originalFilename}
                ${file.size}    
            """.trimIndent())
        }
    }
}
