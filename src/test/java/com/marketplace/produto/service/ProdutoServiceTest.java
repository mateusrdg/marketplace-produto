package com.marketplace.produto.service;


import com.marketplace.produto.exceptions.RegistroNaoEncontradoException;
import com.marketplace.produto.model.Produto;
import com.marketplace.produto.model.dto.ProdutoDTO;
import com.marketplace.produto.repository.ProdutoRepository;
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
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ProdutoRepository produtoRepository;

    @Test
    public void listarbuscas(){
        List<Produto> produtos = new ArrayList<>();
        Produto produto = new Produto();
        produtos.add(produto);
        produto.setDescricao("Lapis");

        Mockito.when(produtoRepository.findAll()).thenReturn(produtos);
        Mockito.when(modelMapper.map(any(),any())).thenReturn(getProdutoDTO());

        List<ProdutoDTO> produtoDTO = produtoService.buscarTodos();

        Assertions.assertEquals(1,produtoDTO.size());

    }

    @Test
    public void buscarPeloidproduto(){
        Long id = 1L;
        ProdutoDTO produtoDTO = getProdutoDTO();
        Mockito.when(produtoRepository.findById(id)).thenReturn(getProduto());
        Mockito.when(modelMapper.map(any(),any())).thenReturn(produtoDTO);

        ProdutoDTO produtoDTO1 = produtoService.buscarPorId(id);

        Assertions.assertEquals("Produto",produtoDTO1.getDescricao());

    }

    @Test
    public void BuscaExcecaoIdProduto(){
        Long id = 1L;
        Mockito.when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        try{
            produtoService.buscarPorId(id);
            Assertions.fail();
        }catch (RegistroNaoEncontradoException e){
            Assertions.assertEquals("Produto nao encontrado", e.getMessage());
        }
    }


    @Test
    public void inseriProduto(){
       Produto produto = new Produto();
       produto.setDescricao("Lapis");
       Mockito.when(modelMapper.map(any(),any())).thenReturn(produto);
       Mockito.when(produtoRepository.save(produto)).thenReturn(produto);

       Produto produto1 = produtoService.inserir(getProdutoDTO());


       Assertions.assertEquals("Lapis" ,produto1.getDescricao());

    }

    @Test
    public void atualizaProduto(){
        Produto produto = new Produto();
        produto.setDescricao("Lápis");
        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);
        Mockito.when(modelMapper.map(any(),any())).thenReturn(produto);

        produtoService.atualizar(getProdutoDTO());
        Mockito.verify(produtoRepository).save(produto);
    }

   @Test
    public void delete() {

       produtoService.deletar(1L);

       Mockito.verify(produtoRepository).deleteById(1L);

   }


   private  ProdutoDTO getProdutoDTO(){
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setDescricao("Produto");
        return  produtoDTO;

   }
   private Optional<Produto> getProduto(){
        Produto produto = new Produto();
        produto.setDescricao("produto");
        return Optional.of(produto);

   }


}
