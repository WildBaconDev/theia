package br.com.dg.panteao.theia.repository

import br.com.dg.panteao.theia.model.Importacao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImportacaoRepository: JpaRepository<Importacao, Long>