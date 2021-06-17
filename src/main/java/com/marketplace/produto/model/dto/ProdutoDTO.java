package com.marketplace.produto.model.dto;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {

    private Long id;

    private String descricao;

    private BigDecimal valor;

    private List<CategoriaDTO> categorias = new ArrayList<>();
}
