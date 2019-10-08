# Nombres: Alonso, Galiana, Vazquez

#-------------------------------------------------------------------------
# Insercion de datos de prueba
# MODELOS DE AVION
INSERT INTO modelos_avion VALUES ("747 MAX", "Boeing", 2 ,400);
INSERT INTO modelos_avion VALUES ("787", "Boeing", 2 ,350);
INSERT INTO modelos_avion VALUES ("A380", "Airbus", 2 ,320);
INSERT INTO modelos_avion VALUES ("A330", "Airbus", 2 ,300);

# UBICACIONES
INSERT INTO ubicaciones VALUES ("Argentina", "Buenos Aires", "Buenos Aires", '-3');
INSERT INTO ubicaciones VALUES ("USA", "Florida", "Miami", '-5');
INSERT INTO ubicaciones VALUES ("Argentina", "Buenos Aires", "Bahia Blanca", '-3');

# CLASES
INSERT INTO clases VALUES ("primera", 0.05);
INSERT INTO clases VALUES ("turista", 0.10);

# COMODIDADES
INSERT INTO comodidades VALUES ("001", "Peliculas");
INSERT INTO comodidades VALUES ("002", "Comida");
INSERT INTO comodidades VALUES ("003", "confiteria");
INSERT INTO comodidades VALUES ("004", "accesorios de cama");

# PASAJEROS
INSERT INTO pasajeros VALUES ("dni", 25091234, "Schmidt", "Lucia", "11 de abril 257", "291-4745341", "Argetino" );
INSERT INTO pasajeros VALUES ("dni", 16257114, "Braganza", "Belen", "Alem 811", "291-4745341", "Argetino" );
INSERT INTO pasajeros VALUES ("dni", 5423158, "Pozos", "Silvina", "Lamadrid 1050", "291-4745341", "Argetino" );
INSERT INTO pasajeros VALUES ("dni", 30671234, "Cavallo", "Alejandro", "Mitre 215", "291-4745341", "Argetino" );
INSERT INTO pasajeros VALUES ("dni", 17284515, "Lalaland", "Lautaro", "Colon 760", "291-4745341", "Argetino" );

# EMPLEADO
INSERT INTO empleados VALUES (100000, "aaa000", "dni", 36715341, "Gonzales", "Jose", "12 de Octubre 1215", "2926-401352");
INSERT INTO empleados VALUES (100001, "aaa001", "dni", 31247379, "Prat-Gay", "Alfonso", "Belgrano 315", "2926-401352");
INSERT INTO empleados VALUES (100002, "aaa002", "dni", 15671234, "Carabajal", "Carlos", "Salta 720", "2926-401352");
INSERT INTO empleados VALUES (100003, "aaa003", "dni", 11234583, "Simpson", "Homero", "Donado 651", "2926-401352");
INSERT INTO empleados VALUES (200000, "aaa004", "dni", 32456112, "Romero", "Jorge", "Cuyo 691", "2926-401352");
INSERT INTO empleados VALUES (200001, "aaa005", "dni", 30122914, "Sosa", "Mercedes", "19 de mayo 1232", "2926-401352");

# AEROPUERTOS
INSERT INTO aeropuertos VALUES ("EZE", "Ministro Pistarini", "011-4124124", "Autopista Gral. Ricchieri Km 33,5", "Argentina", "Buenos Aires", "Buenos Aires");
INSERT INTO aeropuertos VALUES ("BHI", "Comandante Espora", "291-4332476", "Ex Ruta 3 Norte km 675", "Argentina", "Buenos Aires", "Bahia Blanca");
INSERT INTO aeropuertos VALUES ("MIA", "Ezeiza", "+1 305-876-7000", "2100 NW 42nd Ave", "USA", "Florida", "Miami");

# VUELOS PROGRAMADOS
INSERT INTO vuelos_programados VALUES ("000001", "EZE", "MIA"); -- EZEIZA A MIAMI
INSERT INTO vuelos_programados VALUES ("000005", "EZE", "MIA"); -- EZEIZA A MIAMI
INSERT INTO vuelos_programados VALUES ("000002", "EZE", "BHI"); -- EZEIZA A BAHIA BLANCA
INSERT INTO vuelos_programados VALUES ("000003", "BHI", "EZE"); -- BAHIA BLANCA A EZEIZA
INSERT INTO vuelos_programados VALUES ("000004", "MIA", "EZE"); -- MIAMI A EZEIZA

# SALIDAS
INSERT INTO salidas VALUES ("000001", 'Vi', '15:20', '22:30', "747 MAX");
INSERT INTO salidas VALUES ("000001", 'Lu', '12:00', '21:00', "787");
INSERT INTO salidas VALUES ("000002", 'Vi', '06:00', '06:30', "A330");
INSERT INTO salidas VALUES ("000003", 'Ju', '13:15', '13:45', "747 MAX");
INSERT INTO salidas VALUES ("000004", 'Sa', '07:00', '16:00', "A380");
INSERT INTO salidas VALUES ("000003", 'Vi', '17:00', '17:30', "787");
INSERT INTO salidas VALUES ("000005", 'Lu', '12:00', '15:30', "787");

# INSTANCIAS DE VUELO
INSERT INTO instancias_vuelo VALUES ("000003",'2020-10-10', 'Vi', "ON TIME");
INSERT INTO instancias_vuelo VALUES ("000001",'2020-12-10', 'Lu', "ON TIME");
INSERT INTO instancias_vuelo VALUES ("000005",'2020-12-10', 'Lu', "ON TIME");
INSERT INTO instancias_vuelo VALUES ("000002",'2020-12-9', 'Vi', "ON TIME");
INSERT INTO instancias_vuelo VALUES ("000004",'2020-10-31', 'Sa', "ON TIME");
INSERT INTO instancias_vuelo VALUES ("000001",'2020-9-9', 'Lu', "ON TIME");

