package ddsGrupo2.festival.ui

import org.apache.wicket._
import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import collection.JavaConversions._

import ddsGrupo2.festival.model._

class AnularPage extends WebPage {
    val form = new Form("entradaForm", this.createModel)
    val festival = FestivalesHome.getFestival

    addFields(form)
    addOptions(form)
    addActions(form)
    add(form)

    def addFields(form: Form) {
        //        form.add(new Label("labelFecha", "Fecha"))
        //        form.add(new Label("labelSector", "Sector"))
        //        form.add(new Label("labelFila", "Fila"))
        form.add(new DropDownChoice("tipoPersona", this.descuentosValidos))
    }

    def addOptions(form: Form) {
    }

    def addActions(form: Form) {
    }

    def createModel: CompoundPropertyModel = new CompoundPropertyModel(this.festival);

    def descuentosValidos: java.util.List[TipoPersona] =
        new java.util.ArrayList[TipoPersona](this.festival.descuentosValidos)
}