USE TpFestival
	GO


--DROPS
DROP TABLE Entradas
DROP TABLE TiposPersona
DROP TABLE FilaPorSector
DROP TABLE Sectores
DROP TABLE BandaPorNoche
DROP TABLE Bandas
DROP TABLE Categorias
DROP TABLE Noches
DROP TABLE Festivales


CREATE TABLE [Festivales] (
	festival_Id NUMERIC(18,0) PRIMARY KEY IDENTITY,
	festival_Nombre NVARCHAR(255),
	festival_FechaVencimientoEntradasAnticipadas DATETIME
)
	
CREATE TABLE [Noches](
	noche_Id NUMERIC(18,0) PRIMARY KEY IDENTITY,
	noche_Fecha DATETIME,
	noche_Festival NUMERIC(18,0) FOREIGN KEY REFERENCES Festivales (festival_Id)
)

CREATE TABLE [Categorias](
	categoria_Id NUMERIC(18,0) PRIMARY KEY IDENTITY,
	categoria_Valor NUMERIC(18,0),
	categoria_Descripcion NVARCHAR(255),
)	
CREATE TABLE [Bandas](
	banda_Id NUMERIC(18,0) PRIMARY KEY IDENTITY,
	banda_Nombre NVARCHAR(255),
	banda_CodigoCategoria NUMERIC(18,0) FOREIGN KEY REFERENCES Categorias (categoria_Id),
)

CREATE TABLE [BandaPorNoche](

	bandaPorNoche_CodigoBanda NUMERIC(18,0) FOREIGN KEY REFERENCES Bandas (banda_Id),
	bandaPorNoche_CodigoNoche NUMERIC(18,0) FOREIGN KEY REFERENCES Noches (noche_Id), 
	bandaPorNoche_HorarioComienzo DATETIME,
	bandaPorNoche_HorarioFin DATETIME,
	CONSTRAINT PK_BANDAPORNOCHE PRIMARY KEY (bandaPorNoche_CodigoBanda,bandaPorNoche_CodigoNoche)
)

CREATE TABLE [Sectores](
	sector_Id NUMERIC(18,0) PRIMARY KEY IDENTITY,
	sector_Letra NVARCHAR(1) ,
	sector_Festival NUMERIC(18,0) FOREIGN KEY REFERENCES Festivales (festival_Id),
	
)

CREATE TABLE [FilaPorSector](
	fila_Id NUMERIC(18,0)PRIMARY KEY IDENTITY ,
	fila_NumeroFila NUMERIC(18,0),
	fila_Sector NUMERIC(18,0) FOREIGN KEY REFERENCES Sectores (sector_Id),
	fila_Cantidad_Butacas NUMERIC(18,0),
	fila_ValorBase NUMERIC(18,0),

)

CREATE TABLE [TiposPersona](
	tipoPersona_Id NUMERIC(18,0)  PRIMARY KEY IDENTITY,
	tipoPersona_Nombre NVARCHAR(255),
	tipoPersona_Festival NUMERIC(18,0) FOREIGN KEY REFERENCES Festivales (festival_Id), 
)


CREATE TABLE [Entradas](
	entrada_Id NUMERIC(18,0) PRIMARY KEY IDENTITY,
	entrada_Festival NUMERIC(18,0) FOREIGN KEY REFERENCES Festivales (festival_Id),
	entrada_Noche NUMERIC (18,0) FOREIGN KEY REFERENCES Noches (noche_Id),
	entrada_Sector NUMERIC(18,0) FOREIGN KEY REFERENCES Sectores (sector_Id),
	entrada_Fila NUMERIC(18,0) FOREIGN KEY REFERENCES FilaPorSector (fila_Id),
	entrada_Butaca NUMERIC(18,0),
	entrada_Cliente NVARCHAR(255),
	entrada_TipoPersona  NUMERIC(18,0) FOREIGN KEY REFERENCES TiposPersona (tipoPersona_Id),
	entrada_PuntoDeVenta NUMERIC(18,0),
	entrada_Estado NVARCHAR(1) CHECK (entrada_Estado='V' OR entrada_Estado='L'),
)



--FESTIVALES nombre,fechavtoentradas
INSERT INTO Festivales VALUES('Festival Rock',GETDATE())
INSERT INTO Festivales VALUES('Quilmes Rock',GETDATE())


--CATEGORIAS--
INSERT INTO Categorias VALUES (0,'categoria1')
INSERT INTO Categorias VALUES (50,'categoria2')
INSERT INTO Categorias VALUES (100,'categoria3')
INSERT INTO Categorias VALUES (200,'categoria4')



--BANDAS
INSERT INTO Bandas VALUES ('Led Zeppelin',4)
INSERT INTO Bandas VALUES ('Iron Maiden',4)
INSERT INTO Bandas VALUES ('Soda Stereo',3)
INSERT INTO Bandas VALUES ('Rollings Stones',4)
INSERT INTO Bandas VALUES ('Bon Jovi',4)
INSERT INTO Bandas VALUES ('Indio Solari',2)

--NOCHES
INSERT INTO Noches VALUES ('18/10/2014',1)
INSERT INTO Noches VALUES ('19/10/2014',1)
INSERT INTO Noches VALUES ('20/10/2014',1)
INSERT INTO Noches VALUES ('21/10/2014',1)

INSERT INTO Noches VALUES ('18/10/2014',2)
INSERT INTO Noches VALUES ('19/10/2014',2)

