package entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Inscriptions")
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "apprenant_id")
    private Apprenant apprenant;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    @Column(nullable = false)
    private String dateInscription;

    public Inscription() {}

    public Inscription(Apprenant apprenant, Formation formation, String dateInscription) {
        this.apprenant = apprenant;
        this.formation = formation;
        this.dateInscription = dateInscription;
    }

    public int getId() { return id; }

    public Apprenant getApprenant() { return apprenant; }
    public void setApprenant(Apprenant apprenant) { this.apprenant = apprenant; }

    public Formation getFormation() { return formation; }
    public void setFormation(Formation formation) { this.formation = formation; }

    public String getDateInscription() { return dateInscription; }
    public void setDateInscription(String dateInscription) { this.dateInscription = dateInscription; }
    public int getIdApprenant() {
        return apprenant != null ? apprenant.getId() : 0;
    }
    public int getIdFormation() {
        return formation != null ? formation.getId() : 0;
    }
    public void setIdApprenant(int idApprenant) {
        if (this.apprenant == null) this.apprenant = new Apprenant();
        this.apprenant.setId(idApprenant);
    }
    public void setIdFormation(int idFormation) {
        if (this.formation == null) this.formation = new Formation();
        this.formation.setId(idFormation);
    }

}
