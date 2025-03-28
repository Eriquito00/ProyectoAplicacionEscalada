CREATE DATABASE escaladadb;

USE escaladadb;

CREATE TABLE vies (
	via_id			INT UNSIGNED AUTO_INCREMENT,
    nom 			VARCHAR(50),
    llargada		INT UNSIGNED,
    dificultat_id	INT UNSIGNED,
    orientacio		ENUM("N","NE","NO","SE","SO","E","O","S"),
    estat			ENUM("Apte","Construccio","Tancada"),
    poblacio		VARCHAR(50),
    escola_id		INT UNSIGNED,
    sector_id		INT UNSIGNED,
    coordenada_id	INT UNSIGNED,
    ancoratge_id	INT UNSIGNED,
    tipus_roca_id	INT UNSIGNED,
    escalador_id	INT UNSIGNED,
    CONSTRAINT pk_via PRIMARY KEY vies(via_id),
    CONSTRAINT fk_dificultats_vies FOREIGN KEY (dificultat_id)
		REFERENCES dificultats (dificultat_id),
	CONSTRAINT fk_lloc_vies FOREIGN KEY (via_id)
		REFERENCES dificultats (dificultat_id), 
)