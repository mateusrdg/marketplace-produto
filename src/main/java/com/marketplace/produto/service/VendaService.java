package com.marketplace.produto.service;

import com.marketplace.produto.exceptions.RegistroNaoEncontradoException;
import com.marketplace.produto.model.Venda;
import com.marketplace.produto.model.constantes.Excecoes;
import com.marketplace.produto.model.dto.VendaDTO;
import com.marketplace.produto.repository.VendaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    private final ModelMapper modelMapper;

    public VendaService(VendaRepository vendaRepository,ModelMapper modelMapper) {
        this.vendaRepository = vendaRepository;
        this.modelMapper = modelMapper;
    }

    public List<VendaDTO> list(){
       List<Venda> lista = vendaRepository.findAll();
       return lista.stream().map(x -> modelMapper.map(x ,VendaDTO.class)).collect(Collectors.toList());
    }

    public VendaDTO buscar(Long id){
        Venda venda = vendaRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(Excecoes.VENDA_NAO_ENCONTRADA));
        VendaDTO vendaDTO = modelMapper.map(venda,VendaDTO.class);
        return vendaDTO;
    }

    public Venda salvar(VendaDTO vendaDTO){
        Venda venda = modelMapper.map(vendaDTO,Venda.class);
        return vendaRepository.save(venda);
    }

    public  Venda atualizar(VendaDTO vendaDTO){
        Venda venda = modelMapper.map(vendaDTO,Venda.class);
        return vendaRepository.save(venda);
    }
    public void remover(Long id) {
        vendaRepository.deleteById(id);
    }




}
