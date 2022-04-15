package br.com.dg.panteao.theia.service

import br.com.dg.panteao.theia.model.Importacao
import br.com.dg.panteao.theia.repository.TransacaoRepository
import br.com.dg.panteao.theia.model.Transacao
import org.apache.commons.csv.CSVFormat
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.InputStreamReader
import java.time.LocalDateTime

@Service
class TransacaoService(
    val transacaoRepository: TransacaoRepository,
    val importacaoService: ImportacaoService,
) {

    fun upload(file: MultipartFile): List<Transacao> {

        val transacoes = parseCSV(file)

        val upload = Importacao(
            dataHoraImportacao = LocalDateTime.now(),
            dataTransacao = transacoes[0].dataHoraTransacao.toLocalDate(),
            transacoes = transacoes
        )

        transacoes.forEach { it.upload = upload }

        importacaoService.salvar(upload)
        transacaoRepository.saveAll(transacoes)

        return transacoes
    }

    private fun parseCSV(file: MultipartFile): List<Transacao> {
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
        return transacoes
    }

    fun upload(files: Array<MultipartFile>): Unit = files.forEach(this::upload)

}