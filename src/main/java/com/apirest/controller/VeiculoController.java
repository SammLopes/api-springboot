/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.controller;

import com.apirest.model.Vaga;
import com.apirest.model.Veiculo;
import com.apirest.service.VeiculoService;
import jakarta.transaction.Transactional;
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

/**
 *
 * @author usuario
 */
@CrossOrigin
@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    
    @Autowired
    VeiculoService service; 
    
   
    @GetMapping("/get/list")
    public List<Veiculo> getVeiculo(){
        return this.service.listagem();
    }
    
    
    @GetMapping("/get/{placa}")
    public List<Veiculo> getVeiculoTermo(@PathVariable("placa") String placa){
            if(placa != null ){
                if(!this.service.tabelaVazia()){
                List<Veiculo> veiculo = this.service.buscaPorPlaca(placa);
                return veiculo;
            }
        } 
        return null;   
    }
    
    @PostMapping("/add")
     public String insereVaga(@RequestBody Veiculo veiculo){
         boolean jaExiste = this.service.existeVeiculoComId(veiculo.getId());
        if(veiculo != null && !jaExiste ){
            this.service.salvar(veiculo);
            return "Cadastro Realizado com Sucesso!!";
        }else {
            return "Já cadastrado";
        }
    }
     
    @PutMapping("/update")
    public Veiculo atualizaVeiculo(Veiculo veiculo){
        Veiculo veiculoId =  this.service.seleciona(veiculo.getId());
        if(veiculoId != null){
            this.service.salvar(veiculo);
        }
        return null;
    }
    
    @GetMapping("/tabela")
    public String tabelaVazia(){
        boolean bool = this.service.tabelaVazia();
        if(bool == true){
            return "Tabela Vazia !!";
        }else{
            return "Tabela não está vazia !!";  
        }
        
    }
    
    @DeleteMapping("/delete{id}")
    public String deleteVeiculo(@PathVariable("id") Long id){
        if(id != null && !this.service.tabelaVazia()){
            this.service.delete(id);
            return "Deletado";
        }else{
            return "Tabela está vazia";
        }

    }
    
    @DeleteMapping("/delete/all")
    @Transactional
    public String excluiRegistros(){
        boolean bool = this.service.tabelaVazia();
        if(!bool == true){
            try {
                this.service.deletarRegistros();
                return "Registros excluidos";
            } catch (Exception e) {
                e.printStackTrace();
               return "Erro ao excluir os registros"+e.getMessage();
            }
        }else{
            return "Tabela está vazia";
        }
    }
   
}
