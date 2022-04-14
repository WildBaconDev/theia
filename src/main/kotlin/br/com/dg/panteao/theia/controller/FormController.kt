package br.com.dg.panteao.theia.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/form")
class FormController {

    @GetMapping
    fun form() = "form"

}
