package br.com.dg.panteao.theia.model

import br.com.dg.panteao.theia.util.CSVHeader

data class Transacao(
    @CSVHeader("banco_origem") val bancoOrigem: String,
    @CSVHeader("agencia_origem") val agenciaOrigem: String,
    @CSVHeader("conta_origem") val contaOrigem: String,
    @CSVHeader("banco_destino") val bancoDestino: String,
    @CSVHeader("agencia_destino") val agenciaDestino: String,
    @CSVHeader("conta_destino") val contaDestino: String,
    @CSVHeader("valor_transacao") val valorTransacao: String,
    @CSVHeader("data_hora_transacao") val dataHoraTransacao: String,
)