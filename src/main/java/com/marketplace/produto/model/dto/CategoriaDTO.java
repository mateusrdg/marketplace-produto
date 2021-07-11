package com.marketplace.produto.model.dto;
import com.marketplace.produto.model.Categoria;
import com.marketplace.produto.model.Produto;
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

     private List<ProdutoDTO> produtos;


}