#NUEVAS INSTANCIAS
INSERT INTO instancias_vuelo VALUES ("000002",'2020-9-9', 'Vi', "ON TIME");
INSERT INTO instancias_vuelo VALUES ("000002",'2020-3-15', 'Vi', "ON TIME");
INSERT INTO instancias_vuelo VALUES ("000001",'2020-12-24', 'Lu', "ON TIME");
INSERT INTO instancias_vuelo VALUES ("000003",'2020-3-1', 'Vi', "ON TIME");

# RESERVAS
INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo) VALUES (00001, '2020-4-23', '2020-12-23', "PAGADO", "dni", 25091234, 100001 ); -- RESERVA DE LUCIA Schmidt CON EL EMPLEADO Alfonso Prat-Gay
INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo)  VALUES (00002, '2020-3-23', '2020-11-23', "PAGADO", "dni", 16257114, 100002 ); -- RESERVA DE BELEN BRAGANZA CON EL EMPLEADO CARLOS CARABAJAL
INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo)  VALUES (00003, '2020-3-23', '2020-11-23', "PAGADO", "dni", 30671234, 100001 ); -- RESERVA DE Alejandro Cavallo CON EL EMPLEADO Alfonso Prat-Gay

INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo)  VALUES (00004, '2020-7-23', '2020-2-23', "PAGADO","dni", 25091234, 200000 );
INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo)  VALUES (00005, '2020-5-23', '2020-11-23', "PAGADO", "dni", 25091234, 200000 );


INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo)  VALUES (00006, '2020-7-23', '2020-2-23', "PAGADO","dni", 25091234, 200000 );
INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo)  VALUES (00007, '2020-5-23', '2020-11-23', "PAGADO", "dni", 25091234, 200000 );
INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo)  VALUES (00008, '2020-7-23', '2020-2-23', "PAGADO","dni", 25091234, 200000 );
INSERT INTO reservas(numero, fecha, vencimiento, estado, doc_tipo, doc_nro, legajo)  VALUES (00009, '2020-5-23', '2020-11-23', "PAGADO", "dni", 25091234, 200000 );

# BRINDA
INSERT INTO brinda VALUES ("000001", 'Lu', "primera", 931.12, 100); -- DE EZEIZA A MIAMI EN PRIMERA
INSERT INTO brinda VALUES ("000001", 'Vi', "primera", 931.12, 100); -- DE EZEIZA A MIAMI EN PRIMERA
INSERT INTO brinda VALUES ("000001", 'Vi', "turista", 931.12, 100); -- DE EZEIZA A MIAMI EN TURISTA
INSERT INTO brinda VALUES ("000001", 'Lu', "turista", 731.28, 100); -- DE EZEIZA A MIAMI EN TURISTA
INSERT INTO brinda VALUES ("000003", 'Ju', "primera", 236.12, 100); -- DE BAHIA BLANCA A EZEIZA EN PRIMERA
INSERT INTO brinda VALUES ("000003", 'Ju', "turista", 151.12, 100); -- DE BAHIA BLANCA A EZEIZA EN TURISTA
INSERT INTO brinda VALUES ("000003", 'Vi', "turista", 431.99, 100); -- DE BAHIA BLANCA A EZEIZA EN TURISTA
INSERT INTO brinda VALUES ("000003", 'Vi', "primera", 431.99, 100); -- DE BAHIA BLANCA A EZEIZA EN TURISTA
INSERT INTO brinda VALUES ("000002", 'Vi', "turista", 431.99, 100); -- DE EZEIZA A BAHIA BLANCA EN TURISTA
INSERT INTO brinda VALUES ("000004", 'Sa', "turista", 431.99, 100); -- DE MIAMI A EZEIZA EN TURISTA
INSERT INTO brinda VALUES ("000004", 'Sa', "primera", 431.99, 100); -- DE MIAMI A EZEIZA EN TURISTA
INSERT INTO brinda VALUES ("000005", 'Lu', "primera", 531.99, 80); -- DE MIAMI A EZEIZA EN TURISTA


# POSEE
INSERT INTO posee VALUES ("primera", "001");
INSERT INTO posee VALUES ("primera", "002");
INSERT INTO posee VALUES ("primera", "003");
INSERT INTO posee VALUES ("primera", "004");
INSERT INTO posee VALUES ("turista", "001");
INSERT INTO posee VALUES ("turista", "002");

# RESERVA VUELO CLASE
INSERT INTO reserva_vuelo_clase VALUES (00001,"000003",'2020-10-10', "primera" );
INSERT INTO reserva_vuelo_clase VALUES (00002,"000004",'2020-10-31', "turista" );
INSERT INTO reserva_vuelo_clase VALUES (00003,"000001",'2020-9-9', "primera" );
INSERT INTO reserva_vuelo_clase VALUES (00004,"000001",'2020-9-9', "primera" );
INSERT INTO reserva_vuelo_clase VALUES (00005,"000001",'2020-9-9', "primera" );
INSERT INTO reserva_vuelo_clase VALUES (00006,"000001",'2020-9-9', "primera" );
INSERT INTO reserva_vuelo_clase VALUES (00007,"000001",'2020-9-9', "primera" );
INSERT INTO reserva_vuelo_clase VALUES (00008,"000001",'2020-9-9', "primera" );
INSERT INTO reserva_vuelo_clase VALUES (00009,"000001",'2020-9-9', "primera" );