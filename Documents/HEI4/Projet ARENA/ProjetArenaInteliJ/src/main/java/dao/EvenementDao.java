package dao;


import entities.Evenement;

import java.time.LocalDate;
import java.util.List;

public interface EvenementDao {

    public List<Evenement> listeEvenement();

    public Evenement getEvenement(int id);

    public Integer getId(String nomE, LocalDate dateE);

    public Evenement addEvenement(Evenement evenement);


    public void deleteEvenement(String nomE, LocalDate dateE);
}
