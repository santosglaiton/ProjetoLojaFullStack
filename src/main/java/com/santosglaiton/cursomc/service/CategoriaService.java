package com.santosglaiton.cursomc.service;

import com.santosglaiton.cursomc.domain.CategoriaDomain;
import com.santosglaiton.cursomc.repositories.CategoriaRepository;
import com.santosglaiton.cursomc.service.exceptions.ObjectNotFoundException;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    public CategoriaDomain find(Integer id){

        Optional<CategoriaDomain> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id:" + id + ", Tipo: " + CategoriaDomain.class.getName()));
    }

    public CategoriaDomain insert(CategoriaDomain obj){
        obj.setId(null);
        return repo.save(obj);
    }

    public CategoriaDomain update(CategoriaDomain obj){
        find(obj.getId());
        return repo.save(obj);
    }

}
