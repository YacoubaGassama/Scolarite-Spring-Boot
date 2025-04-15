package uahb.m1gl.gestionscolarite.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 8)
    private String code;
    @Column(length = 150)
    private String nom;
    private int montantInscription;
    private int mensualite;
    private int autreFrais;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

}
