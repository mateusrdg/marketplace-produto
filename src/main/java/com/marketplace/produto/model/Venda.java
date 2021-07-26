package com.marketplace.produto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marketplace.produto.model.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "venda")
    private List<Item> itens = new ArrayList<>();
    
    public  double getValorTotal(){
        double soma =0.0;
        for(Item p : itens){
            soma = soma + p.getValor();
        }
        return soma;

    }









}
