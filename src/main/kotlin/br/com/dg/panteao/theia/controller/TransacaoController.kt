package br.com.dg.panteao.theia.controller

import br.com.dg.panteao.theia.service.TransacaoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/transacao")
class TransacaoController(
    val transacaoService: TransacaoService
) {

    @PostMapping("/upload")
    fun upload(@RequestParam("file") file: MultipartFile) {
        transacaoService.upload(file)
    }

    @PostMapping("/uploadMultiple")
    fun upload(@RequestParam("file") files: Array<MultipartFile>) {
        transacaoService.upload(files)
    }
}
