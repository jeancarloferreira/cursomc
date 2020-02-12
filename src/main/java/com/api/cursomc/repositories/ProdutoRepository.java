package com.api.cursomc.repositories;

import com.api.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface ProdutoRepository extends JpaRepository<Produto, Serializable> {
}
