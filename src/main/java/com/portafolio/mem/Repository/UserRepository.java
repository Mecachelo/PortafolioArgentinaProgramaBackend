package com.portafolio.mem.Repository;

import com.portafolio.mem.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marcelo
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByNombre(String nombre);

    public boolean existsByNombre(String nombre);

}
