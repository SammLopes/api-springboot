    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.service;

import com.apirest.model.Veiculo;
import com.apirest.model.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

/**
 *
 * @author usuario
 */
@Service
public class VeiculoService {
    @Autowired
    VeiculoRepository repository;
    
    //Adicionar Veiculo
    public Veiculo salvar(Veiculo veiculo){
       return this.repository.save(veiculo);
    }
    
    //Deletar veiculos
    public void delete(Long id ){
        Veiculo veiculo = this.seleciona(id);
        this.repository.delete(veiculo);
    }
   
    //verifica se já existe um veiculo com id
    public boolean existeVeiculoComId(Long id) {
        return repository.existsById(id);
    }

    //Verifica se a tabela está vazia
    public boolean tabelaVazia(){
        return this.repository.count() == 0;    
    }
    
    
    public Veiculo seleciona(Long id) {
        Veiculo veiculo =  this.repository.findById(id).get();
        if(veiculo != null){
            return veiculo;
        }
        return null;
    }
    
    public List<Veiculo> listagem(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "modelo","vaga","placa","cor"));
    }
    
    public List<Veiculo> buscaPorTermo(String termo){
        return this.repository.findByPlacaContaining(termo);
    }
    
    //Deleta todos os elementos da lista
    @Transactional
    public void deletarListaDeObjetos(List<Veiculo> veiculos) {
        repository.deleteAll(veiculos);
    }
    
}
