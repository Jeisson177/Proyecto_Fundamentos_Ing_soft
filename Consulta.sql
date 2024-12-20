-- Eliminar tabla INGREDIENTE_PLATO (dependiente)`proyecto ingesoft`
DROP TABLE IF EXISTS INGREDIENTE_PLATO;

-- Eliminar tabla Cliente (dependiente)`proyecto ingesoft`
DROP TABLE IF EXISTS RESERVA;

-- Eliminar tabla INGREDIENTE_INV (dependiente)`proyecto ingesoft`
DROP TABLE IF EXISTS INGREDIENTE_INV;

-- Eliminar tabla usuario (dependiente)`proyecto ingesoft`
DROP TABLE IF EXISTS Usuario;

-- Eliminar tabla Mesa (dependiente)`proyecto ingesoft`
DROP TABLE IF EXISTS MESA;

-- Eliminar tabla Mesa (dependiente)`proyecto ingesoft`
DROP TABLE IF EXISTS MENU_PLATO;

-- Eliminar tabla INGREDIENTE (tabla principal)
DROP TABLE IF EXISTS MENU;

-- Eliminar tabla INGREDIENTE (tabla principal)
DROP TABLE IF EXISTS ingrediente;

-- Eliminar tabla PLATO (tabla principal)
DROP TABLE IF EXISTS plato;

-- Eliminar tabla Horarios 
DROP TABLE IF EXISTS Horarios;

-- Eliminar tabla dias 
DROP TABLE IF EXISTS Dias;
-- ----------------------------------- Creación de tablas
-- Creación tabla ingrediente
CREATE TABLE INGREDIENTE (
    ID_INGREDIENTE INT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(255) NOT NULL
);

-- Creación tabla ingredientes el inventario y compras
CREATE TABLE INGREDIENTE_INV(
	ID_INGREDIENTE_INV INT AUTO_INCREMENT PRIMARY KEY,
	ID_INGREDIENTE INT,
	CANTIDAD_INV INT,
	FECHA_VEN DATE,
	FECHA_INGRESO DATE,
	UNIDAD VARCHAR(10) NOT NULL,
   CONSTRAINT chk_unidad CHECK (UNIDAD IN ('ml', 'g', 'Unidades')),
	FOREIGN KEY (ID_INGREDIENTE) REFERENCES INGREDIENTE(ID_INGREDIENTE)
);

-- Creación tabla Plato
CREATE TABLE PLATO(
    ID_PLATO INT AUTO_INCREMENT PRIMARY KEY,
    NOMBRE VARCHAR(255),
    DESCRIPCION VARCHAR(255),
    PRECIO INT, -- en COP
    CATEGORIA VARCHAR(255),
	 CONSTRAINT chk_cat CHECK (CATEGORIA IN ('Entrada', 'Plato fuerte', 'Postre','Bebida'))
);

-- Creación tabla INGREDIENTE_plato
CREATE TABLE INGREDIENTE_PLATO(
    ID_PLATO INT,
    ID_INGREDIENTE INT,
    Cantidad DECIMAL(10, 2),
    PRIMARY KEY (ID_PLATO, ID_INGREDIENTE),
    FOREIGN KEY (ID_PLATO) REFERENCES PLATO(ID_PLATO),
    FOREIGN KEY (ID_INGREDIENTE) REFERENCES INGREDIENTE(ID_INGREDIENTE)
);

-- Creación tabla mesa
CREATE TABLE MESA(
	 ID_MESA INT AUTO_INCREMENT PRIMARY KEY, -- preguntar
	 CAPACIDAD INT,
    UBICACION_X INT,
    UBICACION_Y INT
);

-- Creación de tabla usuario
CREATE TABLE Usuario(
	 ID_USUARIO INT AUTO_INCREMENT PRIMARY KEY,
	 NOMBRE VARCHAR(255),
	 EMAIL VARCHAR(255),
	 TELEFONO VARCHAR(255),
	 ROL VARCHAR(255),
	 CONTRASENA VARCHAR(255),
	 CONSTRAINT chk_rol CHECK (ROL IN ('Cliente', 'Mesero', 'Administrador'))
);

-- Creación de la tabla reserva
CREATE TABLE RESERVA(
	 ID_RESERVA INT AUTO_INCREMENT PRIMARY KEY,
	 ID_CLIENTE INT,
	 ID_MESA INT,
	 FECHA_HORA DATETIME,
	 -- ESTADO VARCHAR (255),
	 FOREIGN KEY (ID_MESA) REFERENCES MESA(ID_MESA),
	 FOREIGN KEY (ID_CLIENTE) REFERENCES USUARIO(ID_USUARIO)
	 -- CONSTRAINT chk_est CHECK (ESTADO IN ('Confirmado', 'Cancelado', 'Pendiente'))
);	

-- Creación tabla menu
CREATE TABLE MENU(
	 ID_MENU INT PRIMARY KEY,
	 NOMBRE VARCHAR (255),
	 DESCRIPCION VARCHAR (255)
);

-- Creación tabla menu_plato
CREATE TABLE MENU_PLATO(
	 ID_MENU INT,
    ID_PLATO INT,
    PRIMARY KEY (ID_PLATO, ID_MENU),
    FOREIGN KEY (ID_PLATO) REFERENCES PLATO(ID_PLATO),
    FOREIGN KEY (ID_MENU) REFERENCES MENU(ID_MENU)
);

