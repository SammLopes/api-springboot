    
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
 * @author Samuel
 * 
 * Classe de serviço da entidade Veiculo
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
    
    //Metodo que seleciona um veiculo pelo seu ID
    public Veiculo seleciona(Long id) {
        Veiculo veiculo =  this.repository.findById(id).get();
        if(veiculo != null){
            return veiculo;
        }
        return null;
    }
    
    //Lista todos os elementos
    public List<Veiculo> listagem(){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC, "modelo","vaga","placa","cor"));
    }
    
    //Metodo que busca veiculo por placa
    public List<Veiculo> buscaPorPlaca(String placa){
        try {
            List<Veiculo> veiculos = this.repository.findByPlacaContaining(placa); 
            return veiculos;
        } catch (Exception e) {
            String erro = "Veiculo não encontrado"+e.getMessage();
            System.out.println(erro);
            return null;
        }
      
    }
    
    //Busca por id
    public Veiculo buscaVeiculoId(long id){
        return this.seleciona(id);
    }
    
    //Deleta todos os elementos da lista
    public void deletarRegistros() {
       this.repository.deleteAll();
    }
    
 
}
