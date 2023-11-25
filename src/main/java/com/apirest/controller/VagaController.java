/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.controller;

import com.apirest.model.Vaga;
import com.apirest.service.VagaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin    
@RestController
@RequestMapping("/vaga")
public class VagaController {
        
    @Autowired
    VagaService service;
    
    
    @GetMapping("/get/list")
    public List<Vaga> getVagas(){
        return this.service.listagem();
    }
    
    @PostMapping("/add")
    public Vaga insereVaga(@RequestBody Vaga vaga){
        if(vaga != null){
            this.service.salva(vaga);
        }
        return null;
    }
    
    @PutMapping("/update")
    public Vaga atualiza(@RequestBody Vaga vaga){
        if(vaga.getId() != null){
            this.service.salva(vaga);
        }
        return null;
    }
    
    @DeleteMapping("/delete{id}")
    public Vaga exclui(@PathVariable Long id){
        return null;
    }
}
