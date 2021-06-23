package com.marketplace.produto.model.dto;
import com.marketplace.produto.model.Categoria;
import com.marketplace.produto.model.Produto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
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

    private List<Categoria> categorias;















}