-- Creación tabla días 
CREATE TABLE Dias (
    id_dia INT AUTO_INCREMENT PRIMARY KEY,
    nombre_dia VARCHAR(20) NOT NULL,
    intervalo INT -- intervalo que se demora un cliente en comer
);

-- Creación tabla horarios
CREATE TABLE Horarios (
    id_horario INT AUTO_INCREMENT PRIMARY KEY,
    id_dia INT NOT NULL,
    hora_apertura TIME NOT NULL,
    hora_cierre TIME NOT NULL,
    CONSTRAINT fk_dia FOREIGN KEY (id_dia) REFERENCES Dias(id_dia) ON DELETE CASCADE
);


-- --------------------------------------------------------------------------- Inserción de duplas
-- Inserción de INGREDIENTEs
INSERT INTO INGREDIENTE (nombre) VALUES ('Avellana');
INSERT INTO INGREDIENTE (nombre) VALUES ('Limon');
INSERT INTO INGREDIENTE (nombre) VALUES ('Frambuesa');
INSERT INTO INGREDIENTE (nombre) VALUES ('Fresa');
INSERT INTO INGREDIENTE (nombre) VALUES ('Chocolate picante dulce');
INSERT INTO INGREDIENTE (nombre) VALUES ('Queso Mascarpone');
INSERT INTO INGREDIENTE (nombre) VALUES ('Huevos');
INSERT INTO INGREDIENTE (nombre) VALUES ('Azucar');
INSERT INTO INGREDIENTE (nombre) VALUES ('Bizcochos de soletilla');
INSERT INTO INGREDIENTE (nombre) VALUES ('Cafe Expreso');
INSERT INTO INGREDIENTE (nombre) VALUES ('Cacao en polvo');
INSERT INTO INGREDIENTE (nombre) VALUES ('Crema de leche');
INSERT INTO INGREDIENTE (nombre) VALUES ('Esencia de Vainilla');
INSERT INTO INGREDIENTE (nombre) VALUES ('Frutos rojos');
INSERT INTO INGREDIENTE (nombre) VALUES ('Helado de vainilla');
INSERT INTO INGREDIENTE (nombre) VALUES ('Ricotta');
INSERT INTO INGREDIENTE (nombre) VALUES ('Azucar Glass');
INSERT INTO INGREDIENTE (nombre) VALUES('Canela en polvo');
INSERT INTO INGREDIENTE (nombre) VALUES ('Chocolate rallado');
INSERT INTO INGREDIENTE (nombre) VALUES ('Masa de canolli');
INSERT INTO INGREDIENTE (nombre) VALUES('Lomo de res');
INSERT INTO INGREDIENTE (nombre) VALUES('Salmon');
INSERT INTO INGREDIENTE (nombre) VALUES('Aceite de Oliva');
INSERT INTO INGREDIENTE (nombre) VALUES('Zumo de limon');
INSERT INTO INGREDIENTE (nombre) VALUES('Parmesano');
INSERT INTO INGREDIENTE (nombre) VALUES('Rugula');
INSERT INTO INGREDIENTE (nombre) VALUES('Sal');
INSERT INTO INGREDIENTE (nombre) VALUES('Pimienta');
INSERT INTO INGREDIENTE (nombre) VALUES ('Harina');
INSERT INTO INGREDIENTE (nombre) VALUES ('Agua');
INSERT INTO INGREDIENTE (nombre) VALUES ('Levadura');
INSERT INTO INGREDIENTE (nombre) VALUES('Romero');
INSERT INTO INGREDIENTE (nombre) VALUES ('Sal gruesa');
INSERT INTO INGREDIENTE (nombre) VALUES ('Pasas');
INSERT INTO INGREDIENTE (nombre) VALUES('Frutos secos');
INSERT INTO INGREDIENTE (nombre) VALUES ('Cebolla');
INSERT INTO INGREDIENTE (nombre) VALUES ('Tomates cherry');
INSERT INTO INGREDIENTE (nombre) VALUES ('Aceitunas negras');
INSERT INTO INGREDIENTE (nombre) VALUES ('Lechuga Romana');
INSERT INTO INGREDIENTE (nombre) VALUES ('Pechuga de pollo');
INSERT INTO INGREDIENTE (nombre) VALUES ('Crutones');
INSERT INTO INGREDIENTE (nombre) VALUES ('Aderezo cesar');
INSERT INTO INGREDIENTE (nombre) VALUES ('Champinones');
INSERT INTO INGREDIENTE (nombre) VALUES ('Queso de cabra');
INSERT INTO INGREDIENTE (nombre) VALUES ('Nueces');
INSERT INTO INGREDIENTE (nombre) VALUES ('Vinagreta balsamica');
INSERT INTO INGREDIENTE (nombre) VALUES ('Ventresca de atun');
INSERT INTO INGREDIENTE (nombre) VALUES ('Cebolla morada');
INSERT INTO INGREDIENTE (nombre) VALUES('Pimientos');
INSERT INTO INGREDIENTE (nombre) VALUES ('Queso brie');
INSERT INTO INGREDIENTE (nombre) VALUES ('Queso gorgonzola');
INSERT INTO INGREDIENTE (nombre) VALUES ('Queso Pecorino');
INSERT INTO INGREDIENTE (nombre) VALUES ('Mermelada');
INSERT INTO INGREDIENTE (nombre) VALUES ('Miel');
INSERT INTO INGREDIENTE (nombre) VALUES ('Crakers');
INSERT INTO INGREDIENTE (nombre) VALUES ('Queso mozarella');
INSERT INTO INGREDIENTE (nombre) VALUES ('Anchoas');
INSERT INTO INGREDIENTE (nombre) VALUES ('Salsa de tomate');
INSERT INTO INGREDIENTE (nombre) VALUES ('Albahaca');
INSERT INTO INGREDIENTE (nombre) VALUES ('Peperonni');
INSERT INTO INGREDIENTE (nombre) VALUES ('Tomates');
INSERT INTO INGREDIENTE (nombre) VALUES('Spaguetti');
INSERT INTO INGREDIENTE (nombre) VALUES ('Panceta');
INSERT INTO INGREDIENTE (nombre) VALUES ('Fettuccine');
INSERT INTO INGREDIENTE (nombre) VALUES ('Tomates en conserva');
INSERT INTO INGREDIENTE (nombre) VALUES ('Alcaparras');
INSERT INTO INGREDIENTE (nombre) VALUES ('Trenette');
INSERT INTO INGREDIENTE (nombre) VALUES ('Pesto');
INSERT INTO INGREDIENTE (nombre) VALUES ('Bolognesa');
INSERT INTO INGREDIENTE (nombre) VALUES ('Salsa bechamel');
INSERT INTO INGREDIENTE (nombre) VALUES ('Carne molida');
INSERT INTO INGREDIENTE (nombre) VALUES ('Arroz arborio');
INSERT INTO INGREDIENTE (nombre) VALUES ('Caldo de verduras');
INSERT INTO INGREDIENTE (nombre) VALUES ('Caldo de pescado');
INSERT INTO INGREDIENTE (nombre) VALUES ('Caldo de setas');
INSERT INTO INGREDIENTE (nombre) VALUES('Esparragos');
INSERT INTO INGREDIENTE (nombre) VALUES ('Eneldo');
INSERT INTO INGREDIENTE (nombre) VALUES ('Setas variadas');
INSERT INTO INGREDIENTE (nombre) VALUES ('Agua con gas');
INSERT INTO INGREDIENTE (nombre) VALUES ('Agua');
INSERT INTO INGREDIENTE (nombre) VALUES ('Champagne');
INSERT INTO INGREDIENTE (nombre) VALUES ('Vino Rosado');
INSERT INTO INGREDIENTE (nombre) VALUES ('Vino tinto');
INSERT INTO INGREDIENTE (nombre) VALUES ('Vino Blanco');
INSERT INTO INGREDIENTE (nombre) VALUES ('Quatro');
INSERT INTO INGREDIENTE (nombre) VALUES ('Coca Cola');
INSERT INTO INGREDIENTE (nombre) VALUES ('Fanta Naranja');

