CREATE DATABASE escaladadb;

USE escaladadb;

CREATE TABLE coordenades (
	coordenada_id	INT UNSIGNED AUTO_INCREMENT,
    latitud			VARCHAR(20),
    longitud		VARCHAR(20),
    CONSTRAINT pk_coordenades PRIMARY KEY coordenades (coordenada_id)
);

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

CREATE TABLE llocs (
	lloc_id		INT UNSIGNED AUTO_INCREMENT,
    nom			VARCHAR(50),
    CONSTRAINT pk_llocs PRIMARY KEY llocs (lloc_id)
);

CREATE TABLE tipus (
	tipus_id	INT UNSIGNED AUTO_INCREMENT,
    nom			VARCHAR(50),
    CONSTRAINT pk_tipus PRIMARY KEY tipus (tipus_id)
);

CREATE TABLE dificultats (
	dificultat_id	INT UNSIGNED AUTO_INCREMENT,
    grau			VARCHAR(3),
    tipus_id		INT UNSIGNED,
    CONSTRAINT pk_dificultats PRIMARY KEY dificultats (dificultat_id),
    CONSTRAINT fk_tipus_dificultats FOREIGN KEY dificultats (tipus_id)
		REFERENCES tipus (tipus_id)
);

CREATE TABLE sectors (
	sector_id		INT UNSIGNED AUTO_INCREMENT,
    nom				VARCHAR(50),
    escola_id		INT UNSIGNED,
    CONSTRAINT pk_sector PRIMARY KEY sectors (sector_id),
    CONSTRAINT fk_escoles_sectors FOREIGN KEY sectors (escola_id)
		REFERENCES escoles (escola_id)
);

CREATE TABLE escoles (
	escola_id		INT UNSIGNED AUTO_INCREMENT,
    nom				VARCHAR(50),
    CONSTRAINT pk_escoles PRIMARY KEY escoles (escola_id)
);

CREATE TABLE vies (
	via_id			INT UNSIGNED AUTO_INCREMENT,
    nom 			VARCHAR(50),
    llargada		INT UNSIGNED,
    dificultat_id	INT UNSIGNED,
    orientacio		ENUM("N","NE","NO","SE","SO","E","O","S"),
    estat			ENUM("Apte","Construccio","Tancada"),
    poblacio		VARCHAR(50),
    sector_id		INT UNSIGNED,
    tipus_id		INT UNSIGNED,
    ancoratge_id	INT UNSIGNED,
    coordenada_id	INT UNSIGNED,
    tipus_roca_id	INT UNSIGNED,
    lloc_id			INT UNSIGNED,
    escalador_id	INT UNSIGNED,
    CONSTRAINT pk_via PRIMARY KEY vies(via_id),
    CONSTRAINT fk_dificultats_vies FOREIGN KEY (dificultat_id)
		REFERENCES dificultats (dificultat_id),
	CONSTRAINT fk_lloc_vies FOREIGN KEY (via_id)
		REFERENCES dificultats (dificultat_id), 
);