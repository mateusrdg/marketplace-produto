package com.marketplace.produto.service;

import com.marketplace.produto.model.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.math.BigDecimal.valueOf;

@Service
public class DBTeste {

    @Autowired
    private ProdutoService service;

    public void testDatabase(){
        List<ProdutoDTO> produtos = Arrays.asList(ProdutoDTO.builder().descricao("lapis").valor(valueOf(1)).build(),
                ProdutoDTO.builder().descricao("caderno").valor(valueOf(14)).build(),
                ProdutoDTO.builder().descricao("cadeira").valor(valueOf(200)).build(),
                ProdutoDTO.builder().descricao("garrafa").valor(valueOf(7)).build(),
                ProdutoDTO.builder().descricao("teclado").valor(valueOf(157.5)).build(),
                ProdutoDTO.builder().descricao("mouse").valor(valueOf(100)).build());

        produtos.forEach(service::inserir);
    }
}
