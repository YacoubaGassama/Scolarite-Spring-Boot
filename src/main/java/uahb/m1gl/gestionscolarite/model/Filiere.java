package uahb.m1gl.gestionscolarite.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity //(name = "filier_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 50)
    private String nom;
    @OneToMany(mappedBy = "filiere")
    private List<Classe> classes;
}