--BANDAS X NOCHE (Id_banda,Id_noche,fechaComienzo,fechaFin)
INSERT INTO BandaPorNoche VALUES (1,1,'12:00:00','14:00:00')
INSERT INTO BandaPorNoche VALUES (3,1,'14:00:00','16:00:00')
INSERT INTO BandaPorNoche VALUES (5,2,'12:00:00','14:00:00')
INSERT INTO BandaPorNoche VALUES (1,3,'14:00:00','16:00:00')
INSERT INTO BandaPorNoche VALUES (2,3,'16:00:00','18:00:00')
INSERT INTO BandaPorNoche VALUES (1,4,'12:00:00','14:00:00')
INSERT INTO BandaPorNoche VALUES (3,4,'14:00:00','17:00:00')



--SECTORES festival,letra

INSERT INTO Sectores VALUES('A',1)
INSERT INTO Sectores VALUES('B',1)

INSERT INTO Sectores VALUES('A',2)
INSERT INTO Sectores VALUES('B',2)
INSERT INTO Sectores VALUES('C',2)

--Filas x sector nrofila, sector,cantbutacas,valorbase

INSERT INTO FilaPorSector VALUES (1,1,15,100)
INSERT INTO FilaPorSector VALUES (2,1,15,100)
INSERT INTO FilaPorSector VALUES (3,1,15,100)

INSERT INTO FilaPorSector VALUES (1,2,10,500)
INSERT INTO FilaPorSector VALUES (2,2,10,500)
INSERT INTO FilaPorSector VALUES (3,2,10,500)

INSERT INTO FilaPorSector VALUES (1,3,10,500)
INSERT INTO FilaPorSector VALUES (2,3,10,400)
INSERT INTO FilaPorSector VALUES (3,3,10,300)

INSERT INTO FilaPorSector VALUES (1,4,10,500)
INSERT INTO FilaPorSector VALUES (2,4,10,500)
INSERT INTO FilaPorSector VALUES (3,4,10,500)

INSERT INTO FilaPorSector VALUES (1,5,100,500)


--TIPOS PERSONA
INSERT INTO TiposPersona VALUES ('Dama',1)
INSERT INTO TiposPersona VALUES ('Jubilado',1)
INSERT INTO TiposPersona VALUES ('Menor',1)
INSERT INTO TiposPersona VALUES ('Mayor',1)

INSERT INTO TiposPersona VALUES ('Dama',2)
INSERT INTO TiposPersona VALUES ('Menor',2)
INSERT INTO TiposPersona VALUES ('Mayor',2)



--ENTRADAS
INSERT INTO Entradas VALUES(1,1,1,1,1,'Kevin',1,1,'V')
INSERT INTO Entradas VALUES(1,2,3,2,2,'Flor',2,1,'V')
INSERT INTO Entradas VALUES(1,2,2,1,2,'Pablo',2,1,'V')
INSERT INTO Entradas VALUES(1,3,3,2,3,'Nico',1,1,'V')
INSERT INTO Entradas VALUES(1,3,2,1,4,'Pepe',3,1,'V')
INSERT INTO Entradas VALUES(2,3,2,1,4,'Tomas',3,1,'V')
INSERT INTO Entradas VALUES(2,3,2,1,4,'Yago',3,1,'V')




--Integrante 1: información sobre las entradas vendidas a un usuario/cliente (ej: “Kevin”)
SELECT festival_Nombre,CONVERT(VARCHAR(10),noche_Fecha,101)'Fecha Noche',sector_Letra,entrada_Fila,entrada_Butaca,entrada_Cliente,tipoPersona_Nombre,entrada_PuntoDeVenta 
		FROM ENTRADAS,FESTIVALES,TiposPersona,Sectores,Noches
		WHERE 
			festival_Id = entrada_Festival and
			noche_Id = entrada_Noche and
			sector_Id = entrada_Sector and
			tipoPersona_Id = entrada_TipoPersona and
			entrada_cliente='Kevin'
			
--Integrante 2: información sobre las entradas vendidas para un festival (ej: “Jesus María Rock”)
SELECT festival_Nombre,CONVERT(VARCHAR(10),noche_Fecha,101)'Fecha Noche',sector_Letra,entrada_Fila,entrada_Butaca,entrada_Cliente,tipoPersona_Nombre,entrada_PuntoDeVenta 
	FROM ENTRADAS,FESTIVALES,TiposPersona,Sectores,Noches	
	WHERE 
	festival_Id = entrada_Festival and
	noche_Id = entrada_Noche and		
	sector_Id = entrada_Sector and
	tipoPersona_Id = entrada_TipoPersona and
	festival_Nombre= 'Festival Rock' and entrada_Festival= festival_Id

--Integrante 3: información sobre las presentaciones de una banda en distintos festivales (ej: “Catupecu Machu”)
SELECT banda_Nombre,festival_Nombre,CONVERT(VARCHAR(10),noche_Fecha,101)'Fecha Noche' 
	FROM Bandas,BandaPorNoche,Noches, Festivales 
	WHERE 
			banda_Id = bandaPorNoche_CodigoBanda and banda_Nombre = 'Led Zeppelin' and
			noche_Id = bandaPorNoche_CodigoNoche and noche_Festival = festival_Id


--Integrante 4: información sobre todas las bandas que se presentan una noche, con el horario de comienzo y fin.
SELECT banda_Nombre,CONVERT(VARCHAR(10),noche_Fecha,101)'Fecha Noche' ,CONVERT(VARCHAR(10),bandaPorNoche_HorarioComienzo,108)'Fecha Comienzo',CONVERT(VARCHAR(10),bandaPorNoche_HorarioFin,108)'Fecha Comienzo'
	FROM Bandas,BandaPorNoche,Noches
	WHERE
			banda_Id = bandaPorNoche_CodigoBanda and
			noche_Id = bandaPorNoche_CodigoNoche
			
	