package com.marketplace.produto.model.dto;
import com.marketplace.produto.model.Categoria;
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






}
