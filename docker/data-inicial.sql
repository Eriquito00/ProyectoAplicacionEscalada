-- Insertar datos en la tabla tipus_roques
INSERT INTO tipus_roques (nom) VALUES
                                   ("conglomerat"),
                                   ("granit"),
                                   ("calcaria"),
                                   ("arenisca"),
                                   ("altres");

-- Insertar datos en la tabla ancoratges
INSERT INTO ancoratges (nom) VALUES
                                 ("spits"),
                                 ("parabolts"),
                                 ("quimics"),
                                 ("friends"),
                                 ("tascons"),
                                 ("bagues"),
                                 ("pitons"),
                                 ("ponts de roca"),
                                 ("tricams"),
                                 ("BigBros");

-- Insertar datos en la tabla tipus
INSERT INTO tipus (nom) VALUES
                            ("esportiva"),
                            ("classica"),
                            ("gel");

-- Insertar datos en la tabla dificultats
INSERT INTO dificultats (grau) VALUES
("4"),("4+"),("5"),("5+"),("6a"),("6a+"),("6b"),("6b+"),("6c"),("6c+"),
("7a"),("7a+"),("7b"),("7b+"),("7c"),("7c+"),("8a"),("8a+"),("8b"),("8b+"),
("8c"),("8c+"),("9a"),("9a+"),("9b"),("9b+"),("9c"),("9c+"),
("WI1"),("WI2"),("WI3"),("WI4"),("WI5"),("WI6"),("WI7");

-- Insertar datos en la tabla poblacions
INSERT INTO poblacions (nom) VALUES
                                ("Barcelona"),
                                ("Girona"),
                                ("Lleida"),
                                ("Tarragona"),
                                ("Andorra"),
                                ("Teruel");

-- Insertar datos en la tabla escoles
INSERT INTO escoles (poblacio_id, nom, aproximacio, num_vies, popularitat, restriccions) VALUES
(1, "Montserrat", "Aproximacio caminant 30 min", 50, "alta", "Prohibit de gener a abril per nidificacio"),
(2, "Sadernes", "Aproximacio en cotxe fins a l'aparcament, despres 20 min a peu", 30, "mitjana", NULL),
(3, "Siurana", "Aproximacio a peu 15 min", 100, "alta", NULL),
(4, "Cavallers", "Aproximacio en cotxe fins al refugi", 40, "alta", "Possible tancament per neu"),
(5, "Rodellar", "Aproximacio en cotxe i despres 25 min a peu", 80, "alta", NULL);

-- Insertar datos en la tabla sectors
INSERT INTO sectors (escola_id, nom, latitud, longitud, aproximacio, num_vies, popularitat, restriccions) VALUES
(1, "Vinya Nova", "41.591", "1.825", "5 min a peu des de l'aparcament", 15, "alta", NULL),
(2, "Collbato", "41.563", "1.832", "10 min a peu", 12, "mitjana", NULL),
(3, "Can Piqui Pugui", "41.333", "1.233", "10 min a peu", 20, "alta", NULL),
(4, "El Pati", "41.342", "1.245", "5 min a peu", 30, "alta", NULL),
(5, "Bovedas de Rodellar", "42.324", "0.123", "20 min a peu", 25, "alta", NULL);

-- Insertar datos en la tabla escaladors
INSERT INTO escaladors (nom, alies, edat, nivell_max, nom_via_max, tipus_fav, fita) VALUES
("Jordi Climber", "JordiC", 32, "8b+", "La Fissura del Temps", "Classica", "Ascensio al Pic Central en solo integral"),
("Miquel Roca", "MikiRocks", 28, "7c", "El Cami dels Somnis", "Esportiva", "Primer 7c a vista"),
("Laura Puig", "LauriP", 25, "8a", "Somnis de Pedra", "Esportiva", "Primer 8a encadenat"),
("Pere Gel", "IceMan", 35, "WI6", "Cascada de Cristall", "Gel", "Primera ascensio hivernal en solitari"),
("Sonia Valls", "SoniV", 29, "7b+", "Diedre Oblicu", "Classica", "Gran ascensio en paret de 300m");

-- Insertar datos en la tabla vies
INSERT INTO vies (sector_id, tipus_id, ancoratge_id, tipus_roca_id, escalador_id, dificultat_id, nom, llargada, numero_via, orientacio, estat, ultim_apte) VALUES
(1, 1, 2, 3, 1, 22, "El repte vertical", 25, 1, "N", "apte", CURDATE()),
(2, 2, 4, 2, 2, 1, "La fissura del vent", 30, 2, "S", "tancada", NULL),
(3, 1, 1, 3, 3, 8, "Somnis de Pedra", 20, 3, "O", "apte", CURDATE()),
(4, 3, 7, 2, 4, 12, "Cascada de Cristall", 50, 4, "NO", "construccio", NULL),
(5, 1, 3, 1, 5, 4, "Diedre Oblicu", 35, 5, "SE", "construccio", NULL),
(3, 2, 5, 4, 1, 9, "L'esquerda infinita", 40, 6, "E", "apte", CURDATE()),
(5, 1, 6, 2, 3, 25, "El sostre del diable", 30, 7, "S", "construccio", NULL),
(4, 3, 8, 5, 4, 8, "Colmillos de hielo", 60, 8, "N", "apte", CURDATE()),
(2, 2, 9, 2, 2, 10, "El regne oblidat", 45, 9, "O", "apte", CURDATE()),
(1, 1, 4, 3, 5, 31, "Mussol nocturn", 28, 10, "NO", "construccio",  NULL),
(2, 1, 3, 2, 1, 35, "Via del Drac", 35, 11, "SE", "apte", CURDATE()),
(3, 2, 4, 3, 2, 23, "El mur silencios", 40, 12, "O", "tancada", NULL),
(4, 3, 6, 4, 3, 27, "Gel Eterna", 55, 13, "N", "apte", CURDATE()),
(5, 1, 5, 1, 4, 14, "Fissura amagada", 30, 14, "S", "tancada", NULL),
(1, 2, 7, 3, 5, 18, "El cami de l'aliga", 38, 15, "E", "apte", CURDATE()),
(3, 1, 2, 2, 1, 20, "Cingle misterios", 33, 16, "NO", "construccio", NULL),
(4, 3, 9, 4, 2, 16, "Parets de gel", 60, 17, "O", "apte", CURDATE()),
(5, 2, 8, 5, 3, 22, "El crit de la muntanya", 45, 18, "N", "apte", CURDATE()),
(1, 1, 1, 3, 4, 31, "Verticalitat pura", 28, 19, "SE", "construccio", NULL),
(2, 2, 4, 2, 5, 2, "L'ombra del cim", 50, 20, "S", "tancada", NULL);