package managers;

import dao.EvUtDao;
import dao.impl.EvUtDaoImpl;
import entities.EvUt;
import entities.Utilisateur;

import java.util.List;

public class EvUtLibrary {


        private static class EvUtLibraryHolder{
            private final static managers.EvUtLibrary instance= new managers.EvUtLibrary();
        }
        public static managers.EvUtLibrary getInstance(){return managers.EvUtLibrary.EvUtLibraryHolder.instance;}

        private EvUtDao evutDao=new EvUtDaoImpl();

        public List<EvUt> listeEvUt(int id){ return evutDao.listeEvUt(id);}

        public Integer listeEvUtPrix(int id){return evutDao.listeEvUtPrix(id);}

        private EvUtLibrary(){}

        public EvUt getEvUt(String pseudo, int id){return evutDao.getEvUt(pseudo, id);}

        public List<Utilisateur> listeEvutMail(int id){return evutDao.listeEvUtMail(id);}

        public EvUt addEvUt(EvUt evut){//evenementDao.addEvenement(evenement);
            return evutDao.addEvUt(evut);
        }

        public void deletEvUt(String pseudo, int id){evutDao.deleteEvUt(pseudo,id);

        }
    }




