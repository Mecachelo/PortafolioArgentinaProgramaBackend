/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.Controller;

import com.portafolio.mem.Dto.ProjectsDto;
import com.portafolio.mem.Entity.Projects;
import com.portafolio.mem.Service.ProjectsService;
import com.portafolio.mem.security.controller.Mensaje;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * @author Marcelo
 */
@RestController
@RequestMapping("proyectos")
@CrossOrigin(origins = {"http://localhost:4200","https://frontendmemportfolio.web.app"})
public class ProjectsController {
    
    @Autowired
    ProjectsService projectsService;

    @GetMapping("/lista")
    public ResponseEntity<List<Projects>> list() {
        List<Projects> list = projectsService.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Projects> getById(@PathVariable("id") int id) {
        if (!projectsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        Projects projects = projectsService.getOne(id).get();
        return new ResponseEntity(projects, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProjectsDto projectsDto) {

        if (StringUtils.isBlank(projectsDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (projectsService.existsByNombre(projectsDto.getNombre())) {
            return new ResponseEntity(new Mensaje("Ese proyecto existe"), HttpStatus.BAD_REQUEST);
        }

        Projects projects = new Projects(projectsDto.getNombre(),projectsDto.getFecha() ,projectsDto.getDescripcion(), projectsDto.getImagen());
        projectsService.save(projects);

        return new ResponseEntity(new Mensaje("Proyecto agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ProjectsDto projectsDto) {

        if (!projectsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        if (projectsService.existsByNombre(projectsDto.getNombre())
                && projectsService.getByNombre(projectsDto.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(projectsDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Projects projects = projectsService.getOne(id).get();
        projects.setNombre(projectsDto.getNombre());
        projects.setFecha(projectsDto.getFecha());
        projects.setDescripcion(projectsDto.getDescripcion());
        projects.setImagen(projectsDto.getImagen());

        projectsService.save(projects);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        if (!projectsService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        projectsService.delete(id);

        return new ResponseEntity(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }
    
}
