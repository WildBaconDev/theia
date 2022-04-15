package br.com.dg.panteao.theia.service

import br.com.dg.panteao.theia.model.Importacao
import br.com.dg.panteao.theia.repository.ImportacaoRepository
import org.springframework.stereotype.Service

@Service
class ImportacaoService(
    val importacaoRepository: ImportacaoRepository
) {

    fun salvar(upload: Importacao) = importacaoRepository.save(upload)

}
