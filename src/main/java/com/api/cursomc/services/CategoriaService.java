package com.api.cursomc.services;

import com.api.cursomc.domain.Categoria;
import com.api.cursomc.repositories.CategoriaRepository;
import com.api.cursomc.services.exceptions.DataIntegrityException;
import com.api.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria find(Integer id){
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try{
            repo.deleteById(id);
        }catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }

    }
}
