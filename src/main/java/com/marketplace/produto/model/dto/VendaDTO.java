package com.marketplace.produto.model.dto;


import com.marketplace.produto.model.Item;
import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendaDTO  {

    private Long id;

    private String nome;

    private int valorTotal;

    private List<Item> itens;


}