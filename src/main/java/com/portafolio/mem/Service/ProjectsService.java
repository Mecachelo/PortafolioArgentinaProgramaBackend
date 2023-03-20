/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.Service;

import com.portafolio.mem.Entity.Projects;
import com.portafolio.mem.Repository.ProjectsRepository;
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
public class ProjectsService {
    @Autowired
    ProjectsRepository projectsRepository;

    public List<Projects> List() {
        return projectsRepository.findAll();
    }

    public Optional<Projects> getOne(int id) {
        return projectsRepository.findById(id);
    }

    public Optional<Projects> getByNombre(String nombre) {
        return projectsRepository.findByNombre(nombre);
    }

    public void save(Projects pro) {
        projectsRepository.save(pro);
    }

    public void delete(int id) {
        projectsRepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return projectsRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return projectsRepository.existsByNombre(nombre);
    }
}
