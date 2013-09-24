package ddsGrupo2.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

import ddsGrupo2.domain.*;

public class EntradaPage extends WebPage {

	private static final long serialVersionUID = 1L;

	private Entrada entrada;
	private Festival festival;

	public EntradaPage(final PageParameters parameters) {
		Form form = new Form("entradaForm", this.createModel());
		this.add(form);

		this.addFields(form);
		this.addActions(form);
	}

	private void addActions(Form form) {
		form.add(new Button("calcularPrecio") {
		});
	}

	private void addFields(Form form) {
		form.add(this.createPrecioTextField(form));
		form.add(new DropDownChoice("tipoPersona", 
				festival.getDescuentosValidos()));
		form.add(new Label("precio"));
		
		form.add(new FeedbackPanel("feedbackPanel"));		
	}

	protected CompoundPropertyModel createModel() {
		this.entrada = new Entrada();
		this.festival = new Festival();
		entrada.setTipoPersona(festival.getDescuentosValidos().get(0));
		return new CompoundPropertyModel(this.entrada);
	}

	protected TextField<Double> createPrecioTextField(final Form form) {
		TextField<Double> precio = new TextField("precioBase");
		precio.add(new IValidator<Double>(){
			private static final long serialVersionUID = 1L;
			
			public void validate(IValidatable<Double> validatable) {
				if(validatable.getValue()<0){
					validatable.error(new ValidationError().setMessage("El precio base no puede ser negativo"));
				}
				
			}
		});
		return precio;
	}
}
