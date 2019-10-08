# Nombres: Alonso, Galiana, Vazquez

# Creación de la Base de Datos
CREATE DATABASE vuelos;

# seleccionando la base de datos sobre la cual se va a trabajar
USE vuelos;

#-------------------------------------------------------------------------
# Creación Tablas para las entidades

CREATE TABLE ubicaciones (
 pais VARCHAR(45) NOT NULL,
 estado VARCHAR(45) NOT NULL,
 ciudad VARCHAR(45) NOT NULL,
 huso INT (2) NOT NULL,   
 
 CONSTRAINT pk_ubicaciones
 PRIMARY KEY (pais,estado,ciudad)

) ENGINE=InnoDB;

CREATE TABLE aeropuertos (
 codigo VARCHAR(3) NOT NULL,
 nombre VARCHAR(45) NOT NULL,
 telefono VARCHAR(45) NOT NULL,
 direccion VARCHAR(45) NOT NULL,
 pais VARCHAR(45) NOT NULL,
 estado VARCHAR(45) NOT NULL,
 ciudad VARCHAR(45) NOT NULL,
 
 CONSTRAINT fk_aeropuertos
 PRIMARY KEY (codigo),
 
 CONSTRAINT fk_aeropuertos_ubicaciones
 FOREIGN KEY (pais,estado,ciudad) REFERENCES ubicaciones (pais,estado,ciudad) 
   ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE vuelos_programados (
 numero VARCHAR(45) NOT NULL, 
 aeropuerto_salida VARCHAR(3) NOT NULL,
 aeropuerto_llegada VARCHAR(3) NOT NULL,
 
 
 CONSTRAINT pk_vuelos 
 PRIMARY KEY (numero),
 
 CONSTRAINT fk_vuelos_aeropuerto_salida
 FOREIGN KEY (aeropuerto_salida) REFERENCES aeropuertos (codigo) 
   ON DELETE RESTRICT ON UPDATE CASCADE,
   
 CONSTRAINT fk_vuelos_aeropuerto_llegada
 FOREIGN KEY (aeropuerto_llegada) REFERENCES aeropuertos (codigo) 
   ON DELETE RESTRICT ON UPDATE CASCADE
 

) ENGINE=InnoDB;

CREATE TABLE modelos_avion (
 modelo VARCHAR(45) NOT NULL,
 fabricante VARCHAR(45) NOT NULL,
 cabinas SMALLINT unsigned NOT NULL,
 cant_asientos SMALLINT unsigned NOT NULL, 
 
 CONSTRAINT pk_modelos_avion
 PRIMARY KEY (modelo)

) ENGINE=InnoDB;

CREATE TABLE salidas (
 vuelo VARCHAR(45) NOT NULL, 
 dia ENUM('do','lu','ma','mi','ju','vi','sa') NOT NULL,
 hora_sale TIME NOT NULL,
 hora_llega TIME NOT NULL,
 modelo_avion VARCHAR(45) NOT NULL, 
 
 CONSTRAINT pk_salidas
 PRIMARY KEY (vuelo,dia),
 
 CONSTRAINT fk_vuelo_salidas_vuelos_programados
 FOREIGN KEY (vuelo) REFERENCES vuelos_programados (numero) 
   ON DELETE RESTRICT ON UPDATE CASCADE,
   
 CONSTRAINT fk_modelo_salidas_modelos_avion
 FOREIGN KEY (modelo_avion) REFERENCES modelos_avion (modelo) 
   ON DELETE RESTRICT ON UPDATE CASCADE
 

) ENGINE=InnoDB;

CREATE TABLE instancias_vuelo (
 vuelo VARCHAR(45) NOT NULL, 
 fecha DATE NOT NULL,
 dia ENUM('do','lu','ma','mi','ju','vi','sa') NOT NULL,
 estado VARCHAR(45),
 

 
 CONSTRAINT pk_instancias_vuelos
 PRIMARY KEY (vuelo,fecha),
 
 CONSTRAINT fk_isntancias_vuelo_vuelos
 FOREIGN KEY (vuelo,dia) REFERENCES salidas (vuelo,dia) 
   ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE clases (
 nombre VARCHAR(45) NOT NULL,
 porcentaje DECIMAL(2,2) unsigned NOT NULL, 
 
 CONSTRAINT pk_clases
 PRIMARY KEY (nombre)

) ENGINE=InnoDB;

CREATE TABLE comodidades (
 codigo INT unsigned NOT NULL,
 descripcion TEXT NOT NULL, 
 
 CONSTRAINT pk_comodidades
 PRIMARY KEY (codigo)

) ENGINE=InnoDB;

CREATE TABLE pasajeros ( 
 doc_tipo VARCHAR(45) NOT NULL,
 doc_nro INT unsigned NOT NULL,
 apellido VARCHAR(45) NOT NULL,
 nombre VARCHAR(45) NOT NULL, 
 direccion VARCHAR(45) NOT NULL,
 telefono VARCHAR(45) NOT NULL,
 nacionalidad VARCHAR(45) NOT NULL,
 
 CONSTRAINT pk_pasajeros
 PRIMARY KEY (doc_tipo,doc_nro)
   
) ENGINE=InnoDB;

CREATE TABLE empleados ( 
 
 legajo INT unsigned NOT NULL,
 password VARCHAR (32) NOT NULL,
 doc_tipo VARCHAR(45) NOT NULL,
 doc_nro INT unsigned NOT NULL,
 apellido VARCHAR(45) NOT NULL,
 nombre VARCHAR(45) NOT NULL, 
 direccion VARCHAR(45) NOT NULL,
 telefono VARCHAR(45) NOT NULL,
 
 CONSTRAINT pk_empleados
 PRIMARY KEY (legajo)
   
) ENGINE=InnoDB;

CREATE TABLE reservas ( 
 
 numero INT unsigned NOT NULL AUTO_INCREMENT,
 fecha DATE NOT NULL,
 vencimiento DATE NOT NULL,
 estado VARCHAR(45) NOT NULL,
 doc_tipo VARCHAR(45) NOT NULL,
 doc_nro INT unsigned NOT NULL,
 legajo INT unsigned NOT NULL,
 
 CONSTRAINT pk_reservas
 PRIMARY KEY (numero),
 
 CONSTRAINT fk_reservas_pasajeros
 FOREIGN KEY (doc_tipo,doc_nro) REFERENCES pasajeros (doc_tipo,doc_nro) 
   ON DELETE RESTRICT ON UPDATE CASCADE,
   
   CONSTRAINT fk_reservas_empleados
 FOREIGN KEY (legajo) REFERENCES empleados (legajo) 
   ON DELETE RESTRICT ON UPDATE CASCADE
   
) ENGINE=InnoDB;

#-------------------------------------------------------------------------
# Creación Tablas para las relaciones

CREATE TABLE brinda (
 vuelo VARCHAR(45) NOT NULL, 
 dia ENUM('do','lu','ma','mi','ju','vi','sa') NOT NULL,
 clase VARCHAR(45) NOT NULL,
 precio DECIMAL(7,2) unsigned NOT NULL,
 cant_asientos INT unsigned NOT NULL, 
 
 CONSTRAINT pk_brinda
 PRIMARY KEY (vuelo,dia,clase),

 CONSTRAINT FK_brinda_salidas
 FOREIGN KEY (vuelo,dia) REFERENCES salidas (vuelo,dia) 
   ON DELETE RESTRICT ON UPDATE CASCADE,
  
 
 CONSTRAINT FK_brinda_clase
 FOREIGN KEY (clase) REFERENCES clases (nombre)
   ON DELETE RESTRICT ON UPDATE RESTRICT
 
) ENGINE=InnoDB;

CREATE TABLE posee (
 clase VARCHAR(45) NOT NULL,
 comodidad INT unsigned NOT NULL, 
 
 CONSTRAINT pk_posee
 PRIMARY KEY (clase,comodidad),
 
 CONSTRAINT FK_posee_clases
 FOREIGN KEY (clase) REFERENCES clases (nombre)
    ON DELETE RESTRICT ON UPDATE CASCADE,
 
 CONSTRAINT FK_posee_comodidades
 FOREIGN KEY (comodidad) REFERENCES comodidades (codigo) 
    ON DELETE CASCADE ON UPDATE CASCADE
 
) ENGINE=InnoDB;

CREATE TABLE reserva_vuelo_clase (
 numero INT unsigned NOT NULL,
 vuelo VARCHAR(45) NOT NULL, 
 fecha_vuelo DATE NOT NULL,
 clase VARCHAR(45) NOT NULL,
 
 CONSTRAINT pk_reserva_vuelo_clase
 PRIMARY KEY (numero,vuelo,fecha_vuelo),
 
 CONSTRAINT FK_res_vue_clases_reserva
 FOREIGN KEY (numero) REFERENCES reservas (numero)
    ON DELETE RESTRICT ON UPDATE CASCADE,
 
 CONSTRAINT FK_res_vue_clases_inst_vuelo
 FOREIGN KEY (vuelo,fecha_vuelo) REFERENCES instancias_vuelo (vuelo,fecha) 
    ON DELETE CASCADE ON UPDATE CASCADE,
 
 CONSTRAINT FK_res_vue_clases_clases
 FOREIGN KEY (clase) REFERENCES clases (nombre)
    ON DELETE RESTRICT ON UPDATE CASCADE	
 
 
) ENGINE=InnoDB;

#-------------------------------------------------------------------------
# Creación de vista vuelos disponibles
CREATE VIEW vuelos_disponibles AS 

	SELECT 
		v_p.numero AS 'Cod. vuelo' ,s.modelo_avion AS 'Modelo avion', i_v.fecha AS 'Fecha', 
		s.hora_sale AS 'Hora salida', s.hora_llega AS 'Hora llegada', timediff(s.hora_llega,s.hora_sale) as 'Tiempo estimado',
		a_1.codigo AS 'Aeropuerto origen', a_1.pais  AS 'Pais origen' , a_1.estado AS 'Estado origen' , 
		a_1.ciudad AS 'Ciudad origen',  a_2.codigo AS 'Aeropuerto destino', a_2.pais 'Pais destino', 
		a_2.estado AS 'Estado destino', a_2.ciudad AS 'Ciudad destino', br.clase AS 'Clase' , br.precio AS 'Precio',
	    TRUNCATE ((br.cant_asientos + c.porcentaje*br.cant_asientos) - (SELECT COUNT(*) FROM reserva_vuelo_clase WHERE 
		reserva_vuelo_clase.clase = br.clase  AND reserva_vuelo_clase.vuelo = i_v.vuelo AND 
		reserva_vuelo_clase.fecha_vuelo = i_v.fecha	) ,0) AS 'Asientos_disponibles'
	
   FROM salidas s, instancias_vuelo i_v, vuelos_programados v_p,  aeropuertos a_1, aeropuertos a_2, brinda br, clases c
   
   WHERE v_p.numero = s.vuelo AND s.vuelo = i_v.vuelo AND
	i_v.dia  = s.dia AND a_1.codigo = v_p.aeropuerto_salida AND
	a_2.codigo = v_p.aeropuerto_llegada AND br.vuelo = v_p.numero AND br.dia = s.dia AND c.nombre = br.clase ;


#-------------------------------------------------------------------------
# Creación de usuarios y otorgamiento de privilegios

#usuario admin
 
 CREATE USER admin@localhost IDENTIFIED BY 'admin';
 
 GRANT ALL PRIVILEGES ON vuelos.* TO admin@localhost;
 
#usuario empleado
 
 CREATE USER empleado@'%' IDENTIFIED BY 'empleado';
 
 GRANT SELECT,INSERT,UPDATE,DELETE on vuelos.pasajeros TO empleado@'%';
 
 GRANT SELECT,INSERT,UPDATE,DELETE on vuelos.reservas TO empleado@'%';
 
 GRANT SELECT,INSERT,UPDATE,DELETE on vuelos.reserva_vuelo_clase TO empleado@'%';
 
#usuario cliente 

 CREATE USER cliente@'%' IDENTIFIED BY 'cliente';
 
 GRANT SELECT ON vuelos.vuelos_disponibles TO cliente@'%';
 
 
 

 
 

		
		
 
 
 
 
 
 
 
 
