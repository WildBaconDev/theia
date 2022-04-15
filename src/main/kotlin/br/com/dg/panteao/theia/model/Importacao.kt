package br.com.dg.panteao.theia.model

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
data class Importacao(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long? = null,
    val dataHoraImportacao: LocalDateTime,
    val dataTransacao: LocalDate,
    @OneToMany(mappedBy = "importacao") val transacoes: List<Transacao>? = null
)
