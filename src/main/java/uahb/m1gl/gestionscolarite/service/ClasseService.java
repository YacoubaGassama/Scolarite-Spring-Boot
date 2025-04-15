package uahb.m1gl.gestionscolarite.service;

import org.springframework.stereotype.Service;
import uahb.m1gl.gestionscolarite.model.Classe;
import uahb.m1gl.gestionscolarite.repository.ClasseRepository;

import java.util.List;

@Service
public class ClasseService {
    //@Autowired
    //private ClasseRepository classeRepository;

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public Classe save(Classe classe){
        return classeRepository.save(classe);
    }

    public List<Classe> findAll(){
        return classeRepository.findAll();
    }

    public Classe findByCode(String code){return  classeRepository.findByCode(code);}
    public Classe findByNom(String nom){return classeRepository.findByNom(nom);}
    public Classe findByFiliereId(long id){ return classeRepository.findByFiliereId(id);}

}
