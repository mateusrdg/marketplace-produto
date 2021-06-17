package com.marketplace.produto.model.dto;

import com.marketplace.produto.model.Categoria;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDTO {

     private Long id;
     private String descricao;




}
