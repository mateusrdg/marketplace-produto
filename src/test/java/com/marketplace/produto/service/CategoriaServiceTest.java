package com.marketplace.produto.service;

import com.marketplace.produto.exceptions.RegistroNaoEncontradoException;
import com.marketplace.produto.model.Categoria;
import com.marketplace.produto.model.dto.CategoriaDTO;
import com.marketplace.produto.repository.CategoriaRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Test
    public void deveBuscarCategoria() {
        Long id = 1L;
        CategoriaDTO categoriaDTO = getCategoriaDTO();
        Mockito.when(categoriaRepository.findById(id)).thenReturn(getCategoria());
        Mockito.when(modelMapper.map(any(), any())).thenReturn(categoriaDTO);

        CategoriaDTO response = categoriaService.buscar(id);

        Assertions.assertEquals("Categoria", response.getDescricao());
    }

    @Test
    public void deveLancarExecaoQuandoCategoriaNaoEncontrada() {
        Long id = 1L;

        Mockito.when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        try {
            categoriaService.buscar(id);
            Assertions.fail();
        } catch (RegistroNaoEncontradoException e) {
            Assertions.assertEquals("categoria nao encontrada", e.getMessage());
        }

    }

    @Test
    public void listarCategoria() {

        List<Categoria> categorias = new ArrayList<>();
        Categoria categoria = new Categoria();
        categorias.add(categoria);
        categoria.setDescricao("Material Escolar");

        Categoria categoria1 = new Categoria();
        categorias.add(categoria1);
        categoria1.setDescricao("Acessorios de Informatica");

        Mockito.when(categoriaRepository.findAll()).thenReturn(categorias);
        Mockito.when(modelMapper.map(any(), any())).thenReturn(getCategoriaDTO());


        List<CategoriaDTO> categoriaDto = categoriaService.list();

        Assertions.assertEquals(2, categoriaDto.size());

    }

    @Test
    public void salvarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setDescricao("Material Escolar");
        Mockito.when(modelMapper.map(any(), any())).thenReturn(categoria);
        Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);

        Categoria categoria1 = categoriaService.salvar(getCategoriaDTO());

        Assertions.assertEquals("Material Escolar", categoria1.getDescricao());

    }

    @Test
    public void atualizaCategoria() {
        Categoria categoria = new Categoria();
        categoria.setDescricao("Material Escolar");
        Mockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);
        Mockito.when(modelMapper.map(any(), any())).thenReturn(categoria);

        categoriaService.atualizarCategoria(getCategoriaDTO());


        Mockito.verify(categoriaRepository).save(categoria);
    }

    @Test
    public void deletar() {
        categoriaService.remover(1L);
        Mockito.verify(categoriaRepository).deleteById(1L);
    }

    private CategoriaDTO getCategoriaDTO() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setDescricao("Categoria");
        return categoriaDTO;
    }

    private Optional<Categoria> getCategoria() {
        Categoria categoria = new Categoria();
        categoria.setDescricao("categoria");
        return Optional.of(categoria);
    }


}