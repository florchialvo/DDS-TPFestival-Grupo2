package ddsGrupo2.festival.ui

import ddsGrupo2.festival.model.Entrada
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.basic.Label
import ddsGrupo2.festival.model.FestivalesHome

class BusquedaEntradasPage(panelBuscador: PanelBuscador) extends BusquedaPage(panelBuscador){
  
  override def titulo = "Buscar Entrada"
  
  override def mostrarResultado(item: ListItem[Nothing]) {
    var model: Entrada = item.getModelObject()
    item.add(new Label("nombre", model.nombre))
    item.add(new Label("precio", model.precio))
 
  }
}