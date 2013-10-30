USE TpFestival
	GO

CREATE TABLE [Festivales] (
	festival_Id NUMERIC(18,0) PRIMARY KEY IDENTITY,
	festival_Nombre NVARCHAR(255),
	festival_ValorBase NUMERIC(18,2),
	festival_FechaVencimientoEntradasAnticipadas DATETIME,
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
)

CREATE TABLE [Sectores](
	sector_Id NVARCHAR(1) PRIMARY KEY,
	sector_Festival NUMERIC(18,0) FOREIGN KEY REFERENCES Festivales (festival_Id),
	sector_Valor NUMERIC(18,0),	
)

CREATE TABLE [FilaPorSector](
	fila_Id NUMERIC(18,0) PRIMARY KEY,
	fila_Sector NVARCHAR(1) FOREIGN KEY REFERENCES Sectores (sector_Id),
	fila_Cantidad_Butacas NUMERIC(18,0),
)

CREATE TABLE [TiposPersona](
	tipoPersona_Id NVARCHAR(255) PRIMARY KEY,
	tipoPersona_Festival NUMERIC(18,0) FOREIGN KEY REFERENCES Festivales (festival_Id), 
)


CREATE TABLE [Entradas](
	entrada_Id NUMERIC(18,0) PRIMARY KEY IDENTITY,
	entrada_Festival NUMERIC(18,0) FOREIGN KEY REFERENCES Festivales (festival_Id),
	entrada_Noche NUMERIC (18,0) FOREIGN KEY REFERENCES Noches (noche_Id),
	entrada_ValorBase NUMERIC (18,2),
	entrada_Sector NVARCHAR(1) FOREIGN KEY REFERENCES Sectores (sector_Id),
	entrada_Fila NUMERIC(18,0) FOREIGN KEY REFERENCES FilaPorSector (fila_Id),
	entrada_Butaca NUMERIC(18,0),
	entrada_Cliente NVARCHAR(255),
	entrada_TipoPersona  NVARCHAR(255) FOREIGN KEY REFERENCES TiposPersona (tipoPersona_Id),
	entrada_PuntoDeVenta NUMERIC(18,0),
	entrada_Estado NVARCHAR(1) CHECK (entrada_Estado='V' OR entrada_Estado='L'),
)


