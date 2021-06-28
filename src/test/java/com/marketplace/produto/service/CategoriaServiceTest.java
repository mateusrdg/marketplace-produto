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
    public void deveBuscarCategorias(){
        Long id = 1L;
        CategoriaDTO categoriaDTO = getCategoriaDTO();

        Mockito.when(categoriaRepository.findById(id)).thenReturn(getCategoria());
        Mockito.when(modelMapper.map(any(),any())).thenReturn(categoriaDTO);

        CategoriaDTO response = categoriaService.buscar(id);

        Assertions.assertEquals("Categoria", response.getDescricao());
    }

    @Test
    public void deveLancarExecaoQuandoCategoriaNaoEncontrada(){
        Long id = 1L;

        Mockito.when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        try {
            categoriaService.buscar(id);
            Assertions.fail();
        } catch (RegistroNaoEncontradoException e) {
            Assertions.assertEquals("categoria nao encontrada", e.getMessage());
        }

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