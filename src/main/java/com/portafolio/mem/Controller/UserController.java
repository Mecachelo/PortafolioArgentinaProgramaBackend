package com.portafolio.mem.Controller;

import com.portafolio.mem.Dto.UserDto;
import com.portafolio.mem.Entity.User;
import com.portafolio.mem.Service.UserService;
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
 * @PreAuthorize("hasRole('ADMIN')")
 */
@RestController
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200","https://frontendmemportfolio.web.app"})
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/lista")
    public ResponseEntity<List<User>> list() {
        List<User> list = userService.List();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        if (!userService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        User user = userService.getOne(id).get();
        return new ResponseEntity(user, HttpStatus.OK);
    }

    /*@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDto userDto) {

        if (StringUtils.isBlank(userDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(userDto.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(userDto.getDescripcion())) {
            return new ResponseEntity(new Mensaje("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(userDto.getImagen())) {
            return new ResponseEntity(new Mensaje("La imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        User user = new User(userDto.getNombre(), userDto.getApellido(), userDto.getDescripcion(), userDto.getImagen());
        userService.save(user);

        return new ResponseEntity(new Mensaje("Usuario agregado"), HttpStatus.OK);
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody UserDto userDto) {

        if (!userService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        if (userService.existsByNombre(userDto.getNombre())
                && userService.getByNombre(userDto.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(userDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.getOne(id).get();
        user.setNombre(userDto.getNombre());
        user.setApellido(userDto.getApellido());
        user.setDescripcion(userDto.getDescripcion());
        user.setImagen(userDto.getImagen());

        userService.save(user);
        return new ResponseEntity(new Mensaje("User actualizado"), HttpStatus.OK);
    }

    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {

        if (!userService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        userService.delete(id);

        return new ResponseEntity(new Mensaje("Usuario eliminado"), HttpStatus.OK);
    }*/

}
