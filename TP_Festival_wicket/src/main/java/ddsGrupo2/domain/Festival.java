package ddsGrupo2.domain;

import java.util.ArrayList;
import java.util.List;

public class Festival {
	private List<TipoPersona> descuentosValidos;
	
	public List<TipoPersona> getDescuentosValidos() {
		return descuentosValidos;
	}

	public Festival(){
		descuentosValidos = new ArrayList<TipoPersona>();
		descuentosValidos.add(new Mayor());
		descuentosValidos.add(new Menor());
		descuentosValidos.add(new Jubilado());
		descuentosValidos.add(new Dama());
		descuentosValidos.add(new MenorDe12Acompaniado());
	}
}
