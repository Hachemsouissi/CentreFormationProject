package entities;
import jakarta.persistence.*;

@Entity
@Table(name = "Formations")
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private int duree;

    @Column(nullable = false)
    private int capacite;

    @Column(nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;

    public Formation() {}

    public Formation(String titre, int duree, int capacite, String description, Professeur professeur) {
        this.titre = titre;
        this.duree = duree;
        this.capacite = capacite;
        this.description = description;
        this.professeur = professeur;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // AJOUT DES GETTERS/SETTERS MANQUANTS POUR PROFESSEUR
    public Professeur getProfesseur() { return professeur; }
    public void setProfesseur(Professeur professeur) { this.professeur = professeur; }
}