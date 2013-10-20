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

class MenuApplication extends WebApplication {
  def getHomePage = classOf[MenuPage]
}

class MenuPage extends WebPage {
  var festival = FestivalesHome.getAnyFestival

  val form = new Form("form")
  val dropFestivales = new DropDownChoice[Festival]("festival",
    new PropertyModel[Festival](this, "festival"), FestivalesHome.festivales)

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
      this.setResponsePage(new BusquedaBandasPage(new PanelBandaContiene))
    }
  }

  addFields
  addActions
  add(form)

  def addActions {
    form.add(dropFestivales)
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

