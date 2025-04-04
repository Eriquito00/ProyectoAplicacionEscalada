DROP DATABASE IF EXISTS escaladadb;
CREATE DATABASE escaladadb;

USE escaladadb;

CREATE TABLE tipus_roques (
	tipus_roca_id	INT UNSIGNED AUTO_INCREMENT,
    nom				VARCHAR(50),
    CONSTRAINT pk_tipus_roques PRIMARY KEY tipus_roques (tipus_roca_id)
);

CREATE TABLE ancoratges (
	ancoratge_id	INT UNSIGNED AUTO_INCREMENT,
    nom				VARCHAR(50),
    CONSTRAINT pk_ancoratges PRIMARY KEY ancoratges (ancoratge_id)
);

CREATE TABLE tipus (
	tipus_id	INT UNSIGNED AUTO_INCREMENT,
    nom			VARCHAR(50),
    CONSTRAINT pk_tipus PRIMARY KEY tipus (tipus_id)
);

CREATE TABLE dificultats (
	dificultat_id	INT UNSIGNED AUTO_INCREMENT,
    grau			VARCHAR(3),
    CONSTRAINT pk_dificultats PRIMARY KEY dificultats (dificultat_id)
);

CREATE TABLE poblacions (
	poblacio_id		INT UNSIGNED AUTO_INCREMENT,
    nom				VARCHAR(50),
    CONSTRAINT pk_poblacions PRIMARY KEY poblacions (poblacio_id)
);

CREATE TABLE escoles (
	escola_id		INT UNSIGNED AUTO_INCREMENT,
    poblacio_id		INT UNSIGNED,
    nom				VARCHAR(50),
    aproximacio		VARCHAR(100),
    num_vies		INT UNSIGNED,
    popularitat		ENUM("baixa","mitjana","alta"),
    restriccions	VARCHAR(100),
    CONSTRAINT pk_escoles PRIMARY KEY escoles (escola_id),
    CONSTRAINT uk_escoles_nom UNIQUE escoles (nom),
    CONSTRAINT fk_escoles_poblacions FOREIGN KEY escoles (poblacio_id)
		REFERENCES poblacions (poblacio_id)
);

CREATE TABLE sectors (
	sector_id		INT UNSIGNED AUTO_INCREMENT,
	escola_id		INT UNSIGNED,
    nom				VARCHAR(50),
    latitud			VARCHAR(20),
    longitud		VARCHAR(20),
    aproximacio		VARCHAR(100),
    num_vies		INT UNSIGNED,
    popularitat		ENUM("baixa","mitjana","alta"),
    restriccions	VARCHAR(100),
    CONSTRAINT pk_sector PRIMARY KEY sectors (sector_id),
    CONSTRAINT fk_escoles_sectors FOREIGN KEY sectors (escola_id)
		REFERENCES escoles (escola_id)
);

CREATE TABLE escaladors (
	escalador_id	INT UNSIGNED AUTO_INCREMENT,
    nom				VARCHAR(50),
    alies			VARCHAR(50),
    edat			INT UNSIGNED,
    nivell_max		CHAR(3),
    nom_via_max		VARCHAR(50),
    tipus_fav		VARCHAR(50),
    fita			VARCHAR(100),
    CONSTRAINT pk_escaladors PRIMARY KEY escaladors (escalador_id)
);

CREATE TABLE vies (
	via_id			INT UNSIGNED AUTO_INCREMENT,
    sector_id		INT UNSIGNED,
    tipus_id		INT UNSIGNED,
    ancoratge_id	INT UNSIGNED,
    tipus_roca_id	INT UNSIGNED,
    escalador_id	INT UNSIGNED,
    nom 			VARCHAR(50),
    llargada		INT UNSIGNED,
    numero_via		INT UNSIGNED,
    orientacio		ENUM("N","NE","NO","SE","SO","E","O","S"),
    estat			ENUM("Apte","Construccio","Tancada"),
    CONSTRAINT pk_vies PRIMARY KEY vies(via_id),
    CONSTRAINT fk_sectors_vies FOREIGN KEY vies (sector_id)
		REFERENCES sectors (sector_id),
	CONSTRAINT fk_tipus_vies FOREIGN KEY vies (tipus_id)
		REFERENCES tipus (tipus_id),
	CONSTRAINT fk_ancoratges_vies FOREIGN KEY vies (ancoratge_id)
		REFERENCES ancoratges (ancoratge_id),
	CONSTRAINT fk_tipus_roques_vies FOREIGN KEY vies (tipus_roca_id)
		REFERENCES tipus_roques (tipus_roca_id),
	CONSTRAINT fk_escaladors_vies FOREIGN KEY vies (escalador_id)
		REFERENCES escaladors (escalador_id)
);