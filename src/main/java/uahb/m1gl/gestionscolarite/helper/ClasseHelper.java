package uahb.m1gl.gestionscolarite.helper;

import org.springframework.stereotype.Component;
import uahb.m1gl.gestionscolarite.dto.ClasseRequest;
import uahb.m1gl.gestionscolarite.dto.ClasseResponse;
import uahb.m1gl.gestionscolarite.exception.DuplicateResourceException;
import uahb.m1gl.gestionscolarite.exception.InvalidDataException;
import uahb.m1gl.gestionscolarite.exception.ScolariteNotFoundException;
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
        // Vérifier si la filliere existe
        if (classeService.findByFiliereId(classeRequest.getFiliereId()) == null){
            throw new ScolariteNotFoundException("Aucune filere avec l'id "+classeRequest.getFiliereId()+" n'est trouvé ");
        }
        // Vérifier si le code existe dans la base de données
        if (classeService.findByCode(classeRequest.getCode()) != null) {
            throw new ScolariteNotFoundException("Une classe avec le code "+classeRequest.getCode()+" existe déjà");
        }

        // Vérifier si le nom existe dans la base de données
        if (classeService.findByNom(classeRequest.getNom()) != null) {
            throw new ScolariteNotFoundException("Une classe avec le nom "+classeRequest.getNom()+" existe déjà");
        }

        // Vérifier si le montant de l'inscription est >= 30k
        valideMontant(classeRequest.getMontantInscription(),30000);

        // Vérifier si la mensualité est >= 30k
        valideMontant(classeRequest.getMensualite(),30000);
        // Vérifier si les autres frais sont >= 10k
        valideMontant(classeRequest.getAutreFrais(),10000);

        // Si toutes les validations passent, sauvegarder la classe
        var savedClasse = classeService.save(classMapper.classeRequestToClasseEntity(classeRequest));
        return classMapper.ClasseEntityToClasseResponse(savedClasse);

    }

    private void valideMontant (int montant,int seuil){
        if (montant < seuil) {
            throw new ScolariteNotFoundException("La mensualité doit être d'au moins "+seuil);
        }
    }

}
