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
public class Produto extends EntidadeBase {


    private String descricao;

    private String valor;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name="produtoCategorias",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name="categoria_id"))
    private List<Categoria> categorias = new ArrayList<>() ;







}
