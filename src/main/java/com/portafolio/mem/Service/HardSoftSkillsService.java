/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.Service;



import com.portafolio.mem.Entity.HardSoftSkills;
import com.portafolio.mem.Repository.HardSoftSkillsRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcelo
 */

@Transactional
@Service
public class HardSoftSkillsService {
    
    @Autowired
    HardSoftSkillsRepository hysRepository;

    public List<HardSoftSkills> List() {
        return hysRepository.findAll();
    }

    public Optional<HardSoftSkills> getOne(int id) {
        return hysRepository.findById(id);
    }

    public Optional<HardSoftSkills> getByNombre(String nombre) {
        return hysRepository.findByNombre(nombre);
    }

    public void save(HardSoftSkills hys) {
        hysRepository.save(hys);
    }

    public void delete(int id) {
        hysRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return hysRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return hysRepository.existsByNombre(nombre);
    }
    
}
