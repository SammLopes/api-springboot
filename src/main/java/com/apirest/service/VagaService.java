/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.service;

import com.apirest.model.Vaga;
import com.apirest.model.repository.VagaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class VagaService {
    
    @Autowired
    VagaRepository repository;
    
    public Vaga salva(Vaga vaga){
        return this.repository.save(vaga);
    }
    
    public Vaga seleciona(Long id){
        Vaga vaga = this.repository.findById(id).get();
        if(vaga != null){
            return vaga;
        }
        return null;
    }
    
    public Vaga exclui(Long id){
        Vaga vaga =  this.seleciona(id);
        if(vaga != null){
             this.repository.delete(vaga);
        }
        return vaga;
    }
    
    public List<Vaga> listagem(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "id", "ocupado"));
    }
    
}
