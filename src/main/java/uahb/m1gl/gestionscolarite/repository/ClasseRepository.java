package uahb.m1gl.gestionscolarite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uahb.m1gl.gestionscolarite.model.Classe;
@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
    Classe findByCode(String code);
    Classe findByNom(String nom);
}
