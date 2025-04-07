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
                                 ('ponts de roca'),
                                 ('tricams'),
                                 ('BigBros');

-- Insertar datos en la tabla tipus
INSERT INTO tipus (nom) VALUES
                            ('Esportiva'),
                            ('Clàssica'),
                            ('Gel');

-- Insertar datos en la tabla dificultats
INSERT INTO dificultats (grau) VALUES
('4'),('4+'),('5'),('5+'),('6a'),('6a+'),('6b'),('6b+'),('6c'),('6c+'),('7a'),('7a+'),('7b'),('7b+'),('7c'),('7c+'),('8a'),('8a+'),('8b'),('8b+'),('8c'),('8c+'),('9a'),('9a+'),('9b'),('9b+'),('9c'),('9c+'),('WI1'),('WI2'),('WI3'),('WI4'),('WI5'),('WI6'),('WI7');

-- Insertar datos en la tabla poblacions
INSERT INTO poblacions (nom) VALUES
                                ('Barcelona'),
                                ('Girona'),
                                ('Lleida'),
                                ('Tarragona'),
                                ('Andorra'),
                                ('Teruel');

-- Insertar datos en la tabla escoles
INSERT INTO escoles (poblacio_id, nom, aproximacio, num_vies, popularitat, restriccions) VALUES
(1, 'Montserrat', 'Aproximació caminant 30 min', 50, 'alta', 'Prohibit de gener a abril per nidificació'),
(2, 'Sadernes', "Aproximació en cotxe fins a l'aparcament, després 20 min a peu", 30, 'mitjana', NULL),
(3, 'Siurana', 'Aproximació a peu 15 min', 100, 'alta', NULL),
(4, 'Cavallers', 'Aproximació en cotxe fins al refugi', 40, 'alta', 'Possible tancament per neu'),
(5, 'Rodellar', 'Aproximació en cotxe i després 25 min a peu', 80, 'alta', NULL);

-- Insertar datos en la tabla sectors
INSERT INTO sectors (escola_id, nom, latitud, longitud, aproximacio, num_vies, popularitat, restriccions) VALUES
(1, 'Vinya Nova', '41.591', '1.825', "5 min a peu des de l'aparcament", 15, 'alta', NULL),
(2, 'Collbató', '41.563', '1.832', '10 min a peu', 12, 'mitjana', NULL),
(3, 'Can Piqui Pugui', '41.333', '1.233', '10 min a peu', 20, 'alta', NULL),
(4, 'El Pati', '41.342', '1.245', '5 min a peu', 30, 'alta', NULL),
(5, 'Bóvedas de Rodellar', '42.324', '0.123', '20 min a peu', 25, 'alta', NULL);

-- Insertar datos en la tabla escaladors
INSERT INTO escaladors (nom, alies, edat, nivell_max, nom_via_max, tipus_fav, fita) VALUES
('Jordi Climber', 'JordiC', 32, '8b+', 'La Fissura del Temps', 'Clàssica', 'Ascensió al Pic Central en solo integral'),
('Miquel Roca', 'MikiRocks', 28, '7c', 'El Camí dels Somnis', 'Esportiva', 'Primer 7c a vista'),
('Laura Puig', 'LauriP', 25, '8a', 'Somnis de Pedra', 'Esportiva', 'Primer 8a encadenat'),
('Pere Gel', 'IceMan', 35, 'WI6', 'Cascada de Cristall', 'Gel', 'Primera ascensió hivernal en solitari'),
('Sonia Valls', 'SoniV', 29, '7b+', 'Diedre Oblicu', 'Clàssica', 'Gran ascensió en paret de 300m');

-- Insertar datos en la tabla vies
INSERT INTO vies (sector_id, tipus_id, ancoratge_id, tipus_roca_id, escalador_id, nom, llargada, numero_via, orientacio, estat) VALUES
(1, 1, 2, 3, 1, 'El repte vertical', 25, 1, 'N', 'Apte'),
(2, 2, 4, 2, 2, 'La fissura del vent', 30, 2, 'S', 'Apte'),
(3, 1, 1, 3, 3, 'Somnis de Pedra', 20, 3, 'O', 'Apte'),
(4, 3, 7, 2, 4, 'Cascada de Cristall', 50, 4, 'NO', 'Apte'),
(5, 1, 3, 1, 5, 'Diedre Oblicu', 35, 5, 'SE', 'Apte'),
(3, 2, 5, 4, 1, "L'esquerda infinita", 40, 6, 'E', 'Apte'),
(5, 1, 6, 2, 3, 'El sostre del diable', 30, 7, 'S', 'Apte'),
(4, 3, 8, 5, 4, 'Colmillos de hielo', 60, 8, 'N', 'Apte'),
(2, 2, 9, 2, 2, 'El regne oblidat', 45, 9, 'O', 'Apte'),
(1, 1, 4, 3, 5, 'Mussol nocturn', 28, 10, 'NO', 'Apte'),
(2, 1, 3, 2, 1, 'Via del Drac', 35, 11, 'SE', 'Apte'),
(3, 2, 4, 3, 2, 'El mur silenciós', 40, 12, 'O', 'Apte'),
(4, 3, 6, 4, 3, 'Gel Eterna', 55, 13, 'N', 'Apte'),
(5, 1, 5, 1, 4, 'Fissura amagada', 30, 14, 'S', 'Apte'),
(1, 2, 7, 3, 5, 'El camí de l’àliga', 38, 15, 'E', 'Apte'),
(3, 1, 2, 2, 1, 'Cingle misteriós', 33, 16, 'NO', 'Apte'),
(4, 3, 9, 4, 2, 'Parets de gel', 60, 17, 'O', 'Apte'),
(5, 2, 8, 5, 3, 'El crit de la muntanya', 45, 18, 'N', 'Apte'),
(1, 1, 1, 3, 4, 'Verticalitat pura', 28, 19, 'SE', 'Apte'),
(2, 2, 4, 2, 5, 'L’ombra del cim', 50, 20, 'S', 'Apte');
