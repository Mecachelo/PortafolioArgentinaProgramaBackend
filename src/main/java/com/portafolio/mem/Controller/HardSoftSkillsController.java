/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portafolio.mem.Controller;

import com.portafolio.mem.Dto.HardSoftSkillsDto;
import com.portafolio.mem.Entity.HardSoftSkills;
import com.portafolio.mem.Service.HardSoftSkillsService;
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
@CrossOrigin(origins = {"http://localhost:4200","https://frontendmemportfolio.web.app"})
@RequestMapping("hys")
public class HardSoftSkillsController {

    @Autowired
    HardSoftSkillsService hysService;

    @GetMapping("/lista")
    public ResponseEntity<List<HardSoftSkills>> list() {
        List<HardSoftSkills> list = hysService.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HardSoftSkills> getById(@PathVariable("id") int id) {
        if (!hysService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        HardSoftSkills hys = hysService.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HardSoftSkillsDto hysDto) {

        if (StringUtils.isBlank(hysDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (hysService.existsByNombre(hysDto.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa skill existe"), HttpStatus.BAD_REQUEST);
        }

        HardSoftSkills hys = new HardSoftSkills(hysDto.getNombre(), hysDto.getPorcentaje());
        hysService.save(hys);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody HardSoftSkillsDto hysDto) {

        if (!hysService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        if (hysService.existsByNombre(hysDto.getNombre())
                && hysService.getByNombre(hysDto.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(hysDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HardSoftSkills hys = hysService.getOne(id).get();
        hys.setNombre(hysDto.getNombre());
        hys.setPorcentaje(hysDto.getPorcentaje());

        hysService.save(hys);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        if (!hysService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        hysService.delete(id);

        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }

}
