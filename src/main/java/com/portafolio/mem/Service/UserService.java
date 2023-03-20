package com.portafolio.mem.Service;

import com.portafolio.mem.Entity.User;
import com.portafolio.mem.Repository.UserRepository;
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
public class UserService {

    @Autowired
    UserRepository userrepository;

    public List<User> List() {
        return userrepository.findAll();
    }

    public Optional<User> getOne(int id) {
        return userrepository.findById(id);
    }

    public Optional<User> getByNombre(String nombre) {
        return userrepository.findByNombre(nombre);
    }

    public void save(User us) {
        userrepository.save(us);
    }

    public void delete(int id) {
        userrepository.deleteById(id);
    }

    public boolean existsById(int id) {
        return userrepository.existsById(id);
    }

    public boolean existsByNombre(String nombre) {
        return userrepository.existsByNombre(nombre);
    }

}
