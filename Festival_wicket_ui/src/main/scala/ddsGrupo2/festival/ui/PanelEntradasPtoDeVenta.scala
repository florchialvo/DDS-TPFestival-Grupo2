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
		val fs = new DropDownChoice("festival", new PropertyModel[String](filtroFestival, "festivalSeleccionado"), 
        this.festivales)
	val ps = new DropDownChoice("puesto", new PropertyModel[Int](entradasPorPuesto, "puesto"), 
        this.puestos)
	setUp
	
  override def addComponents(form: Form[Buscador[_]]) {
	fs.setNullValid(true)
	fs.setOutputMarkupId(true)
	ps.setNullValid(false)
	ps.setOutputMarkupId(true)
    form.add(fs)
    form.add(ps)
	}
	
  override def createModel(form: Form[Buscador[_]]) = {
    form.setModel(new CompoundPropertyModel(
      new Buscador(entradasPorPuesto, filtroFestival)))
  }
  
  def festivales = FestivalesHome.nombreFestivales
  def puestos = FestivalesHome.puestosDeVenta
  
  
}

