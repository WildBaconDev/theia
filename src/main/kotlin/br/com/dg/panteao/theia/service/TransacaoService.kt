package br.com.dg.panteao.theia.service

import br.com.dg.panteao.theia.model.Transacao
import br.com.dg.panteao.theia.util.parseCSV
import org.apache.commons.csv.CSVFormat
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader

@Service
class TransacaoService {

    fun upload(file: MultipartFile) {


        val transacoes = parseCSV<Transacao>(CSVFormat.DEFAULT, InputStreamReader(file.inputStream))
        transacoes.forEach(System.out::println)


    }

    fun upload(files: Array<MultipartFile>) {
        files.forEach(this::upload)
    }

}