-- Inserción de platos
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES ('Gelato Bacio (Avellana)', 'Helado de avellana', 10000, 'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES ('Gelato Limone (Limon)', 'Helado de limon', 9000,'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES ('Gelato Lampone (Frambuesa)', 'Helado de frambuesa',8000,'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Gelato Fragola (Fresa)', 'Helado de fresa',8000,'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Gelato Cioccolato con Peperoncino', 'Helado de chocolate picante dulce',1100,'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Tiramisu', 'Postre clasico italiano con queso mascarpone y cafe', 15000,'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Panna Cotta', 'Postre tradicional italiano a base de nata',10000 ,'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Affogato', 'Helado de vainilla con cafe',15000,'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Cannoli', 'Rollo de masa con queso ricotta y canela',12000,'Postre');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Carpaccio de Res', 'Laminas finas de res marinadas', 8000, 'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Carpaccio de Salmon', 'Laminas finas de salmon marinadas',10000 ,'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Tabla de Quesos', 'Seleccion de quesos variados', 15000,'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Ensalada Cesar Romana', 'Ensalada clasica con pollo, lechuga romana y crutones',4000 ,'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Ensalada de Portobello', 'Ensalada de champinones portobello, rugula y queso de cabra', 5000,'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Ensalada de Ventresca', 'Ensalada con ventresca de atun, tomates cherry y aceitunas', 2000, 'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Cuatro Quesos Personal', 'Pizza con mozzarella, gorgonzola, parmesano y queso de cabra',11000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Margarita Personal', 'Pizza clasica con salsa de tomate, mozzarella y albahaca', 11000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Pepperoni Personal', 'Pizza con pepperoni, salsa de tomate y mozzarella',11000, 'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Napolitana Personal', 'Pizza con anchoas, aceitunas y tomate triturado',11000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Focaccia Genovese', 'Focaccia con aceite de oliva y sal', 2000,'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Focaccia Dolce', 'Focaccia dulce con azucar y pasas', 3000,'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Focaccia Veneta', 'Focaccia con cebolla caramelizada', 5000,'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Focaccia Pugliese', 'Focaccia con tomates cherry y aceitunas negras', 3000,'Entrada');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pasta alla Carbonara', 'Espagueti con panceta, huevo y queso pecorino', 15000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Fettuccine alla Puttanesca', 'Fettuccine con anchoas, aceitunas y alcaparras', 20000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Trenette al Pesto', 'Pasta Trenette con salsa pesto y parmesano', 30000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Lasagna Bolognese', 'Lasana con salsa bolonesa', 20000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Lasagna Mixta', 'Lasana con carne molida y pollo', 40000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Lasagna de Pollo', 'Lasana con pollo desmenuzado y bechamel', 20000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Risotto Vegetariano', 'Risotto con verduras frescas', 10000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Risotto de Salmon', 'Risotto con salmon ahumado y eneldo', 50000, 'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Risotto de Hongos', 'Risotto con setas variadas', 30000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Agua', 'Agua en botella', 4000,'Bebida');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Agua con gas', 'Agua carbonatada en botella', 3000,'Bebida');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Vino rosado', 'Botella de vino rosado', 30000,'Bebida');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Vino tinto', 'Botella de vino tinto', 40000,'Bebida');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Vino blanco', 'Botella de vino blanco', 20000,'Bebida');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Champagne', 'Bebida alcoholica en botella', 20000,'Bebida');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Cuatro Quesos Mediana', 'Pizza con mozzarella, gorgonzola, parmesano y queso de cabra',14000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Margarita Mediana', 'Pizza clasica con salsa de tomate, mozzarella y albahaca', 14000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Pepperoni Mediana', 'Pizza con pepperoni, salsa de tomate y mozzarella',14000, 'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Napolitana Mediana', 'Pizza con anchoas, aceitunas y tomate triturado',14000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Cuatro Quesos Familiar', 'Pizza con mozzarella, gorgonzola, parmesano y queso de cabra',18000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Margarita Familiar', 'Pizza clasica con salsa de tomate, mozzarella y albahaca', 18000,'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Pepperoni Familiar', 'Pizza con pepperoni, salsa de tomate y mozzarella',18000, 'Plato fuerte');
INSERT INTO PLATO (nombre, descripcion, precio, categoria) VALUES('Pizza Napolitana Familiar', 'Pizza con anchoas, aceitunas y tomate triturado',18000,'Plato fuerte');

