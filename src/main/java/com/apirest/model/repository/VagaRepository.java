/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.model.repository;

import com.apirest.model.Vaga;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author usuario
 * Repositorio da entidade Vaga
 */
public interface VagaRepository  extends JpaRepository<Vaga, Long>{
   
}
