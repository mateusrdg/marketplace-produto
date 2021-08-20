package com.marketplace.produto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends EntidadeBase {


    private int quantidade;

    private String produto;

    private int preco;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_venda", nullable =false)
    private Venda venda;
    
    public double getValor(){

        return quantidade * preco ;
    }






}
