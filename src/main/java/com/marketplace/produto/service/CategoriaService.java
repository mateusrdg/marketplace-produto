package com.marketplace.produto.service;


import com.marketplace.produto.model.Categoria;
import com.marketplace.produto.model.dto.CategoriaDTO;
import com.marketplace.produto.model.dto.ProdutoDTO;
import com.marketplace.produto.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    private  ModelMapper modelMapper;

    public CategoriaService(CategoriaRepository categoriaRepository,ModelMapper modelMapper) {
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;

    }

    public List<CategoriaDTO> list() {
        List<Categoria> listar = categoriaRepository.findAll();
        return listar.stream().map(x -> modelMapper.map(x, CategoriaDTO.class)).collect(Collectors.toList());
    }


    public Categoria salvar(CategoriaDTO categoriaDTO) {
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        associarDados(categoria);
        return categoriaRepository.save(categoria);

    }

    public void atualizarCategoria(CategoriaDTO categoriaDTO){
        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);
        associarDados(categoria);
        categoriaRepository.save(categoria);
    }

    public void remover(Long id){
        categoriaRepository.deleteById(id);
    }

    private void associarDados(Categoria categoria) {

    }
}
