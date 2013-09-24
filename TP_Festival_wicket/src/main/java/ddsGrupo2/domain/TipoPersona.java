package ddsGrupo2.domain;

public abstract class TipoPersona {
    abstract double descuento(double valorBase);
    
    public String toString(){
    	 return this.getClass().getSimpleName();
    }
}

class Menor extends TipoPersona {
	double descuento(double valorBase){
        if (valorBase > 100)
            return valorBase * 0.20;
        return 10;
    }
}

class Mayor extends TipoPersona {
	double descuento(double valorBase){
        return 0;
    }
}

class Jubilado extends TipoPersona {
    double descuento(double valorBase){
    	return valorBase * 0.15;
    }
}

class Dama extends TipoPersona {
	double descuento(double valorBase){
        return valorBase * 0.20;
    }
}

class MenorDe12Acompaniado extends TipoPersona {
	double descuento(double valorBase){
        return valorBase * 0.5;
    }
}