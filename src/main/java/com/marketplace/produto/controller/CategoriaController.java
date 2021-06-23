package com.marketplace.produto.controller;
import com.marketplace.produto.model.Categoria;
import com.marketplace.produto.model.dto.CategoriaDTO;
import com.marketplace.produto.repository.CategoriaRepository;
import com.marketplace.produto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.List;
import java.net.URI;



@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;



    @GetMapping
    public ResponseEntity list(){
        List<CategoriaDTO> categorias = categoriaService.list();
        return ResponseEntity.ok(categorias);
    }
    @GetMapping("{id}")
    public ResponseEntity buscar(@PathVariable Long id){
          return categoriaRepository.findById(id)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping
    public ResponseEntity atualizarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        categoriaService.atualizarCategoria(categoriaDTO);
        return ResponseEntity.notFound().build();

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity salvar(@RequestBody CategoriaDTO categoriaDTO){
        Categoria categorias = categoriaService.salvar(categoriaDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(categorias.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    @DeleteMapping
    public ResponseEntity remover(@PathVariable Long id){
        categoriaService.remover(id);
        return ResponseEntity.notFound().build();
    }

}
