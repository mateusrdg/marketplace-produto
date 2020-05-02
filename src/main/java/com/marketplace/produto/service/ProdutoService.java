package com.marketplace.produto.service;

import com.marketplace.produto.exceptions.RegistroNaoEncontradoException;
import com.marketplace.produto.model.Produto;
import com.marketplace.produto.model.constantes.Excecoes;
import com.marketplace.produto.model.dto.ProdutoDTO;
import com.marketplace.produto.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ModelMapper modelMapper;

    public ProdutoService(ProdutoRepository produtoRepository, ModelMapper modelMapper){
        this.produtoRepository = produtoRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProdutoDTO> buscarTodos() {
        List<Produto> list = produtoRepository.findAll();
        return list.stream().map(x -> modelMapper.map(x, ProdutoDTO.class)).collect(Collectors.toList());
    }

    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(Excecoes.PRODUTO_NAO_ENCONTRADO));
        ProdutoDTO produtoDTO = modelMapper.map(produto, ProdutoDTO.class);
        return produtoDTO;
    }

    public Produto inserir(ProdutoDTO produtoDTO) {
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        associarDados(produto);
        return produtoRepository.save(produto);
    }

    public void atualizar(ProdutoDTO produtoDTO) {
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        associarDados(produto);
        produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }
    private void associarDados(Produto produto){
    //    produto.getEnderecos().forEach(endereco-> endereco.setUsuario(produto));
    }
}

