package br.com.sociofuracao.bc;

import java.util.Map;
import java.util.Map.Entry;

import br.com.sociofuracao.model.Evento;


public class EventoBC {
	private static Map<Long, Evento> tabela;
	private static EventoBC eventoBC = new EventoBC();
	int contador;

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public static void setTabela(Map<Long, Evento> tabela) {
		EventoBC.tabela = tabela;
	}

	private EventoBC() {
		/*
		if(tabela == null){
			tabela = new HashMap<Long, Evento>();
			Evento evento1 = new Evento();
			evento1.setDescricao("Campeonato Brasileiro 2014 - Sétima Rodada");
			evento1.setData("Dom 25/05 as 16h00 - Durival Brito");
			evento1.setImgUrl("/img/AtleticoxCoritiba.png");
			evento1.setStatus("0");
			contador = tabela.size() + 1;
			evento1.setId(contador);
			tabela.put(Long.valueOf(contador), evento1);
			Evento evento2 = new Evento();
			evento2.setDescricao("Campeonato Brasileiro 2014 - Oitava Rodada");
			evento2.setData("Qua 25/05 as 19h30 - Durival Brito");
			evento2.setImgUrl("/img/Atletico x Sao Paulo.png");
			evento2.setStatus("0");
			contador = tabela.size() + 1;
			evento2.setId(contador);
			tabela.put(Long.valueOf(contador), evento2);
		}*/
	}

	public static EventoBC getInstance() {
		return eventoBC;
	}

	public Map<Long, Evento> getTabela() {
		return tabela;
	}

	public void create(Evento evento) {
		contador = tabela.size() + 1;
		//evento.setId(contador);
		tabela.put(Long.valueOf(contador), evento);
	}

	public Evento retrieve(long id) {
		if (tabela.containsKey(id)) {
			for (Long aKey : tabela.keySet()) {
				Evento aValue = tabela.get(aKey);
				if (aValue.getId() == id) {
					return aValue;
				}
			}
		}
		return null;
	}

	public Boolean update(Evento evento) {
		for (Entry<Long, Evento> entry : tabela.entrySet()) {
			if (entry.getKey().equals(Long.valueOf(evento.getId()))) {
				entry.setValue(evento);
				return Boolean.TRUE;
			}
		}
		return Boolean.TRUE;
	}

	public Boolean delete(Evento evento) {
		if (tabela.containsKey(Long.valueOf(evento.getId()))) {
			tabela.remove(Long.valueOf(evento.getId()));
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
