package com.marketplace.produto.model.dto;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDTO {

     private Long id;

     private String descricao;

     private List<CategoriaDTO> produtos;


}
