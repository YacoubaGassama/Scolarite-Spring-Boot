package uahb.m1gl.gestionscolarite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uahb.m1gl.gestionscolarite.model.Filiere;
@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
    Filiere findById(long id) ;
}
