package uahb.m1gl.gestionscolarite.helper;

import org.springframework.stereotype.Component;
import uahb.m1gl.gestionscolarite.dto.ClasseRequest;
import uahb.m1gl.gestionscolarite.dto.ClasseResponse;
import uahb.m1gl.gestionscolarite.exception.DuplicateResourceException;
import uahb.m1gl.gestionscolarite.exception.InvalidDataException;
import uahb.m1gl.gestionscolarite.mapper.ClassMapper;
import uahb.m1gl.gestionscolarite.repository.ClasseRepository;
import uahb.m1gl.gestionscolarite.service.ClasseService;

import java.util.List;
@Component
public class ClasseHelper {
    private final ClasseService classeService;
    private final ClassMapper classMapper;
    private final ClasseRepository classeRepository;
    public ClasseHelper(ClasseService classeService, ClassMapper classMapper, ClasseRepository classeRepository) {
        this.classeService = classeService;
        this.classMapper = classMapper;
        this.classeRepository = classeRepository;
    }

    public List<ClasseResponse> findAllClasses(){
        return classeService.findAll().stream()
                .map(classMapper::ClasseEntityToClasseResponse)
                .toList();
    }

    public ClasseResponse saveClasse(ClasseRequest classeRequest){
        // Vérifier si le code existe dans la base de donnée

        // Vérifier si le code existe dans la base de données
        if (classeRepository.findByCode(classeRequest.getCode()) != null) {
            throw new DuplicateResourceException("Une classe avec ce code existe déjà");
        }

        // Vérifier si le nom existe dans la base de données
        if (classeRepository.findByNom(classeRequest.getNom()) != null) {
            throw new DuplicateResourceException("Une classe avec ce nom existe déjà");
        }

        // Vérifier si le montant de l'inscription est >= 30k
        if (classeRequest.getMontantInscription() < 30000) {
            throw new InvalidDataException("Le montant de l'inscription doit être d'au moins 30 000");
        }

        // Vérifier si la mensualité est >= 30k
        if (classeRequest.getMensualite() < 30000) {
            throw new InvalidDataException("La mensualité doit être d'au moins 30 000");
        }

        // Vérifier si les autres frais sont >= 10k
        if (classeRequest.getAutreFrais() < 10000) {
            throw new InvalidDataException("Les autres frais doivent être d'au moins 10 000");
        }

        // Si toutes les validations passent, sauvegarder la classe
        var savedClasse = classeService.save(classMapper.classeRequestToClasseEntity(classeRequest));
        return classMapper.ClasseEntityToClasseResponse(savedClasse);

    }

}
