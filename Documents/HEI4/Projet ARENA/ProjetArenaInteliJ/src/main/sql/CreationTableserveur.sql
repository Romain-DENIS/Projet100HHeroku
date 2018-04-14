Drop Table IF EXISTS EvUt;
Drop Table IF EXISTS Commentaire;
Drop Table IF EXISTS Utilisateur;
Drop Table IF EXISTS Evenement;



CREATE TABLE Utilisateur(
`nom` VARCHAR(50) NOT NULL,
`prenom` VARCHAR(50) NOT NULL,
`pseudo` VARCHAR(50) NOT NULL,
`mot_de_passe` VARCHAR(50) NOT NULL,
`email` VARCHAR(50) NOT NULL,
`classe` VARCHAR(50) NOT NULL,
`notif` bool NOT NULL,
PRIMARY KEY (`pseudo`)
);

CREATE TABLE Evenement(
`id` int NOT NULL,
`nomE` VARCHAR(50) NOT NULL,
`descri` VARCHAR(500) NOT NULL,
`dateE` date NOT NULL,
`plateforme` VARCHAR(50) NOT NULL,
`interhei` bool NOT NULL,
`payant` int NOT NULL,
PRIMARY KEY (`id`)
);

CREATE TABLE EvUt(

`pseudo` VARCHAR(50) NOT NULL,
`id` int NOT NULL,
`paye` bool NOT NULL,
PRIMARY KEY(`id`,`pseudo`)
);

ALTER TABLE EvUt
ADD FOREIGN KEY (id) REFERENCES Evenement(id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE EvUt
add CONSTRAINT lien_Util_EvUt FOREIGN KEY (pseudo) REFERENCES Utilisateur(pseudo)
ON DELETE CASCADE
ON UPDATE CASCADE;

CREATE TABLE Commentaire(
  `commentaire` TEXT NOT NULL,
  `pseudo` VARCHAR(50) NOT NULL,

  `idevut` int not null ,
  FOREIGN KEY (`idevut`) REFERENCES Evenement(id),
  FOREIGN KEY (`pseudo`) REFERENCES Utilisateur(pseudo),
  primary key(`idevut`,`pseudo`)
);

INSERT INTO utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('Admin','Admin','Administrateur','ARENAHEI','.@hei.yncrea.fr','H',0);


INSERT INTO utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom1','prenom1','pseudo1','mdp1','test1@exemple','H44',1);
INSERT INTO utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom2','prenom2','pseudo2','mdp2','test2@exemple','H42',0);
INSERT INTO utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom3','prenom3','pseudo3','mdp3','test3@exemple','H43',1);

