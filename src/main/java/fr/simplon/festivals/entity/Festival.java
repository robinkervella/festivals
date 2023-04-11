package fr.simplon.festivals.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table (name = "festivals")
public class Festival {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long id;
    private String nom;
    private String url;

    @Temporal(TemporalType.DATE)
    private Date debut;

    @Temporal(TemporalType.DATE)
    private Date fin;
    private String ville;
    private int cp;
    private String lieu;
    private double lat;
    private double lon;

    public Festival(Long id, String nom, String url, Date debut, Date fin, String ville, int cp, String lieu, double lat, double lon) {
        this.id = id;
        this.nom = nom;
        this.url = url;
        this.debut = debut;
        this.fin = fin;
        this.ville = ville;
        this.cp = cp;
        this.lieu = lieu;
        this.lat = lat;
        this.lon = lon;
    }

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