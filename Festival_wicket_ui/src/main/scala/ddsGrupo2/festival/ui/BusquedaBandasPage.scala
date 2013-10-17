package ddsGrupo2.festival.ui

import ddsGrupo2.festival.model.Banda
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.basic.Label
import ddsGrupo2.festival.model.FestivalesHome
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.markup.html.form.Form
import ddsGrupo2.festival.model.Busqueda

class BusquedaBandasPage(var panelBuscador: PanelBuscador) extends BusquedaPage{
  
  override def titulo = "Buscar Bandas"
  
  override def mostrarResultado(item: ListItem[Nothing]) {
    var model: Banda = item.getModelObject()
    item.add(new Label("nombre", model.nombre))
    item.add(new Label("precio", model.categoria.getValor))
  }
  
  def crearBuscador = panelBuscador.buscador 
  
  def agregarPanelBuscador(form: Form[Busqueda[_]]) =
    form.add(panelBuscador) 
}