package ddsGrupo2.festival.ui


import org.apache.wicket.markup.html.WebPage
import org.apache.wicket._

import org.apache.wicket.protocol.http._
import org.apache.wicket.markup.html._
import org.apache.wicket.markup.html.form._
import org.apache.wicket.model._
import org.apache.wicket.markup.html.basic.Label
import collection.JavaConversions._

import ddsGrupo2.festival.model._

class AnularPage extends TBasicPage {
    val buttonAnular = new Button("anular") {
      override def onSubmit() {
     }
    }
	this.setUp(buttonAnular)

}