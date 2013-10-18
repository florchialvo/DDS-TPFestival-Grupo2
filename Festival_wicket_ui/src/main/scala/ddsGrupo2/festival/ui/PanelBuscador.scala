package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.panel.Panel
import ddsGrupo2.festival.model.Buscador

abstract class PanelBuscador extends Panel("panelBuscador") {
	def buscador:Buscador[_]
}