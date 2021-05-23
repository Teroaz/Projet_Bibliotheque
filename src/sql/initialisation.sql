DROP TABLE ETUDIANT;
DROP TABLE LIVRE;
DROP TABLE RESERV;
DROP TABLE EXEMPLAIRE;
DROP TABLE EMPRUNT;

/*
 Création des tables
 */

CREATE TABLE ETUDIANT
(
    ID_ET  NUMBER(20) PRIMARY KEY,
    NOM    VARCHAR(50)  NOT NULL,
    PRENOM VARCHAR(50)  NOT NULL,
    MDP    VARCHAR(350) NOT NULL,
    EMAIL  VARCHAR(150) NOT NULL CHECK ( EMAIL LIKE '%@%.%' )
);

CREATE TABLE LIVRE
(
    ID_LIV NUMBER(20) PRIMARY KEY,
    AUTEUR VARCHAR(100) NOT NULL,
    TITRE  VARCHAR(200) NOT NULL
);

CREATE TABLE RESERV
(
    DATE_RES     TIMESTAMP   DEFAULT SYSDATE,
    DATE_FIN_RES TIMESTAMP   NOT NULL,
    ID_ET        NUMBER(20) NOT NULL REFERENCES ETUDIANT(ID_ET),
    ID_LIV       NUMBER(20) NOT NULL REFERENCES LIVRE(ID_LIV),
    PRIMARY KEY (DATE_RES, ID_ET, ID_LIV)
);

CREATE TABLE EXEMPLAIRE
(
    ID_EX  NUMBER(20) PRIMARY KEY,
    ID_LIV NUMBER(20) NOT NULL REFERENCES LIVRE(ID_LIV),
    ETAT VARCHAR(30) NOT NULL
);

CREATE TABLE EMPRUNT
(
    DATE_EMP    TIMESTAMP        NOT NULL,
    DATE_RETOUR TIMESTAMP        NOT NULL,
    ID_ET       NUMBER(20)  NOT NULL REFERENCES ETUDIANT(ID_ET),
    ID_EX       NUMBER(20)  NOT NULL REFERENCES EXEMPLAIRE(ID_EX),
    PRIMARY KEY (ID_ET, ID_EX, DATE_EMP)
);


