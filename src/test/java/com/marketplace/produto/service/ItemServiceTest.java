package com.marketplace.produto.service;

import com.marketplace.produto.exceptions.RegistroNaoEncontradoException;
import com.marketplace.produto.model.Item;
import com.marketplace.produto.model.dto.ItemDTO;
import com.marketplace.produto.repository.ItemRepository;
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
public class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void deveBuscaItem(){
        Long id = 1L;
        ItemDTO itemDTO = getItemDTO();
        Mockito.when(itemRepository.findById(id)).thenReturn(getItem());
        Mockito.when(modelMapper.map(any(),any())).thenReturn(getItemDTO());

        ItemDTO itemDTO1 = itemService.buscarId(id);

        Assertions.assertEquals(1,itemDTO1.getQuantidade());


    }

    @Test
    public void deveLancarExcecaoItem(){
        Long id = 1L;
        Mockito.when(itemRepository.findById(id)).thenReturn(Optional.empty());

        try{
            itemService.buscarId(id);
            Assertions.fail();

        }catch(RegistroNaoEncontradoException e){
          Assertions.assertEquals("item nao encontrado" ,e.getMessage());
        }

    }

    @Test
    public void listaItem(){
        List<Item> item = new ArrayList<>();
        Item item1 = new Item();
        item.add(item1);
        item1.setProduto("caneta");

        Mockito.when(itemRepository.findAll()).thenReturn(item);
        Mockito.when(modelMapper.map(any(),any())).thenReturn(getItemDTO());

        List<ItemDTO> itemDTOS = itemService.buscaTodos();
        Assertions.assertEquals(1,itemDTOS.size());

    }

    @Test
    public void salvaItem(){
        Item item = new Item();
        item.setQuantidade(2);
        item.setPreco(4);

        Mockito.when(modelMapper.map(any(),any())).thenReturn(item);
        Mockito.when(itemRepository.save(item)).thenReturn(item);


        Item item1 = itemService.inserir(getItemDTO());

        Assertions.assertEquals(8,item1.getValor());

    }

    @Test
    public void atualizaItem(){
        Item item= new Item();
        item.setQuantidade(1);
        Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(modelMapper.map(any(), any())).thenReturn(item);

        itemService.atualzar(getItemDTO());
        Mockito.verify(itemRepository).save(item);

    }


    @Test
    public void deletar(){
        itemService.deletar(1L);
        Mockito.verify(itemRepository).deleteById(1L);

    }


    private ItemDTO getItemDTO(){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantidade(1);
        itemDTO.setPreco(2);

        return itemDTO;

    }

    private Optional<Item> getItem(){
        Item item = new Item();
        item.setQuantidade(1);
        item.setPreco(2);


        return Optional.of(item);
    }
}
