package com.marketplace.produto.model.dto;

import lombok.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int quantidade;
    private int preco;
    private String produto;


}