INSERT INTO LIVRE VALUES (1, 'Foenkinos, David', 'Charlotte');
INSERT INTO LIVRE VALUES (2, 'Carrère, Emmanuel', 'Le royaume');
INSERT INTO LIVRE VALUES (3, 'Slimani, Leïla', 'Chanson douce');
INSERT INTO LIVRE VALUES (4, 'Ferrante, Elena', 'L''amie prodigieuse (Tome 2) - Le nouveau nom');
INSERT INTO LIVRE VALUES (5, 'Viel, Tanguy', 'Article 353 du code pénal');
INSERT INTO LIVRE VALUES (6, 'Walsh, Rosie', 'Les jours de ton absence');
INSERT INTO LIVRE VALUES (7, 'Ferney, Alice', 'Les Bourgeois');
INSERT INTO LIVRE VALUES (8, 'Vargas, Fred', 'Temps glaciaires');
INSERT INTO LIVRE VALUES (9, 'Kerangal, Maylis de', 'Réparer les vivants');
INSERT INTO LIVRE VALUES (10, 'Shalev, Zeruya', 'Ce qui reste de nos vies');
INSERT INTO LIVRE VALUES (11, 'Del Arbol, Victor', 'La Veille de presque tout');
INSERT INTO LIVRE VALUES (12, 'Vuillard, Eric', 'L"ordre du jour');
INSERT INTO LIVRE VALUES (13, 'Läckberg, Camilla', 'La sorcière');
INSERT INTO LIVRE VALUES (14, 'Paasilinna, Arto', 'Les mille et une gaffes de l''ange gardien Ariel Auvinen');
INSERT INTO LIVRE VALUES (15, 'Modiano, Patrick', 'Pour que tu ne te perdes pas dans le quartier');
INSERT INTO LIVRE VALUES (16, 'Bagieu, Pénélope', 'Culottées. Des femmes qui ne font que ce qu''elles veulent (Tome 2)');
INSERT INTO LIVRE VALUES (17, 'Lagercrantz, David', 'Millénium 4 - Ce qui ne me tue pas');
INSERT INTO LIVRE VALUES (18, 'Rufin, Jean-Christophe', 'Le collier rouge');
INSERT INTO LIVRE VALUES (19, 'Japp, Andrea H.', 'Barbarie 2.0');
INSERT INTO LIVRE VALUES (20, 'Nesbo, Jo', 'Du sang sur la glace');
INSERT INTO LIVRE VALUES (21, 'Hegland, Jean', 'Dans la forêt');
INSERT INTO LIVRE VALUES (22, 'Zeniter, Alice', 'L''Art de perdre');
INSERT INTO LIVRE VALUES (23, 'Penny, Louise', 'Le beau mystère');
INSERT INTO LIVRE VALUES (24, 'Négar, Djavadi', 'Désorientale');
INSERT INTO LIVRE VALUES (25, 'Marsh, Willa', 'Une famille délicieuse');
INSERT INTO LIVRE VALUES (26, 'Dugain, Marc', 'Ils vont tuer Robert Kennedy');
INSERT INTO LIVRE VALUES (27, 'Schulman, Ninni', 'Le Garçon qui ne pleurait plus');
INSERT INTO LIVRE VALUES (28, 'Salem, Carlos', 'Attends-moi au ciel');
INSERT INTO LIVRE VALUES (29, 'Gallay, Claudie', 'La Beauté des jours');
INSERT INTO LIVRE VALUES (30, 'May, Peter', 'Les disparus du phare');
INSERT INTO LIVRE VALUES (31, 'Rouart, Jean-Marie', 'Ne pars pas avant moi');
INSERT INTO LIVRE VALUES (32, 'Harmel, Kristin', 'L''Heure indigo');
INSERT INTO LIVRE VALUES (33, 'Kellerman, Jonathan', 'Les soeurs ennemies');
INSERT INTO LIVRE VALUES (34, 'Horst, Jorn Lier', 'Fermé pour l''hiver');
INSERT INTO LIVRE VALUES (35, 'Matar, Hisham', 'La terre qui les sépare');
INSERT INTO LIVRE VALUES (36, 'Bachi, Salim', 'Dieu, Allah, moi et les autres');
INSERT INTO LIVRE VALUES (37, 'Labro, Philippe', 'Ma mère, cette inconnue');
INSERT INTO LIVRE VALUES (38, 'Frioux, Dalibor', 'Incident voyageurs');
INSERT INTO LIVRE VALUES (39, 'Padura, Fuentes, Leonardo', 'Hérétiques');
INSERT INTO LIVRE VALUES (40, 'Menegoz, Mathias', 'Karpathia');
INSERT INTO LIVRE VALUES (41, 'Bartelt, Franz', 'Hôtel du Grand Cerf');
INSERT INTO LIVRE VALUES (42, 'Parsons, Tony', 'Des garçons bien élevés');
INSERT INTO LIVRE VALUES (43, 'Rouquette, Anne', 'Émilie voit quelqu''un - Après la psy, le beau temps ?');
INSERT INTO LIVRE VALUES (44, 'Zafon, Carlos ruiz', 'Le Labyrinthe des esprits');
INSERT INTO LIVRE VALUES (45, 'Trillard, Marc', 'L''anniversaire du roi');
INSERT INTO LIVRE VALUES (46, 'Joncour, Serge', 'L''écrivain national');
INSERT INTO LIVRE VALUES (47, 'McDermid, Val', 'Châtiments');
INSERT INTO LIVRE VALUES (48, 'Aillon, Jean d''', 'Rouen 1203');
INSERT INTO LIVRE VALUES (49, 'Kellerman, Jonathan', 'Un maniaque dans la ville');
INSERT INTO LIVRE VALUES (50, 'Wilson, Robert Charles', 'Blind Lake');
INSERT INTO LIVRE VALUES (51, 'Flournoy, Angela', 'La Maison des Turner');
INSERT INTO LIVRE VALUES (52, 'Wohlsdorf, Gina', 'Sécurité');
INSERT INTO LIVRE VALUES (53, 'Ferrari, Jérôme', 'À son image');
INSERT INTO LIVRE VALUES (54, 'Bonin, Cyril', 'La délicatesse');
INSERT INTO LIVRE VALUES (55, 'Hill, Nathan', 'Les fantômes du vieux pays');
INSERT INTO LIVRE VALUES (56, 'Higgins Clark, Mary', 'Le Temps des regrets');
INSERT INTO LIVRE VALUES (57, 'Slimani, Leïla', 'Dans le jardin de l''ogre');
INSERT INTO LIVRE VALUES (58, 'Reinhardt, Eric', 'L''amour et les forêts');
INSERT INTO LIVRE VALUES (59, 'Abécassis, Eliette', 'Un secret du docteur Freud');
INSERT INTO LIVRE VALUES (60, 'Ma, Jian', 'La route sombre');
INSERT INTO LIVRE VALUES (61, 'Levy, Deborah', 'Sous l''eau');
INSERT INTO LIVRE VALUES (62, 'Nesbo, Jo', 'Soleil de nuit');
INSERT INTO LIVRE VALUES (63, 'Ellory, R. J.', 'Un coeur sombre');
INSERT INTO LIVRE VALUES (64, 'Cook, Thomas H.', 'Danser dans la poussière');
INSERT INTO LIVRE VALUES (65, 'Melo, Patricia', 'Feu follet');
INSERT INTO LIVRE VALUES (66, 'Modiano, Patrick', 'Une Jeunesse');
INSERT INTO LIVRE VALUES (67, 'Busquets, Milena', 'Ça aussi, ça passera');
INSERT INTO LIVRE VALUES (68, 'Jablonka, Ivan', 'Laëtitia ou la fin des hommes');
INSERT INTO LIVRE VALUES (69, 'Miller, Jax', 'Les infâmes');
INSERT INTO LIVRE VALUES (70, 'Chavouet, Florent', 'L''ïle Louvre');
INSERT INTO LIVRE VALUES (71, 'Duroy, Lionel', 'Eugenia');
INSERT INTO LIVRE VALUES (72, 'Flanagan, Joe', 'Un moindre mal');
INSERT INTO LIVRE VALUES (73, 'Ruskovich, Emily', 'Idaho');
INSERT INTO LIVRE VALUES (74, 'Enard, Mathias', 'Boussole');
INSERT INTO LIVRE VALUES (75, 'Alexieva, Elena', 'Le prix Nobel');
INSERT INTO LIVRE VALUES (76, 'Colin, Fabrice', 'La poupée de Kafka');
INSERT INTO LIVRE VALUES (77, 'Baricco, Alessandro', 'Mr Gwyn');
INSERT INTO LIVRE VALUES (78, 'Lansdale, Joe R.', 'Les mécanos de Vénus');
INSERT INTO LIVRE VALUES (79, 'Ahern, Cecelia', 'Tombée du ciel');
INSERT INTO LIVRE VALUES (80, 'Baricco, Alessandro', 'Trois fois dès l''aube');
INSERT INTO LIVRE VALUES (81, 'Ferus, Jim', 'La Vengeance des mères');
INSERT INTO LIVRE VALUES (82, 'Clément, Catherine', 'Indu Boy');
INSERT INTO LIVRE VALUES (83, 'Malamud, Bernard', 'Le commis');
INSERT INTO LIVRE VALUES (84, 'Gonthier, Nicole', 'Les Chants de la mort');
INSERT INTO LIVRE VALUES (85, 'Urbani, Ellen', 'Landfall');
INSERT INTO LIVRE VALUES (86, 'Baricco, Alessandro', 'La Jeune épouse');
INSERT INTO LIVRE VALUES (87, 'Groff, Lauren', 'Les Furies');
INSERT INTO LIVRE VALUES (88, 'Ghosh, Amitav', 'Un déluge de feu');
INSERT INTO LIVRE VALUES (89, 'Penny, Louise', 'Une illusion d''optique');
INSERT INTO LIVRE VALUES (90, 'Runcie, James', 'Sidney Chambers et les périls de la nuit');
INSERT INTO LIVRE VALUES (91, 'Geni, Abby', 'Farallon Islands');
INSERT INTO LIVRE VALUES (92, 'Lothar, Ernst', 'Mélodie de Vienne');
INSERT INTO LIVRE VALUES (93, 'Burton, Jessie', 'Les filles au lion');
INSERT INTO LIVRE VALUES (94, 'Vales, José C.', 'Cabaret Biarritz');
INSERT INTO LIVRE VALUES (95, 'Hope, Anna', 'La salle de bal');
INSERT INTO LIVRE VALUES (96, 'Schirach, Ferdinand von', 'L''affaire Collini');
INSERT INTO LIVRE VALUES (97, 'Bouchard, Nicolas', 'Tarpeia, les venins de Rome');
INSERT INTO LIVRE VALUES (98, 'Boyd, William', 'Les Vies multiples d''Amory Clay');
INSERT INTO LIVRE VALUES (99, 'Barbato, Paola', 'A mains nues');
INSERT INTO LIVRE VALUES (100, 'Reverdy, Thomas B.', 'Il était une ville');

commit