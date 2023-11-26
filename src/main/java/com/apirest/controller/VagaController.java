/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.controller;

import com.apirest.model.Vaga;
import com.apirest.model.Veiculo;
import com.apirest.service.VagaService;
import com.apirest.service.VeiculoService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    
    @Autowired
    VeiculoService serviceVeiculo;
    
    @GetMapping("/get/list")
    public List<Vaga> getVagas(){
        return this.service.listagem();
    }
    
    @PostMapping("/add")
    public String insereVaga(@RequestBody Vaga vaga){
     
        if(vaga != null){
            boolean bool = this.service.existeVagaComId(vaga.getId());
             if(!bool == true){
                if(vaga.isOcupado() == true){
                    vaga.setOcupado(false);
                    this.service.salva(vaga);
                    return "Vaga cadastrada";
                }
             }else{
                 return "Vaga já cadastrada";
             }
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
    
    @PostMapping("/add/veiculo{idVe}-vaga{idVa}")
    public /*String*/ Vaga addVeiculoVaga(@PathVariable("idVe") Long idVeiculo, @PathVariable("idVa") Long idVaga){
        List<Veiculo> veiculos = new ArrayList<>();
        boolean existeVeiculo =  this.serviceVeiculo.existeVeiculoComId(idVeiculo);
        boolean existeVaga = this.service.existeVagaComId(idVaga);
        if(existeVeiculo && existeVaga){
            Veiculo veiculo =  this.serviceVeiculo.buscaVeiculoId(idVeiculo);
            Vaga vaga = this.service.buscaVagaId(idVaga);
            veiculos.add(veiculo);
            veiculo.setVaga(vaga);
            vaga.setOcupado(true);
            vaga.setVeiculos(veiculos);
            this.service.salva(vaga);
            return vaga;
            //return "Veiculo adicionado a vaga";
        }
        //return "Vaga ou veiculos não cadastrados";
        return null;
    }
    
    @DeleteMapping("/delete{id}")
    public Vaga exclui(@PathVariable Long id){
        if(id != null && !this.service.tabelaVazia()){
            boolean bool = this.service.existeVagaComId(id);
            if(bool == true){
                return this.service.exclui(id);
            }
        }
        return null;
    }
    
    @DeleteMapping("/delete/all")
    @Transactional
    public String deletaRegistros(){
        boolean bool = this.service.tabelaVazia();
        try {
            if(!bool == true){
                this.service.deletaRegistros();
                return "Todos os registros deletados";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ocorreu um erro ao tentar deletar os registros"+e.getMessage();
        }
        return null;
    }
    
    
}
