package com.marketplace.produto.service;

import com.marketplace.produto.exceptions.RegistroNaoEncontradoException;
import com.marketplace.produto.model.Venda;
import com.marketplace.produto.model.dto.VendaDTO;
import com.marketplace.produto.repository.VendaRepository;
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
public class VendaServiceTest {

    @InjectMocks
    private VendaService vendaService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private VendaRepository vendaRepository;

    @Test
    public void deveBuscarVenda(){
        Long id=1L;
        VendaDTO vendaDTO = getVendaDTO();
        vendaDTO.setNome("jose");
        Mockito.when(vendaRepository.findById(id)).thenReturn(getVenda());
        Mockito.when(modelMapper.map(any(),any())).thenReturn(vendaDTO);
        VendaDTO venda1 = vendaService.buscarId(id);
        Assertions.assertEquals("jose",venda1.getNome());
    }

    @Test
    public void deveLancaVendaExcecao(){
        Long id = 1L;
        Mockito.when(vendaRepository.findById(id)).thenReturn(Optional.empty());

        try{
            vendaService.buscarId(id);
            Assertions.fail();
        } catch(RegistroNaoEncontradoException e ){
            Assertions.assertEquals("venda nao encontrada",e.getMessage());

        }

    }

    @Test
    public void listarBuscas(){
        List<Venda> vendas = new ArrayList<>();
        Venda venda1= new Venda();
        vendas.add(venda1);
        venda1.setNome("jose");

        Mockito.when(vendaRepository.findAll()).thenReturn(vendas);
        Mockito.when(modelMapper.map(any(),any())).thenReturn(getVendaDTO());

        List<VendaDTO> vendaDTOS = vendaService.list();

        Assertions.assertEquals(1, vendaDTOS.size());

    }
    @Test
    public void salvarVenda(){
        Venda venda = new Venda();
        venda.setNome("joao");
        venda.setId(1L);



        Mockito.when(vendaRepository.save(venda)).thenReturn(venda);
        Mockito.when(modelMapper.map(any(),any())).thenReturn(venda);

        Venda venda1 = vendaService.inserir(getVendaDTO());
        Assertions.assertEquals("joao",venda1.getNome());
        Assertions.assertEquals(1,venda1.getId());
    }

    @Test
    public void atualizaVenda() {
        Venda venda = new Venda();
        venda.getId();

        Mockito.when(vendaRepository.save(venda)).thenReturn(venda);
        Mockito.when(modelMapper.map(any(),any())).thenReturn(venda);

        vendaService.atualizar(getVendaDTO());

        Mockito.verify(vendaRepository).save(venda);

    }

    @Test
    public void delete(){
         vendaService.deletar(1L);
         Mockito.verify(vendaRepository).deleteById(1L);

    }

     private VendaDTO getVendaDTO(){
        VendaDTO vendaDTO = new VendaDTO();
        vendaDTO.setNome("jose");
        return  vendaDTO;
     }

     private Optional<Venda> getVenda(){
        Venda venda = new Venda();
        venda.setNome("jose");
        venda.getValorTotal();
        return Optional.of(venda);
     }


}
