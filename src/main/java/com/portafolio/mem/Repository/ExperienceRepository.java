/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.mem.Repository;

import com.portafolio.mem.Entity.Experience;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcelo
 */
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Integer> {

    public Optional<Experience> findByNombreExp(String nombreExp);

    public boolean existsByNombreExp(String nombreExp);
}
