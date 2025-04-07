package uahb.m1gl.gestionscolarite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseResponse {
    private long id;
    private String code;
    private String nom;
    private int montantInscription;
    private int mensualite;
    private int autreFrais;

    private FiliereDTO filiere;
}
