package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.panel.Panel
import ddsGrupo2.festival.model.Buscador

abstract class PanelBuscador(id:String) extends Panel(id) {
	def buscador:Buscador[_]
}