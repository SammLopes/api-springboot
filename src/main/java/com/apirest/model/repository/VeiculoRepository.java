/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.model.repository;

import com.apirest.model.Veiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 * Repositorio da entidade Veiculo
 */

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    public List<Veiculo> findByPlacaContaining(String placa);    
}
