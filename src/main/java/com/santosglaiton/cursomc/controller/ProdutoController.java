package com.santosglaiton.cursomc.controller;

import com.santosglaiton.cursomc.dto.ProdutoDTO;
import com.santosglaiton.cursomc.controller.utils.URL;
import com.santosglaiton.cursomc.domain.ProdutoDomain;
import com.santosglaiton.cursomc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDomain> find(@PathVariable Integer id ){

        ProdutoDomain obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
                                                     @RequestParam(value = "categorias", defaultValue = "") String categorias,
                                                     @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction ){
        String nomeDecoded = URL.decodeParam(nome);
        List<Integer> ids= URL.decodeIntList(categorias);
        Page<ProdutoDomain> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

}
