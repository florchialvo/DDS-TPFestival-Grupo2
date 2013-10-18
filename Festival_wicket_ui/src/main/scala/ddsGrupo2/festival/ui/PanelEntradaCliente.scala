package ddsGrupo2.festival.ui

import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.CompoundPropertyModel
import ddsGrupo2.festival.model._
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.model.ComponentPropertyModel
import scala.collection.JavaConverters._
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.markup.html.basic.Label

class PanelEntradaCliente extends PanelBuscador {

  val filtroContiene = new FiltroEntradaContiene
  val form: Form[Buscador[Entrada]] = new Form("buscador", createModel)
  form.add(new TextField("contiene", new PropertyModel[String](this.filtroContiene, "clienteContiene")))
  
  val dropFechaDesde = new DropDownChoice("fechaDesde",new PropertyModel[Fecha](this.filtroContiene,"fechaDesde"), this.fechas)
  
  
  val dropFechaHasta = new DropDownChoice("fechaHasta",new PropertyModel[Fecha](this.filtroContiene,"fechaHasta"),new PropertyModel(this,"fechasMayoresAlDesde"))
  

  
  dropFechaHasta.setOutputMarkupId(true)
  
  dropFechaDesde.add(new AjaxFormComponentUpdatingBehavior("onchange") {
        override def onUpdate(target: AjaxRequestTarget) = {
        
         target.add(dropFechaHasta)
        }
      })
  
  dropFechaDesde.setNullValid(false)
  dropFechaHasta.setNullValid(false)
  
  form.add(dropFechaDesde)
  form.add(dropFechaHasta)
  
  
  add(form)

  def createModel: CompoundPropertyModel[Buscador[Entrada]] = {
    new CompoundPropertyModel(
      new Buscador(new GeneradorEntradas(FestivalesHome.getFestival), filtroContiene))
  }

  def buscador = form.getModelObject()
  
  def fechas = new EntradaApplicationModel(FestivalesHome.getFestival).fechas.asScala.sortWith(_ < _).asJava
  def fechasMayoresAlDesde = this.obtenerFechasFestival
  
  def obtenerFechasFestival: java.util.List[Fecha]=new EntradaApplicationModel(FestivalesHome.getFestival)
  													.fechas
  													.asScala
  													.toList
  													.filter(elem => elem>=filtroContiene.fechaDesde)
  													.asJava
}