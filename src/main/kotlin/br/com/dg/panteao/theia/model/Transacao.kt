package br.com.dg.panteao.theia.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
data class Transacao(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
    val bancoOrigem: String,
    val agenciaOrigem: String,
    val contaOrigem: String,
    val bancoDestino: String,
    val agenciaDestino: String,
    val contaDestino: String,
    val valorTransacao: String,
    val dataHoraTransacao: LocalDateTime,
)