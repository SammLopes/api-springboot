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

//Controller da entidade Vaga

@CrossOrigin    
@RestController
@RequestMapping("/vaga")
public class VagaController {
        
    @Autowired
    VagaService service;
    
    @Autowired
    VeiculoService serviceVeiculo;
    
    //Metodo GET que lista todos os registros da tabela
    @GetMapping("/get/list")
    public List<Vaga> getVagas(){
        return this.service.listagem();
    }
    
    //Metodo POST que recebe um objeto e adiciona ele no banco
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
    
    //Metodo PUT que atualiza 
    @PutMapping("/update")
    public Vaga atualiza(@RequestBody Vaga vaga){
        if(vaga.getId() != null){
            this.service.salva(vaga);
        }
        return null;
    }
    
    //Metodo POST que adiciona um veiculo a uma vaga pelo seu id
    @PostMapping("/add/veiculo{idVe}-vaga{idVa}")
    public  Vaga addVeiculoVaga(@PathVariable("idVe") Long idVeiculo, @PathVariable("idVa") Long idVaga){
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
    
    //Metodo DELETE onde recebe o id da vaga para exclusão da vaga
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
    
    //Rota que deleta todos os registros
    //@Transactional Se a transação for concluída com sucesso, as alterações no banco de dados devem ser confirmadas. Se ocorrer uma exceção, a transação deve ser revertida.
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
