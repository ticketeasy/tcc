package br.com.sociofuracao.bc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.com.sociofuracao.model.Lugar;

public class LugarBC {
	
	private static Map<Long, Lugar> tabela;
	private static LugarBC lugarBC = new LugarBC();
	int contador;

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public static void setTabela(Map<Long, Lugar> tabela) {
		LugarBC.tabela = tabela;
	}

	private LugarBC() {
		if(tabela == null){
			tabela = new HashMap<Long, Lugar>();
		}
	}

	public static LugarBC getInstance() {
		return lugarBC;
	}

	public Map<Long, Lugar> getTabela() {
		return tabela;
	}

	public void create(Lugar lugar) {
		contador = tabela.size() + 1;
		//lugar.setId(contador);
		tabela.put(Long.valueOf(contador), lugar);
	}

	public Lugar retrieve(long id) {
		if (tabela.containsKey(id)) {
			for (Long aKey : tabela.keySet()) {
				Lugar aValue = tabela.get(aKey);
				if (aValue.getId() == id) {
					return aValue;
				}
			}
		}
		return null;
	}

	public Boolean update(Lugar lugar) {
		for (Entry<Long, Lugar> entry : tabela.entrySet()) {
			if (entry.getKey().equals(Long.valueOf(lugar.getId()))) {
				entry.setValue(lugar);
				return Boolean.TRUE;
			}
		}
		return Boolean.TRUE;
	}

	public Boolean delete(Lugar lugar) {
		if (tabela.containsKey(Long.valueOf(lugar.getId()))) {
			tabela.remove(Long.valueOf(lugar.getId()));
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}


}
