-- Insertar datos en la tabla tipus_roques
INSERT INTO tipus_roques (nom) VALUES
                                   ('conglomerat'),
                                   ('granit'),
                                   ('calcaria'),
                                   ('arenisca'),
                                   ('altres');

-- Insertar datos en la tabla ancoratges
INSERT INTO ancoratges (nom) VALUES
                                 ('spits'),
                                 ('parabolts'),
                                 ('químics'),
                                 ('friends'),
                                 ('tascons'),
                                 ('bagues'),
                                 ('pitons'),
                                 ('tricams'),
                                 ('BigBros');

-- Insertar datos en la tabla tipus
INSERT INTO tipus (nom) VALUES
                            ('Esportiva'),
                            ('Clàssica'),
                            ('Gel');

-- Insertar datos en la tabla dificultats
INSERT INTO dificultats (grau) VALUES
                                   ('4'),('4+'),('5'),('5+'),('6a'),('6a+'),('6b'),('6b+'),('6c'),('6c+'),('7a'),('7a+'),('7b'),('7b+'),('7c'),('7c+'),('8a'),('8a+'),('8b'),('8b+'),('8c'),('8c+'),('9a'),('9a+'),('9b'),('9b+'),('9c'),('9c+');

-- Insertar datos en la tabla poblacions
INSERT INTO poblacions (nom) VALUES
                                 ('Barcelona'),
                                 ('Girona'),
                                 ('Lleida'),
                                 ('Tarragona');

-- Insertar datos en la tabla escoles
INSERT INTO escoles (poblacio_id, nom, aproximacio, num_vies, popularitat, restriccions) VALUES
                                                                                             (1, 'Montserrat', 'Aproximació caminant 30 min', 50, 'alta', 'Prohibit de gener a abril per nidificació'),
                                                                                             (2, 'Sadernes', 'Aproximació en cotxe fins a l\'aparcament, després 20 min a peu', 30, 'mitjana', NULL);

-- Insertar datos en la tabla sectors
INSERT INTO sectors (escola_id, nom, latitud, longitud, aproximacio, num_vies, popularitat, restriccions) VALUES
(1, 'Vinya Nova', '41.591', '1.825', '5 min a peu des de l\'aparcament', 15, 'alta', NULL),
                                                                                             (2, 'Collbató', '41.563', '1.832', '10 min a peu', 12, 'mitjana', NULL);

-- Insertar datos en la tabla escaladors
INSERT INTO escaladors (nom, alies, edat, nivell_max, nom_via_max, tipus_fav, fita) VALUES
                                                                                        ('Jordi Climber', 'JordiC', 32, '8b+', 'La Fissura del Temps', 'Clàssica', 'Ascensió al Pic Central en solo integral'),
                                                                                        ('Miquel Roca', 'MikiRocks', 28, '7c', 'El Camí dels Somnis', 'Esportiva', 'Primer 7c a vista');

-- Insertar datos en la tabla vies
INSERT INTO vies (sector_id, tipus_id, ancoratge_id, tipus_roca_id, escalador_id, nom, llargada, numero_via, orientacio, estat) VALUES
                                                                                                                                    (1, 1, 2, 3, 1, 'El repte vertical', 25, 1, 'N', 'Apte'),
                                                                                                                                    (2, 2, 4, 2, 2, 'La fissura del vent', 30, 2, 'S', 'Apte');
