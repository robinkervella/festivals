package fr.simplon.festivals.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;


/**
 * Cette classe représente l'entité Festival dans la base de données.
 * Elle est annotée avec @Entity pour indiquer à JPA
 * qu'elle est persistante et est mappée à la table "festivals".
 */
@Entity
@Table(name = "festivals")
public class Festival {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String url;
    private String ville;
    private int cp;
    @Column(length = 1024)
    private String lieu;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date debut;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date fin;
    private double lat;
    private double lon;

    /**
     * Constructeur de la classe Festival avec tous les paramètres.
     *
     * @param id    l'identifiant du festival
     * @param nom   le nom du festival
     * @param url   l'URL du site web du festival
     * @param debut la date de début du festival
     * @param fin   la date de fin du festival
     * @param ville la ville où se déroule le festival
     * @param cp    le code postal de la ville où se déroule le festival
     * @param lieu  le lieu exact où se déroule le festival
     * @param lat   la latitude du lieu où se déroule le festival
     * @param lon   la longitude du lieu où se déroule le festival
     */
    public Festival(Long id, String nom, String url, Date debut, Date fin, String ville, int cp, String lieu, double lat, double lon) {
        this.id = id;
        this.nom = nom;
        this.url = url;
        this.ville = ville;
        this.cp = cp;
        this.lieu = lieu;
        this.debut = debut;
        this.fin = fin;
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Constructeur de la classe Festival sans paramètre pour que Spring y ait accès.
     */
    public Festival() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
