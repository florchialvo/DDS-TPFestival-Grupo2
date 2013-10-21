package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.form.Form
import ddsGrupo2.festival.model._
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.form.TextField

class PanelBandaCliente extends PanelBuscador {
  val filtroFestival = new FiltroBandaFestival
  val generador = new BandasPorCliente
  
  val dropFestivales = new DropDownChoice("festival",
  new PropertyModel[Festival](filtroFestival, "festival"),
  new PropertyModel(filtroFestival, "festivales"))
  
  setUp
  
  override def addComponents(form: Form[Buscador[_]]){
    dropFestivales.setNullValid(true)
    dropFestivales.setOutputMarkupId(true)
    form.add(new TextField("cliente", new PropertyModel[String](generador, "clienteABuscar")))
    form.add(dropFestivales)
  }
  
  override def createModel(form: Form[Buscador[_]]){
    form.setModel(new CompoundPropertyModel(
        new Buscador(generador, filtroFestival)))
  }
}