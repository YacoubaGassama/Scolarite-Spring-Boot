package uahb.m1gl.gestionscolarite.mapper;

import org.springframework.stereotype.Component;
import uahb.m1gl.gestionscolarite.dto.ClasseRequest;
import uahb.m1gl.gestionscolarite.dto.ClasseResponse;
import uahb.m1gl.gestionscolarite.dto.FiliereDTO;
import uahb.m1gl.gestionscolarite.model.Classe;
import uahb.m1gl.gestionscolarite.model.Filiere;
@Component
public class ClassMapper {

    public Classe classeRequestToClasseEntity(ClasseRequest classeRequest){
        return Classe.builder()
                .nom(classeRequest.getNom())
                .code(classeRequest.getCode())
                .mensualite(classeRequest.getMensualite())
                .montantInscription(classeRequest.getMontantInscription())
                .filiere(Filiere.builder().id(classeRequest.getFiliereId()).build())
                .build();

    }

    public ClasseResponse ClasseEntityToClasseResponse(Classe classe){
        return ClasseResponse.builder()
                .id(classe.getId())
                .nom(classe.getNom())
                .code(classe.getCode())
                .mensualite(classe.getMensualite())
                .montantInscription(classe.getMontantInscription())
                .autreFrais(classe.getAutreFrais())
                .filiere(filiereEntityToFiliereDto(classe.getFiliere()))
                .build();
    }

    public FiliereDTO filiereEntityToFiliereDto(Filiere filiere){
        return FiliereDTO.builder()
                .id(filiere.getId())
                .nom(filiere.getNom())
                .build();
    }
}
