package ddsGrupo2.festival.model.exception

import org.uqbar.commons.model.UserException

class ComboVacioException (mensaje: String) extends UserException(mensaje)

class EntradaNoVendidaException (mensaje: String) extends UserException(mensaje)

class EntradaYaAgregadaException (mensaje: String) extends UserException(mensaje)

class EntradaYaVendidaException(mensaje: String) extends UserException(mensaje)