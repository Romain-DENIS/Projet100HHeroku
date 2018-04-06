package dao;

import entities.EvUt;
import java.util.List;

public interface EvUtDao {

    public List<EvUt> listeEvUt(int id);

    public EvUt getEvUt(String pseudo, int id);

 //   public String getId(String nomE, LocalDate dateE);

    public EvUt addEvUt(EvUt evut);


    public void deleteEvUt(String pseudo, int id);
}
