package com.marketplace.produto.controller;

import com.marketplace.produto.model.Item;
import com.marketplace.produto.model.dto.ItemDTO;
import com.marketplace.produto.repository.ItemRepository;
import com.marketplace.produto.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity lista() {
         List<ItemDTO> item = itemService.listas();
         return ResponseEntity.ok(item);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Item> buscaId(@PathVariable("id") Long id){
         return itemRepository.findById(id)
                 .map(ResponseEntity:: ok)
                 .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity <Item> salvar(@RequestBody ItemDTO itemDTO){
        Item item = itemService.salvar(itemDTO);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping
    public ResponseEntity atualizar(@RequestBody ItemDTO itemDTO){
        itemService.atualzar(itemDTO);
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping
    public ResponseEntity remover(@PathVariable Long id) {
        itemService.remover(id);
        return ResponseEntity.notFound().build();

    }
}
