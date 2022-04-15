package br.com.dg.panteao.theia

import br.com.dg.panteao.theia.model.Transacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransacaoRepository: JpaRepository<Transacao, Long>