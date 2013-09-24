package ddsGrupo2.domain;

public class Entrada {
	
	private double precioBase;
	private TipoPersona tipoPersona; 
	
	public Entrada(){
//		this.setTipoPersona(new Mayor());
	}
	
	public TipoPersona getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(TipoPersona tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}
	
	public double precio() {
		this.validar();
		return this.precioBase - this.descuento();
	}
	
	private void validar() {
		if(this.tipoPersona==null)
			throw new RuntimeException("Falta descuento");
	}

	public double descuento(){
		return this.tipoPersona.descuento(precioBase);
	}
	
}
