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
    EMAIL  VARCHAR(150) NOT NULL CHECK ( EMAIL LIKE '%@%.%' ),
    MDP    VARCHAR(350) NOT NULL
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
    DATE_FIN_RES TIMESTAMP   DEFAULT SYSDATE+5,
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
    DATE_EMP    TIMESTAMP        DEFAULT SYSDATE,
    DATE_RETOUR TIMESTAMP        DEFAULT SYSDATE + 15,
    ID_ET       NUMBER(20)  NOT NULL REFERENCES ETUDIANT(ID_ET),
    ID_EX       NUMBER(20)  NOT NULL REFERENCES EXEMPLAIRE(ID_EX),
    PRIMARY KEY (ID_ET, ID_EX, DATE_EMP)
);


/**
    Etudiant : auto incrémentation id_et
 */
-- DROP SEQUENCE etudiant_seq;
CREATE SEQUENCE etudiant_seq START WITH 1 NOCACHE;

CREATE OR REPLACE TRIGGER etudiant_id_auto_inc
BEFORE INSERT ON ETUDIANT
FOR EACH ROW

BEGIN
    SELECT etudiant_seq.nextval INTO :new.ID_ET FROM DUAL;
end;


/**
    Exemplaire : auto incrémentation id_ex
 */
--  DROP SEQUENCE exemplaire_seq;
CREATE SEQUENCE exemplaire_seq START WITH 1 NOCACHE;

CREATE OR REPLACE TRIGGER exemplaire_id_auto_inc
BEFORE INSERT ON EXEMPLAIRE
FOR EACH ROW

BEGIN
    SELECT exemplaire_seq.nextval INTO :new.ID_EX FROM DUAL;
end;

/**
    Livre : auto incrémentation id_liv
 */
--  DROP SEQUENCE livre_seq;
CREATE SEQUENCE livre_seq START WITH 1 NOCACHE;

CREATE OR REPLACE TRIGGER livre_id_auto_inc
BEFORE INSERT ON LIVRE
FOR EACH ROW

BEGIN
    SELECT livre_seq.nextval INTO :new.ID_LIV FROM DUAL;
end;

commit