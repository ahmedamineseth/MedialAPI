package entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "patient", schema = "medical5", catalog = "")
@NamedQueries( {
        @NamedQuery( name="patient.findAll" , query = "SELECT p FROM PatientEntity p"),
        @NamedQuery( name="patient.findAllByNom" , query = "SELECT p FROM PatientEntity p WHERE nom like :nom")
})

public class PatientEntity {
    private int id;
    private String nom;
    private String prenom;
    private Date datenaissance;
    private String adresse;
    private VilleEntity ville;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nom", nullable = false, length = 100)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "prenom", nullable = false, length = 100)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "datenaissance", nullable = false)
    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    @Basic
    @Column(name = "adresse", nullable = false, length = 100)
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PatientEntity that = (PatientEntity) o;

        if (id != that.id) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (datenaissance != null ? !datenaissance.equals(that.datenaissance) : that.datenaissance != null)
            return false;
        if (adresse != null ? !adresse.equals(that.adresse) : that.adresse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (datenaissance != null ? datenaissance.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "ville", referencedColumnName = "id", nullable = false)
    public VilleEntity getVille() {
        return ville;
    }

    public void setVille(VilleEntity ville) {
        this.ville = ville;
    }
}
