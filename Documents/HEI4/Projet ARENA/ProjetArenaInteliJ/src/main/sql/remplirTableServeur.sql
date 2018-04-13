

INSERT INTO utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom1','prenom1','pseudo1','mdp1','test1@exemple','H44',1);
INSERT INTO utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom2','prenom2','pseudo2','mdp2','test2@exemple','H42',0);
INSERT INTO utilisateur(`nom`,`prenom`,`pseudo`,`mot_de_passe`,`email`,`classe`,`notif`) VALUES ('nom3','prenom3','pseudo3','mdp3','test3@exemple','H43',1);


/*
INSERT INTO evenement(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even1','descri1','2018-02-19','ps4',1,1);
INSERT INTO evenement(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even2','descri2','2018-02-18','ordi',0,1);
INSERT INTO evenement(`nomE`,`descri`,`dateE`,`plateforme`,`interhei`,`payant`) VALUES ('even3','descri3','2018-02-17','xbox',1,0);

*/
INSERT INTO evut(`id`,`pseudo`,`paye`) VALUES ('1','pseudo1',1);
INSERT INTO evut(`id`,`pseudo`,`paye`) VALUES ('2','pseudo2',0);

INSERT INTO commentaire(`idevut`,`pseudo`,`commentaire`) VALUES ('1','pseudo1','commentaire1');
INSERT INTO commentaire(`idevut`,`pseudo`,`commentaire`) VALUES ('2','pseudo2','commentaire2');