-- Inserción INGREDIENTE_plato
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (1, 1, 100); -- Avellana (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (1, 15, 150); -- Helado de vainilla (150ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (2, 2, 100); -- Limón (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (2, 15, 150); -- Helado de vainilla (150ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (3, 3, 100); -- Frambuesa (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (3, 15, 150); -- Helado de vainilla (150ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (4, 4, 100); -- Fresa (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (4, 15, 150); -- Helado de vainilla (150ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (5, 5, 100); -- Chocolate picante dulce (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (5, 15, 150); -- Helado de vainilla (150ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (6, 6, 100); -- Queso Mascarpone (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (6, 7, 2);   -- Huevos (2 unidades)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (6, 8, 50);  -- Azúcar (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (6, 9, 2);   -- Bizcochos de soletilla (2 unidades)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (6, 10, 60); -- Café expreso (60ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (6, 11, 2);  -- Cacao en polvo (2g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (7, 12, 150); -- Crema de leche (150ml)
INSERT INTO ingrediente_plato (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (7, 13, 1);   -- Esencia de vainilla (1ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (7, 8, 30);   -- Azúcar (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (8, 15, 100); -- Helado de vainilla (100ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (8, 10, 30);  -- Café expreso (30ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (9, 16, 50);  -- Ricotta (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (9, 20, 30);  -- Masa de cannoli (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (9, 17, 10);  -- Azúcar Glass (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (9, 18, 1);   -- Canela en polvo (1g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (10, 21, 70);  -- Lomo de res (70g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (10, 23, 10);  -- Aceite de oliva (10ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (10, 24, 5);   -- Zumo de limón (5ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (10, 27, 2);   -- Sal (2g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (10, 28, 1);   -- Pimienta (1g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (11, 22, 70);  -- Salmón (70g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (11, 23, 10);  -- Aceite de oliva (10ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (11, 24, 5);   -- Zumo de limón (5ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (11, 27, 2);   -- Sal (2g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (11, 32, 1);   -- Romero (1g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (12, 25, 50);   -- Parmesano (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (12, 44, 50);   -- Queso de cabra (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (12, 50 ,50);   -- Queso brie (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (12, 51, 50);   -- Queso gorgonzola (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (13, 39, 100);  -- Lechuga Romana (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (13, 40, 150);  -- Pechuga de pollo (150g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (13, 41, 10);   -- Crutones (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (13, 42, 20);   -- Aderezo César (20ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (13, 25, 10);   -- Parmesano (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (14, 43, 100);  -- Champiñones (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (14, 26, 10);   -- Rúcula (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (14, 44, 20);   -- Queso de cabra (20g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (14, 46, 10);   -- Vinagreta balsámica (10ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (15, 47, 50);   -- Ventresca de atún (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (15, 37, 30);   -- Tomates cherry (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (15, 38, 10);   -- Aceitunas negras (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (15, 46, 10);   -- Vinagreta balsámica (10ml)
INSERT INTO ingrediente_plato (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (16, 56, 100);  -- Mozzarella (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (16, 51, 30);   -- Gorgonzola (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (16, 25, 20);   -- Parmesano (20g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (16, 44, 30);   -- Queso de cabra (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (17, 58, 50);   -- Salsa de tomate (50ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (17, 56, 100);  -- Mozzarella (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (17, 59, 5);    -- Albahaca (5g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (18, 58, 50);   -- Salsa de tomate (50ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (18, 56, 100);  -- Mozzarella (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (18, 60, 30);   -- Pepperoni (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (19, 58, 50);   -- Salsa de tomate (50ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (19, 57, 20);   -- Anchoas (20g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (19, 38, 10);   -- Aceitunas negras (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (20, 23, 20);   -- Aceite de oliva (20ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (20, 27, 5);    -- Sal (5g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (20, 29, 100);  -- Harina (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (21, 8, 30);   -- Azúcar (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (21, 34, 20);  -- Pasas (20g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (21, 29, 100); -- Harina (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (21, 23, 10);  -- Aceite de oliva (10ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (22, 36, 30);   -- Cebolla (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (22, 29, 100);  -- Harina (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (22, 23, 10);   -- Aceite de oliva (10ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (23, 37, 30);   -- Tomates cherry (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (23, 38, 10);   -- Aceitunas negras (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (23, 29, 100);  -- Harina (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (23, 23, 10);   -- Aceite de oliva (10ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (24, 62, 100);  -- Pasta (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (24, 63, 30);   -- Pancetta (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (24, 7, 1);     -- Huevo (1 unidad)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (24, 25, 20);   -- Parmesano (20g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (24, 28, 1);    -- Pimienta (1g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (25, 62, 100);  -- Pasta (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (25, 32, 10);   -- Albahaca (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (25, 30, 30);   -- Piñones (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (25, 23, 10);   -- Aceite de oliva (10ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (25, 25, 20);   -- Parmesano (20g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (26, 67, 100);  -- Trenette (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (26, 68, 30);   -- Pesto (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (26, 25, 10);   -- Parmesano (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (27, 69, 100);  -- Salsa Bolognesa (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (27, 70, 30);   -- Salsa Bechamel (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (27, 29, 20);   -- Harina (20g, para la pasta)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (28, 71, 50);   -- Carne molida (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (28, 40, 50);   -- Pechuga de pollo (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (28, 70, 30);   -- Salsa Bechamel (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (28, 29, 20);   -- Harina (20g, para la pasta)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (29, 40, 100);  -- Pechuga de pollo (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (29, 70, 50);   -- Salsa Bechamel (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (29, 29, 20);   -- Harina (20g, para la pasta)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (30, 72, 100);  -- Arroz Arborio (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (30, 73, 200);  -- Caldo de verduras (200ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (30, 36, 20);   -- Cebolla (20g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (30, 37, 20);   -- Tomates cherry (20g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (30, 76, 30);   -- Espárragos (30g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (31, 72, 100);  -- Arroz Arborio (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (31, 74, 200);  -- Caldo de pescado (200ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (31, 22, 50);   -- Salmón (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (31, 77, 5);    -- Eneldo (5g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (32, 72, 100);  -- Arroz Arborio (100g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (32, 75, 200);  -- Caldo de setas (200ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (32, 78, 50);   -- Setas variadas (50g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (33, 80, 1);   --  Agua (1u)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (34, 79, 50);   -- Agua con gas(1u)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (35, 82, 50);   -- Rosado (1u)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (36, 83, 50);   -- Tinto (1u)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (37, 84, 50);   -- blanco (1u)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (38, 81, 50);   -- champagne (1u)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (39, 56, 200);  -- Mozzarella (200g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (39, 51, 60);   -- Gorgonzola (60g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (39, 25, 40);   -- Parmesano (40g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (39, 44, 60);   -- Queso de cabra (60g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (40, 58, 100);   -- Salsa de tomate (100ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (40, 56, 200);  -- Mozzarella (200g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (40, 59, 10);    -- Albahaca (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (41, 58, 100);   -- Salsa de tomate (100ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (41, 56, 200);  -- Mozzarella (200g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (41, 60, 60);   -- Pepperoni (60g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (42, 58, 100);   -- Salsa de tomate (100ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (42, 57, 40);   -- Anchoas (40g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (42, 38, 10);   -- Aceitunas negras (10g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (43, 56, 300);  -- Mozzarella (300g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (43, 51, 90);   -- Gorgonzola (90g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (43, 25, 60);   -- Parmesano (60g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (43, 44, 90);   -- Queso de cabra (90g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (44, 58, 150);   -- Salsa de tomate (150ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (44, 56, 300);  -- Mozzarella (300g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (44, 59, 15);    -- Albahaca (15g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (45, 58, 150);   -- Salsa de tomate (150ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (45, 56, 300);  -- Mozzarella (300g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (45, 60, 90);   -- Pepperoni (90g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (46, 58, 150);   -- Salsa de tomate (150ml)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (46, 57, 60);   -- Anchoas (60g)
INSERT INTO INGREDIENTE_PLATO (ID_PLATO, ID_INGREDIENTE, Cantidad) VALUES (46, 38, 30);   -- Aceitunas negras (30g)

-- Inserciones en la tabla INGREDIENTE_INV
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(1, 5000, '2025-12-31', '2024-09-22', 'g');   -- Avellana
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(2, 1000, '2025-12-31', '2024-09-22', 'g'); -- Limon
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(3, 3000, '2025-11-30', '2024-09-22', 'g');   -- Frambuesa
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(4, 2500, '2025-10-31', '2024-09-22', 'g');   -- Fresa
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(5, 200, '2026-01-31', '2024-09-22', 'g');    -- Chocolate picante dulce
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(6, 50, '2025-07-31', '2024-09-22', 'g');     -- Queso Mascarpone
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(7, 500, '2024-12-31', '2024-09-22', 'Unidades'); -- Huevos
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(8, 3000, '2025-08-31', '2024-09-22', 'g');   -- Azucar
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(9, 1000, '2025-06-30', '2024-09-22', 'g');   -- Bizcochos de soletilla
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(10, 500, '2024-12-31', '2024-09-22', 'ml');  -- Cafe Expreso
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(11, 1000, '2025-11-30', '2024-09-22', 'g');  -- Cacao en polvo
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(12, 2000, '2025-04-30', '2024-09-22', 'ml'); -- Crema de leche
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(13, 1000, '2025-09-30', '2024-09-22', 'ml'); -- Esencia de Vainilla
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(14, 500, '2025-07-31', '2024-09-22', 'g');   -- Frutos rojos
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(15, 100, '2025-12-31', '2024-09-22', 'g');   -- Helado de vainilla
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(16, 500, '2025-11-30', '2024-09-22', 'g');   -- Ricotta
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(17, 1000, '2025-06-30', '2024-09-22', 'g');  -- Azucar Glass
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(18, 200, '2026-02-28', '2024-09-22', 'g');   -- Canela en polvo
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(19, 200, '2025-03-31', '2024-09-22', 'g');   -- Chocolate rallado
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(20, 300, '2025-04-30', '2024-09-22', 'g');   -- Masa de canolli
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(21, 50, '2024-12-31', '2024-09-22', 'g');    -- Lomo de res
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(22, 100, '2025-03-31', '2024-09-22', 'g');   -- Salmon
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(23, 5000, '2025-06-30', '2024-09-22', 'ml'); -- Aceite de Oliva
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(24, 1000, '2024-12-31', '2024-09-22', 'ml'); -- Zumo de limon
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(25, 1000, '2025-11-30', '2024-09-22', 'g');  -- Parmesano
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(26, 2000, '2024-11-30', '2024-09-22', 'g');  -- Rugula
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(27, 500, '2024-10-31', '2024-09-22', 'g');   -- Sal
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(28, 500, '2024-12-31', '2024-09-22', 'g');   -- Pimienta
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(29, 3000, '2025-05-31', '2024-09-22', 'g');  -- Harina
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(30, 10000, '2025-12-31', '2024-09-22', 'ml'); -- Agua
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(31, 500, '2025-03-31', '2024-09-22', 'g');   -- Levadura
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(32, 1000, '2025-09-30', '2024-09-22', 'g');  -- Romero
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(33, 3000, '2025-07-31', '2024-09-22', 'g');  -- Sal gruesa
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(34, 1000, '2025-01-31', '2024-09-22', 'g');  -- Pasas
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(35, 2000, '2024-12-31', '2024-09-22', 'g');  -- Frutos secos
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(36, 2000, '2025-02-28', '2024-09-22', 'g');  -- Cebolla
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(37, 1000, '2025-01-31', '2024-09-22', 'g');  -- Tomates cherry
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(38, 500, '2025-08-31', '2024-09-22', 'g');  -- Aceitunas negras
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(39, 1000, '2024-12-31', '2024-09-22', 'g');  -- Lechuga Romana
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(40, 1000, '2024-11-30', '2024-09-22', 'g');  -- Pechuga de pollo
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(41, 200, '2025-07-31', '2024-09-22', 'g');   -- Crutones
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(42, 500, '2025-12-31', '2024-09-22', 'ml');  -- Aderezo cesar
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(43, 2000, '2025-05-31', '2024-09-22', 'g');  -- Champinones
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(44, 1000, '2025-06-30', '2024-09-22', 'g');  -- Queso de cabra
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(45, 500, '2025-03-31', '2024-09-22', 'g');   -- Nueces
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(46, 1000, '2025-04-30', '2024-09-22', 'ml'); -- Vinagreta balsamica
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(47, 500, '2025-07-31', '2024-09-22', 'g');   -- Ventresca de atun
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(48, 1000, '2025-01-31', '2024-09-22', 'g');  -- Cebolla morada
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(49, 2000, '2025-06-30', '2024-09-22', 'g');  -- Pimientos
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(50, 1000, '2025-11-30', '2024-09-22', 'g');  -- Queso brie
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(51, 500, '2026-02-28', '2024-09-22', 'g');   -- Queso gorgonzola
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(52, 500, '2025-09-30', '2024-09-22', 'g');   -- Queso Pecorino
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(53, 500, '2025-04-30', '2024-09-22', 'g');   -- Mermelada
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(54, 500, '2025-12-31', '2024-09-22', 'ml');  -- Miel
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(55, 300, '2025-05-31', '2024-09-22', 'g');   -- Crakers
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(56, 2000, '2025-10-31', '2024-09-22', 'g');  -- Queso mozarella
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(57, 500, '2025-09-30', '2024-09-22', 'g');   -- Anchoas
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(58, 5000, '2025-07-31', '2024-09-22', 'ml'); -- Salsa de tomate
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(59, 1000, '2025-02-28', '2024-09-22', 'g');  -- Albahaca
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(60, 2000, '2025-06-30', '2024-09-22', 'g');  -- Peperonni
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(61, 1000, '2025-08-31', '2024-09-22', 'g');  -- Tomates
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(62, 5000, '2025-12-31', '2024-09-22', 'g');  -- Spaguetti
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(63, 2000, '2024-12-31', '2024-09-22', 'g');  -- Panceta
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(64, 1000, '2025-05-31', '2024-09-22', 'g');  -- Fettuccine
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(65, 1000, '2025-03-31', '2024-09-22', 'g');  -- Tomates en conserva
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(66, 500, '2025-08-31', '2024-09-22', 'g');   -- Alcaparras
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(67, 1000, '2025-07-31', '2024-09-22', 'g');  -- Trenette
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(68, 500, '2025-10-31', '2024-09-22', 'g');   -- Pesto
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(69, 5000, '2025-12-31', '2024-09-22', 'g');  -- Bolognesa
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(70, 1000, '2025-04-30', '2024-09-22', 'g');  -- Salsa bechamel
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(71, 5000, '2024-12-31', '2024-09-22', 'g');  -- Carne molida
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(72, 500, '2025-06-30', '2024-09-22', 'g');   -- Arroz arborio
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(73, 10000, '2025-01-31', '2024-09-22', 'ml'); -- Caldo de verduras
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(74, 1000, '2025-03-31', '2024-09-22', 'ml'); -- Caldo de pescado
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(75, 1000, '2025-06-30', '2024-09-22', 'ml'); -- Caldo de setas
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(76, 1000, '2025-04-30', '2024-09-22', 'g');  -- Esparragos
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(77, 500, '2025-07-31', '2024-09-22', 'g');   -- Eneldo
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(78, 1000, '2025-03-31', '2024-09-22', 'g');  -- Setas variadas
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(79, 50, '2024-12-31', '2024-09-22', 'Unidades');  -- Agua con gas
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(80, 100, '2024-12-31', '2024-09-22', 'Unidades'); -- Agua
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(81, 20, '2026-01-31', '2024-09-22', 'Unidades');  -- Champagne
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(82, 25, '2025-03-31', '2024-09-22', 'Unidades');  -- Vino Rosado
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(83, 25, '2025-06-30', '2024-09-22', 'Unidades');  -- Vino tinto
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(84, 25, '2025-02-28', '2024-09-22', 'Unidades'); -- Vino Blanco
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(85, 50, '2025-12-31', '2024-09-22', 'Unidades');  -- Quatro
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(86, 100, '2024-11-30', '2024-09-22', 'Unidades'); -- Coca Cola
INSERT INTO INGREDIENTE_INV (ID_INGREDIENTE, CANTIDAD_INV, FECHA_VEN, FECHA_INGRESO, UNIDAD) VALUES(87, 50, '2025-10-31', '2024-09-22', 'Unidades');  -- Fanta Naranja

-- Inserción de mesas
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (10, 318.0, 137.0); -- bntm1
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 332.0, 171.0); -- bntm2
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 313.0, 188.0); -- bntm3
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 331.0, 189.0); -- bntm4
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 295.0, 205.0); -- bntm5
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 313.0, 205.0); -- bntm6
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 332.0, 205.0); -- bntm7
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 295.0, 224.0); -- bntm8
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 313.0, 225.0); -- bntm9
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 332.0, 223.0); -- bntm10
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 296.0, 242.0); -- bntm11
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 313.0, 243.0); -- bntm12
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (4, 332.0, 243.0); -- bntm13
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (6, 358.0, 174.0); -- bntm14
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (6, 390.0, 182.0); -- bntm15
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (6, 411.0, 211.0); -- bntm16
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (6, 411.0, 242.0); -- bntm17
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (6, 389.0, 275.0); -- bntm18
INSERT INTO mesa (CAPACIDAD, UBICACION_X, UBICACION_Y) VALUES (6, 359.0, 283.0); -- bntm19


-- Inserciones en la tabla usuario
INSERT INTO Usuario (NOMBRE, EMAIL, TELEFONO, ROL, CONTRASENA) VALUES
('Juan Perez', 'juanperez@email.com', '555-1234', 'Cliente', 'contrasena1'),
('Ana Garcia', 'anagarcia@email.com', '555-2345', 'Cliente', 'contrasena2'),
('Luis Martinez', 'luismartinez@email.com', '555-3456', 'Cliente', 'contrasena3'),
('Maria Lopez', 'marialopez@email.com', '555-4567', 'Cliente', 'contrasena4'),
('Carlos Fernandez', 'carlosf@email.com', '555-5678', 'Cliente', 'contrasena5'),
('Sofia Rodriguez', 'sofiarodriguez@email.com', '555-6789', 'Cliente', 'contrasena6'),
('Javier Morales', 'javiermorales@email.com', '555-7890', 'Cliente', 'contrasena7'),
('Laura Gomez', 'lauragomez@email.com', '555-8901', 'Cliente', 'contrasena8'),
('Pedro Sanchez', 'pedrosanchez@email.com', '555-9012', 'Cliente', 'contrasena9'),
('Marta Ruiz', 'martaruiz@email.com', '555-0123', 'Cliente', 'contrasena10'),
('Diego Torres', 'diegotorres@email.com', '555-1235', 'Cliente', 'contrasena11'),
('Carmen Diaz', 'carmendiaz@email.com', '555-2346', 'Cliente', 'contrasena12'),
('Rafael Jimenez', 'rafaeljimenez@email.com', '555-3457', 'Cliente', 'contrasena13'),
('Paula Silva', 'paulasilva@email.com', '555-4568', 'Cliente', 'contrasena14'),
('Fernando Castro', 'fernandocastro@email.com', '555-5679', 'Cliente', 'contrasena15'),
('Natalia Mendoza', 'nataliamendoza@email.com', '555-6780', 'Cliente', 'contrasena16'),
('Gustavo Ortega', 'gustavoortega@email.com', '555-7891', 'Cliente', 'contrasena17'),
('Isabel Herrera', 'isabelherrera@email.com', '555-8902', 'Cliente', 'contrasena18'),
('Fernando Jimenez', 'fernandojimenez@email.com', '555-9013', 'Cliente', 'contrasena19'),
('Alma Vazquez', 'almavazquez@email.com', '555-0124', 'Cliente', 'contrasena20'),
('Andres Romero', 'andresromero@email.com', '555-1111', 'Mesero', 'mesero1'),
('Patricia Mendoza', 'patriciamendoza@email.com', '555-2222', 'Mesero', 'mesero2'),
('Samuel Vargas', 'samuelvargas@email.com', '555-3333', 'Mesero', 'mesero3'),
('Lucia Torres', 'luciatorres@email.com', '555-4444', 'Mesero', 'mesero4'),
('Jorge Castillo', 'jorgecastillo@email.com', '555-5555', 'Mesero', 'mesero5'),
('Esteban Torres','estebanxd@email.com','123-3212','Administrador','admin');

-- Inserciones en la tabla RESERVA
INSERT INTO RESERVA (ID_CLIENTE, ID_MESA, FECHA_HORA) VALUES
(1, 1, '2024-10-07 09:00:00'),
(2, 2, '2024-10-07 11:00:00'),
(3, 3, '2024-10-07 13:00:00'),
(4, 4, '2024-10-07 15:00:00'),
(5, 5, '2024-10-07 17:00:00'),
(6, 6, '2024-10-07 19:00:00'),
(7, 1, '2024-10-07 21:00:00'),
(1, 2, '2024-11-13 10:00:00'),
(2, 3, '2024-11-13 19:20:00'),
(3, 4, '2024-11-14 15:50:00'),
(4, 5, '2024-11-14 15:40:00'),
(5, 6, '2024-11-15 11:00:00'),
(6, 7, '2024-11-15 16:00:00'),
(7, 8, '2024-11-16 12:00:00'),
(8, 9, '2024-11-16 18:30:00'),
(9, 10, '2024-11-17 10:30:00'),
(10, 11, '2024-11-17 14:30:00'),
(11, 12, '2024-11-18 09:00:00'),
(12, 13, '2024-11-18 12:30:00'),
(13, 14, '2024-11-19 11:00:00'),
(14, 15, '2024-11-19 17:00:00'),
(15, 16, '2024-11-20 10:00:00'),
(16, 17, '2024-11-20 15:40:00');


INSERT INTO MENU (ID_MENU, NOMBRE, DESCRIPCION) VALUES (1, 'Menu Bella Venture', 'Menu estilo italiano');


INSERT INTO MENU_PLATO (ID_MENU, ID_PLATO) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10), (1, 11), (1, 12), (1, 13), 
(1, 14), (1, 15), (1, 16), (1, 17), (1, 18), (1, 19), (1, 20), (1, 21), (1, 22), (1, 23), (1, 24), 
(1, 25), (1, 26), (1, 27), (1, 28), (1, 29), (1, 30), (1, 31), (1, 32), (1, 33), (1, 34), (1, 35), 
(1, 36), (1, 37), (1, 38), (1, 39), (1, 40), (1, 41), (1, 42), (1, 43), (1, 44), (1, 45), (1, 46);

INSERT INTO Dias (nombre_dia,intervalo) VALUES
('Lunes',120),
('Martes',120),
('Miercoles',120),
('Jueves',120),
('Viernes',120),
('Sabado',120),
('Domingo',120);

INSERT INTO Horarios (id_dia, hora_apertura, hora_cierre) VALUES
(1, '09:00:00', '22:00:00'), -- Lunes
(2, '09:00:00', '22:00:00'), -- Martes
(3, '09:00:00', '22:00:00'), -- Miércoles
(4, '09:00:00', '23:00:00'), -- Jueves
(5, '09:00:00', '23:00:00'), -- Viernes
(6, '10:00:00', '23:00:00'), -- Sábado
(7, '10:00:00', '21:00:00'); -- Domingo
