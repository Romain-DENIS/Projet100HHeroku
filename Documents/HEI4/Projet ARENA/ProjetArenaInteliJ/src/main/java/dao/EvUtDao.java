package dao;

import entities.EvUt;
import entities.Evenement;
import entities.Utilisateur;

import javax.swing.tree.ExpandVetoException;
import java.util.List;

public interface EvUtDao {

    public List<EvUt> listeEvUt(int id);

    public Integer listeEvUtPrix(int id);

    public EvUt getEvUt(String pseudo, int id);

 //   public String getId(String nomE, LocalDate dateE);

    public EvUt addEvUt(EvUt evut);


    public void deleteEvUt(String pseudo, int id);

    public List<Evenement> listeEvUtEven(String pseudo);

    public List<Utilisateur> listeEvUtMail(int id);
}
