package ddsGrupo2.festival.ui

import org.apache.wicket._
import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.markup.html.basic.Label
import ddsGrupo2.festival.model.FiltroBandaContiene
import org.apache.wicket.model.CompoundPropertyModel
import ddsGrupo2.festival.model.FestivalesHome
import org.apache.wicket.model.ComponentPropertyModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import collection.JavaConversions._
import ddsGrupo2.festival.model.Festival
import uqbar.arena.persistence.Configuration

class MenuPage extends WebPage {
  var festival = FestivalesHome.festivales.head
  def festivales:java.util.List[Festival] = FestivalesHome.festivales
  
  val form = new Form("form")
  val formBusqueda = new Form("formBusqueda")

  val dropFestivales = new DropDownChoice[Festival]("festival",
    new PropertyModel[Festival](this, "festival"), new PropertyModel(this, "festivales"))

  dropFestivales.setNullValid(false)
  dropFestivales.setOutputMarkupId(true)

  val buttonVender = new Button("vender") {
    override def onSubmit() {
      this.setResponsePage(new VenderPage(festival))
    }
  }

  val buttonAnular = new Button("anular") {
    override def onSubmit() {
      this.setResponsePage(new AnularPage(festival))
    }
  }

  val buttonCombo = new Button("venderCombo") {
    override def onSubmit() {
      this.setResponsePage(new VenderComboPage(festival))
    }
  }

  val buttonBuscarEntradasFecha = new Button("buscarEntradasFecha") {
    override def onSubmit() {
      this.setResponsePage(new BusquedaEntradasPage(new PanelEntradaCliente))
    }
  }

  val buttonEntradasPtoVenta = new Button("buscarEntradasPuesto") {
    override def onSubmit() {
      this.setResponsePage(new BusquedaEntradasPage(new PanelEntradasPtoDeVenta))
    }
  }

  val buttonBuscarBandasContiene = new Button("buscarBandasContiene") {
    override def onSubmit() {
      this.setResponsePage(new BusquedaBandasPage(new PanelBandaContiene))
    }
  }

  val buttonBuscarBandasCliente = new Button("buscarBandasCliente") {
    override def onSubmit() {
      this.setResponsePage(new BusquedaBandasPage(new PanelBandaCliente))
    }
  }

  addFields
  addActions
  add(form)
  add(formBusqueda)

  def addActions {
    form.add(buttonVender)
    form.add(buttonAnular)
    form.add(buttonCombo)

    formBusqueda.add(buttonBuscarEntradasFecha)
    formBusqueda.add(buttonBuscarBandasContiene)
    formBusqueda.add(buttonEntradasPtoVenta)
    formBusqueda.add(buttonBuscarBandasCliente)
  }

  def addFields {
    form.add(dropFestivales)
  }
}

