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

class MenuApplication extends WebApplication {
  def getHomePage = classOf[MenuPage]
}

class MenuPage extends WebPage {
  
  val form = new Form("form", this.createModel)
  val festivales = new DropDownChoice("festival", new ComponentPropertyModel("nombreFestivales"))

  festivales.setNullValid(false)

  festivales.setOutputMarkupId(true)
  
  val buttonVender = new Button("vender") {
    override def onSubmit() {
      this.setResponsePage(new VenderPage(FestivalesHome.getSelectedFestival))
    }
  }

  val buttonAnular = new Button("anular") {
    override def onSubmit() {
      this.setResponsePage(new AnularPage(FestivalesHome.getSelectedFestival))
    }
  }

  val buttonCombo = new Button("venderCombo") {
    override def onSubmit() {
      this.setResponsePage(new VenderComboPage(FestivalesHome.getSelectedFestival))
    }
  }

  val buttonBuscarEntradas = new Button("buscarEntradas") {
    override def onSubmit() {
      this.setResponsePage(new BusquedaEntradasPage(new PanelEntradaCliente))
    }
  }

  val buttonEntradasPtoVenta = new Button("buscarEntradasPuesto") {
    override def onSubmit() {
      this.setResponsePage(new BusquedaEntradasPage(new PanelEntradasPtoDeVenta))
    }
  }
  
  val buttonBuscarBandas = new Button("buscarBandas") {
    override def onSubmit() {
      this.setResponsePage(new BusquedaBandasPage(new PanelBandaContiene(FestivalesHome.getSelectedFestival)))
    }
  }

  addFields
  addActions
  add(form)

  def addActions {
    form.add(festivales)
    form.add(buttonVender)
    form.add(buttonBuscarEntradas)
    form.add(buttonBuscarBandas)
    form.add(buttonEntradasPtoVenta)
    form.add(buttonAnular)
    form.add(buttonCombo)
  }

  def addFields {
    val label = new Label("label", "Elija una operación a realizar")
    form.add(label)
  }
  
   def createModel = {
    new CompoundPropertyModel(FestivalesHome)
  }
}

