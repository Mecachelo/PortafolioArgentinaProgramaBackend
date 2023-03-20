/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.portafolio.mem.Repository;


import com.portafolio.mem.Entity.HardSoftSkills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcelo
 */
@Repository
public interface HardSoftSkillsRepository extends JpaRepository<HardSoftSkills, Integer>{
    
    public Optional<HardSoftSkills> findByNombre(String nombre);

    public boolean existsByNombre(String nombre);
    
}
