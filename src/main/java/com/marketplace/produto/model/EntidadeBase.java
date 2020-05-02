package com.marketplace.produto.model;

import com.marketplace.produto.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntidadeBase extends EntidadeAPI {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private Integer status= Status.ATIVO.getValue();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Calendar insercao;

    @Column(updatable = false)
    private Calendar alteracao;

    @PrePersist
    private void prePersist(){
        this.insercao = Calendar.getInstance();
        uuid = UUID.randomUUID().toString();
    }
    @PreUpdate
    private void preUpdate(){
        this.alteracao = Calendar.getInstance();
    }

}
