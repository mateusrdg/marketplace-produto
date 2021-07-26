package com.marketplace.produto.service;

import com.marketplace.produto.exceptions.RegistroNaoEncontradoException;
import com.marketplace.produto.model.Item;
import com.marketplace.produto.model.constantes.Excecoes;
import com.marketplace.produto.model.dto.ItemDTO;
import com.marketplace.produto.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository ,ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
    }


    public List<ItemDTO> buscaTodos(){
        List<Item> list = itemRepository.findAll();
        return list.stream().map(x ->modelMapper.map(x, ItemDTO.class)).collect(Collectors.toList());
    }

    public ItemDTO buscarId(Long id){
         Item item = itemRepository.findById(id).orElseThrow(() -> new RegistroNaoEncontradoException(Excecoes.ITEM_NAO_ENCONTRADO));
         ItemDTO itemDTO = modelMapper.map(item,ItemDTO.class);
         return  itemDTO;
    }

    public Item inserir(ItemDTO itemDTO){
        Item item = modelMapper.map(itemDTO,Item.class);
        return itemRepository.save(item);

    }

    public Item atualzar(ItemDTO itemDTO){
        Item item= modelMapper.map(itemDTO,Item.class);
        return itemRepository.save(item);
    }

    public void deletar(Long id){
        itemRepository.deleteById(id);
    }




}

