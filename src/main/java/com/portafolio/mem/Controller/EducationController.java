/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.Controller;

import com.portafolio.mem.Dto.EducationDto;
import com.portafolio.mem.Entity.Education;
import com.portafolio.mem.Service.EducationService;
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
@RequestMapping("educacion")
@CrossOrigin(origins = {"http://localhost:4200","https://frontendmemportfolio.web.app"})
public class EducationController {

    @Autowired
    EducationService educationService;

    @GetMapping("/lista")
    public ResponseEntity<List<Education>> list() {
        List<Education> list = educationService.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id) {
        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        Education education = educationService.getOne(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducationDto educationDto) {

        if (StringUtils.isBlank(educationDto.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (educationService.existsByNombreEdu(educationDto.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("Esa educacion existe"), HttpStatus.BAD_REQUEST);
        }

        Education education = new Education(educationDto.getNombreEdu(), educationDto.getDescripcionEdu());
        educationService.save(education);

        return new ResponseEntity(new Mensaje("Educacion agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducationDto educationDto) {

        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        if (educationService.existsByNombreEdu(educationDto.getNombreEdu())
                && educationService.getByNombreEdu(educationDto.getNombreEdu()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa educacion ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(educationDto.getNombreEdu())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Education education = educationService.getOne(id).get();
        education.setNombreEdu(educationDto.getNombreEdu());
        education.setDescripcionEdu(educationDto.getDescripcionEdu());

        educationService.save(education);
        return new ResponseEntity(new Mensaje("Educacion actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        if (!educationService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        educationService.delete(id);

        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }

}
