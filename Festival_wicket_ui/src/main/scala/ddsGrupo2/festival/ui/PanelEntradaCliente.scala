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
  val filtroContiene = new FiltroEntradaFecha
  val generador = new EntradasPorCliente
  
  setUp

  override def addComponents(form: Form[Buscador[_]]) {
    val dropFechaDesde = new DropDownChoice("fechaDesde", new PropertyModel[Fecha](this.filtroContiene, "fechaDesde"), this.fechas)
    val dropFechaHasta = new DropDownChoice("fechaHasta", new PropertyModel[Fecha](this.filtroContiene, "fechaHasta"), new PropertyModel(this, "fechasMayoresAlDesde"))
    form.add(new TextField("cliente", new PropertyModel[String](generador, "cliente")))
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
  }

  def createModel(form: Form[Buscador[_]]) = {
    form.setModel(new CompoundPropertyModel(
      new Buscador(new EntradasPorCliente(), filtroContiene)))
  }

  def fechas = new EntradaApplicationModel(FestivalesHome.getFestival).fechas.asScala.sortWith(_ < _).asJava
  def fechasMayoresAlDesde = this.obtenerFechasFestival

  def obtenerFechasFestival: java.util.List[Fecha] = new EntradaApplicationModel(FestivalesHome.getFestival)
    .fechas
    .asScala
    .toList
    .filter(elem => elem >= filtroContiene.fechaDesde)
    .asJava
}