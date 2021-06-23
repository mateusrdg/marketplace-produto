package com.marketplace.produto.controller;
import com.marketplace.produto.model.Produto;
import com.marketplace.produto.model.dto.ProdutoDTO;
import com.marketplace.produto.repository.ProdutoRepository;
import com.marketplace.produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity buscarTodos(){
        List<ProdutoDTO> produtos = produtoService.buscarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity buscarPorId(@PathVariable Long id){
          return produtoRepository.findById(id)
                 .map(ResponseEntity::ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity inserir(@RequestBody ProdutoDTO produtoDTO){
        Produto produto = produtoService.inserir(produtoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping()
    public ResponseEntity atualizar(@RequestBody ProdutoDTO produtoDTO){
        produtoService.atualizar(produtoDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
