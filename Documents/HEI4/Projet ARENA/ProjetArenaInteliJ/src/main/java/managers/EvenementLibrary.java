package managers;

import dao.EvenementDao;
import dao.impl.EvenementDaoImpl;
import entities.Evenement;

import java.time.LocalDate;
import java.util.List;

public class EvenementLibrary {




        private static class EvenementLibraryHolder{
            private final static EvenementLibrary instance= new EvenementLibrary();
        }
        public static EvenementLibrary getInstance(){return EvenementLibraryHolder.instance;}

        private EvenementDao evenementDao=new EvenementDaoImpl();

        public List<Evenement> listeEvenement(){ return evenementDao.listeEvenement();}

        private EvenementLibrary(){}

        public Evenement getEvenement(String nomE){return evenementDao.getEvenement(nomE);}

        public String getId(String nomE,  LocalDate dateE){
            if (nomE == null || "".equals(nomE)) {

                throw new IllegalArgumentException("Le nom de l'evenement ne doit pas être vide.");

            }

            return evenementDao.getId(nomE,dateE);

        }


        public Evenement addEvenement(Evenement evenement){//evenementDao.addEvenement(evenement);
            return evenementDao.addEvenement(evenement);
        }
    public void deleteEvenement(String nomE, LocalDate dateE){evenementDao.deleteEvenement(nomE,dateE);

    }
}


