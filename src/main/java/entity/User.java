package entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nom de famille")
    private String firstName;
    @Column(name = "prenom")
    private  String lastName;

    private  String nomUtilisateur;

    private  String passWord;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
