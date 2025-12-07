package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titre;

    @Column(columnDefinition = "TEXT")
    private String contenu;

    @Column(nullable = false)
    private String dateCreation;

    @Column(nullable = false)
    private boolean visible = false;

    @ManyToOne
    @JoinColumn(name = "formation_id", nullable = false)
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "professeur_id", nullable = false)
    private Professeur professeur;

    private String fichierUrl;

    public Cours() {}

    public Cours(String titre, String contenu, String dateCreation, Formation formation, Professeur professeur) {
        this.titre = titre;
        this.contenu = contenu;
        this.dateCreation = dateCreation;
        this.formation = formation;
        this.professeur = professeur;
        this.visible = false;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public String getDateCreation() { return dateCreation; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }

    public Formation getFormation() { return formation; }
    public void setFormation(Formation formation) { this.formation = formation; }

    public Professeur getProfesseur() { return professeur; }
    public void setProfesseur(Professeur professeur) { this.professeur = professeur; }

    public String getFichierUrl() { return fichierUrl; }
    public void setFichierUrl(String fichierUrl) { this.fichierUrl = fichierUrl; }

    public boolean isVisible() { return visible; }
    public void setVisible(boolean visible) { this.visible = visible; }
}