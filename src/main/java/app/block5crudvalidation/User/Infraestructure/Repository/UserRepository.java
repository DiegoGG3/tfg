package app.block5crudvalidation.User.Infraestructure.Repository;


import app.block5crudvalidation.User.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.gmail = :gmail")
    Optional<User> findByGmail(@Param("gmail") String gmail);}
