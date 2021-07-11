package com.marketplace.produto.controller;

import com.marketplace.produto.model.Venda;
import com.marketplace.produto.model.dto.VendaDTO;
import com.marketplace.produto.repository.VendaRepository;
import com.marketplace.produto.service.VendaService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

     @Autowired
     private VendaRepository vendaRepository;
     @Autowired
     private VendaService vendaService;

     @GetMapping
     public ResponseEntity listas(){
          List<VendaDTO> vendas = vendaService.list();
          return ResponseEntity.ok(vendas);
     }

     @GetMapping("{id}")
     public ResponseEntity<Venda> buscarId(@PathVariable Long id){
          return vendaRepository.findById(id)
                  .map(ResponseEntity :: ok)
                  .orElse(ResponseEntity.notFound().build());
     }
     @PostMapping
     public ResponseEntity salvar(@RequestBody VendaDTO vendaDTO){
        Venda vendas = vendaService.salvar(vendaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(vendas.getId()).toUri();
        return ResponseEntity.created(uri).build();
     }
     @PutMapping
     public  ResponseEntity atualizar(@RequestBody VendaDTO vendaDTO){
          vendaService.atualizar(vendaDTO);
          return ResponseEntity.notFound().build();
     }
     @DeleteMapping
     public ResponseEntity remover(@PathVariable Long id){
          vendaService.remover(id);
          return ResponseEntity.notFound().build();
     }



}
