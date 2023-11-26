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
 * Classe de serviço da entidade Vaga
 */
@Service
public class VagaService {
    
    @Autowired
    VagaRepository repository;
    
    //Salva uma entidade vaga no banco de dados
    public Vaga salva(Vaga vaga){
        return this.repository.save(vaga);
    }
    
    //verifica se já existe uma vaga com id
    public boolean existeVagaComId(Long id) {
        if(repository.existsById(id)){
            return true;
        }else{
            return false;
        }
    }
    
    //Verifica se a tabela está vazia
    public boolean tabelaVazia(){
        return this.repository.count() == 0;
    }
    
    //Seleciona uma vaga de acordo com o id, retornando uma vaga
    public Vaga seleciona(Long id){
        Vaga vaga = this.repository.findById(id).get();
        if(vaga != null){
            return vaga;
        }
        return null;
    }
    
    //Retoran o objeto com o id
    public Vaga buscaVagaId(Long id){
        return this.seleciona(id);
    }
    
    //Exclui uma vaga de acordo com o id vaga
    public Vaga exclui(Long id){
        Vaga vaga =  this.seleciona(id);
        if(vaga != null){
             this.repository.delete(vaga);
        }
        return vaga;
    }
    
    //Lista todos os registros com de forma ascendente
    public List<Vaga> listagem(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "id", "ocupado"));
    }
    
    //Metodo que deleta todos os registros da tabela
    public void deletaRegistros(){
        this.repository.deleteAll();
    }
}
