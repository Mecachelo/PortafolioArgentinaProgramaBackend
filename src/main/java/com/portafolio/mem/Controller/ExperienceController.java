/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.Controller;

import com.portafolio.mem.Dto.ExperienceDto;
import com.portafolio.mem.Entity.Experience;
import com.portafolio.mem.Service.ExperienceService;
import com.portafolio.mem.security.controller.Mensaje;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("expLaboral")
@CrossOrigin(origins = {"http://localhost:4200","https://frontendmemportfolio.web.app"})

public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    @GetMapping("/lista")
    public ResponseEntity<List<Experience>> list() {
        List<Experience> list = experienceService.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id")int id){
       if (!experienceService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        } 
       Experience experience = experienceService.getOne(id).get();
       return new ResponseEntity(experience, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienceDto experienceDto) {

        if (StringUtils.isBlank(experienceDto.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (experienceService.existsByNombreExp(experienceDto.getNombreExp())) {
            return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);
        }

        Experience experience = new Experience(experienceDto.getNombreExp(), experienceDto.getDescripcionExp());
        experienceService.save(experience);

        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienceDto experienceDto) {

        if (!experienceService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        if (experienceService.existsByNombreExp(experienceDto.getNombreExp())
                && experienceService.getByNombreExp(experienceDto.getNombreExp()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(experienceDto.getNombreExp())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Experience experience = experienceService.getOne(id).get();
        experience.setNombreExp(experienceDto.getNombreExp());
        experience.setDescripcionExp(experienceDto.getDescripcionExp());

        experienceService.save(experience);
        return new ResponseEntity(new Mensaje("Experiencia actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        if (!experienceService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        experienceService.delete(id);

        return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.OK);
    }
}
