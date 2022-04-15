package br.com.dg.panteao.theia.service

import br.com.dg.panteao.theia.TransacaoRepository
import br.com.dg.panteao.theia.model.Transacao
import org.apache.commons.csv.CSVFormat
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader
import java.time.LocalDateTime

@Service
class TransacaoService(
    val transacaoRepository: TransacaoRepository
) {

    fun upload(file: MultipartFile) {

        val transacoes = CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader(
            "banco_origem",
            "agencia_origem",
            "conta_origem",
            "banco_destino",
            "agencia_destino",
            "conta_destino",
            "valor_transacao",
            "data_hora_transacao"
        ).build().parse(InputStreamReader(file.inputStream)).map {
                Transacao(
                    bancoOrigem = it.get("banco_origem"),
                    agenciaOrigem = it.get("agencia_origem"),
                    contaOrigem = it.get("conta_origem"),
                    bancoDestino = it.get("banco_destino"),
                    agenciaDestino = it.get("agencia_destino"),
                    contaDestino = it.get("conta_destino"),
                    valorTransacao = it.get("valor_transacao"),
                    dataHoraTransacao = LocalDateTime.parse(it.get("data_hora_transacao"))
                )
            }

        transacoes.forEach(System.out::println)

        transacaoRepository.saveAll(transacoes)
    }

    fun upload(files: Array<MultipartFile>): Unit = files.forEach(this::upload)

}