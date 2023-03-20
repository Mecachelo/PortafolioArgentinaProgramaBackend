/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.Service;

import com.portafolio.mem.Entity.Education;
import com.portafolio.mem.Repository.EducationRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Marcelo
 */
@Service
@Transactional
public class EducationService {
    
    @Autowired
    EducationRepository educationRepository;

    public List<Education> List() {
        return educationRepository.findAll();
    }

    public Optional<Education> getOne(int id) {
        return educationRepository.findById(id);
    }

    public Optional<Education> getByNombreEdu(String nombreEdu) {
        return educationRepository.findByNombreEdu(nombreEdu);
    }

    public void save(Education edu) {
        educationRepository.save(edu);
    }

    public void delete(int id) {
        educationRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return educationRepository.existsById(id);
    }

    public boolean existsByNombreEdu(String nombreEdu) {
        return educationRepository.existsByNombreEdu(nombreEdu);
    }
    
}
