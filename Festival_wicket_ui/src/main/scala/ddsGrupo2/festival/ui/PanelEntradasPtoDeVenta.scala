package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.CompoundPropertyModel
import ddsGrupo2.festival.model._
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.model.ComponentPropertyModel

class PanelEntradasPtoDeVenta extends PanelBuscador {
  val filtroFestival = new FiltroPorFestival
  val entradasPorPuesto = new EntradasPuestoVta
  val dropFestivales = new DropDownChoice("festival",
    new PropertyModel[Festival](filtroFestival, "festival"),
    new PropertyModel(filtroFestival, "festivales"))
  val dropPuestos = new DropDownChoice("puesto", new PropertyModel[Int](entradasPorPuesto, "puesto"),
    this.puestos)
  setUp

  override def addComponents(form: Form[Buscador[_]]) {
    dropFestivales.setNullValid(true)
    dropFestivales.setOutputMarkupId(true)
    dropPuestos.setNullValid(false)
    dropPuestos.setOutputMarkupId(true)
    form.add(dropFestivales)
    form.add(dropPuestos)
  }

  override def createModel(form: Form[Buscador[_]]) = {
    form.setModel(new CompoundPropertyModel(
      new Buscador(entradasPorPuesto, filtroFestival)))
  }

  def puestos = FestivalesHome.puestosDeVenta
}

