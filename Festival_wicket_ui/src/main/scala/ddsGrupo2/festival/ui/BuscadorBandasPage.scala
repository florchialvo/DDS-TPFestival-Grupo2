package ddsGrupo2.festival.ui

import ddsGrupo2.festival.model.Banda
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.basic.Label
import ddsGrupo2.festival.model.FestivalesHome

class BuscadorBandasPage extends BuscadorPage{
  
  override def titulo = "Buscar Bandas"
  
  override def mostrarResultado(item: ListItem[Nothing]) {
    var model: Banda = item.getModelObject()
    item.add(new Label("nombre", model.nombre))
    item.add(new Label("precio", model.categoria.getValor))
  }

  override def coleccionBusqueda:List[_] = FestivalesHome.getBandas.toList
}