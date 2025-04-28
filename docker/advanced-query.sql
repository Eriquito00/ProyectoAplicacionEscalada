USE escaladadb;
    
/*CONSULTAS AVANZADAS*/
    
/* 1 Mostra les vies d'una Escola que es trobaran disponibles */
    
SELECT v.nom, v.numero_via, v.orientacio, t.nom AS tipus, d.grau AS dificultat, s.nom AS sector, e.nom AS escola
	FROM vies v
    INNER JOIN tipus t ON t.tipus_id = v.tipus_id
    INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id
    INNER JOIN sectors s ON s.sector_id = v.sector_id
    INNER JOIN escoles e ON e.escola_id = s.escola_id
WHERE e.nom = ? AND v.estat = "apte";

/* 2 Cercar vies per dificultat en un rang específic (via, grau, sector, escola) */

SELECT v.nom, d.grau, s.nom, e.nom
	FROM vies v
    INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id
    INNER JOIN sectors s ON s.sector_id = v.sector_id
    INNER JOIN escoles e ON e.escola_id = s.escola_id
WHERE d.grau = ?;

/* 3 Cercar vies segons estat (Apte, Construcció, Tancada) */

SELECT v.nom, v.numero_via, v.orientacio, t.nom AS tipus, d.grau AS dificultat, s.nom AS sector, e.nom AS escola
	FROM vies v
    INNER JOIN tipus t ON t.tipus_id = v.tipus_id
    INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id
    INNER JOIN sectors s ON s.sector_id = v.sector_id
    INNER JOIN escoles e ON e.escola_id = s.escola_id
WHERE v.estat = ?;

/* 4 Consultar escoles amb restriccions actives actualment */

SELECT e.nom, p.nom AS poblacio, e.aproximacio, e.num_vies, e.popularitat, e.restriccions
	FROM escoles e
    INNER JOIN poblacions p ON p.poblacio_id = e.poblacio_id
WHERE e.restriccions RLIKE ".*";

/* 5 Mostrar sectors amb més de X vies disponibles */

SELECT s.nom, e.nom, s.latitud, s.longitud, s.aproximacio, s.num_vies, s.popularitat, s.restriccions
	FROM sectors s
    INNER JOIN escoles e ON e.escola_id = s.escola_id
WHERE s.num_vies > ?;

/* 6 Mostrar escaladors amb el mateix nivell màxim assolit */

SELECT e.nom, e.alies, e.edat, e.nivell_max, e.nom_via_max, e.tipus_fav, e.fita
	FROM escaladors e
WHERE e.nivell_max = ?;

/* 7 Mostrar les vies més llargues d’una escola determinada */

SELECT v.nom, v.llargada, v.numero_via, v.orientacio, t.nom AS tipus, d.grau AS dificultat, s.nom AS sector, e.nom AS escola
	FROM vies v
    INNER JOIN tipus t ON t.tipus_id = v.tipus_id
    INNER JOIN dificultats d ON d.dificultat_id = v.dificultat_id
    INNER JOIN sectors s ON s.sector_id = v.sector_id
    INNER JOIN escoles e ON e.escola_id = s.escola_id
WHERE e.nom = ?
ORDER BY v.llargada DESC;