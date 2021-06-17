package com.marketplace.produto.repository;

import com.marketplace.produto.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria,Long>{




}
