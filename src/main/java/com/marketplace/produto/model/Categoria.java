package com.marketplace.produto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categoriaProduto")
public class Categoria extends EntidadeBase{


    private String descricao;

    @ManyToMany(mappedBy = "categorias" )
    private List<Produto> produtos = new ArrayList<>();











}
