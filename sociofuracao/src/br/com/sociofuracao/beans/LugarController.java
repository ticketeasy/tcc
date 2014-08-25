package br.com.sociofuracao.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sociofuracao.bc.EventoBC;
import br.com.sociofuracao.bc.LugarBC;
import br.com.sociofuracao.model.Lugar;

@ManagedBean
@SessionScoped
public class LugarController {
	
	private Lugar lugar;
	
	private List<Lugar> lstLugar;
	
	private List<Lugar> lstLugarCompra;
	
	private LugarBC lugarBC;
	
	private EventoBC eventoBC;
	
	private String termoID;
	
	private BigDecimal valorTotal;
	
	//private Cartao cartao;
	
	public LugarController(){
		lugarBC = LugarBC.getInstance();
		eventoBC = EventoBC.getInstance();
		
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	
	public String resgatarIngresso(){
		if(termoID.equalsIgnoreCase("1")){
			//lugar.getEvento().setStatus("0");
			//eventoBC.update(lugar.getEvento());
			lugarBC.delete(lugar);
		}
		
		
		return "/member/index.xhtml";
	}
	
	public String comprarIngresso(){
		//lugar.getEvento().setStatus("3");
		//eventoBC.update(lugar.getEvento());
		lugarBC.delete(lugar);
		return "/member/index.xhtml";
	}

	public List<Lugar> getLstLugar() {
		lstLugar = new ArrayList<Lugar>();
		for(Long aKey : lugarBC.getTabela().keySet()) {
		    Lugar aValue = lugarBC.getTabela().get(aKey);
		    lstLugar.add(aValue);
		}
		return lstLugar;
	}

	public void setLstLugar(List<Lugar> lstLugar) {
		this.lstLugar = lstLugar;
	}
	
	public String voltar(){
		return "/member/index.xhtml?faces-redirect=true";
	}

	public LugarBC getLugarBC() {
		return lugarBC;
	}

	public void setLugarBC(LugarBC lugarBC) {
		this.lugarBC = lugarBC;
	}

	public String getTermoID() {
		return termoID;
	}

	public void setTermoID(String termoID) {
		this.termoID = termoID;
	}

	public String disponibilizarIngresso(){
		return "/member/eventos/registrarrresgate.xhtml";
	}
	
	public String escolherLugar(){
		if(lstLugarCompra == null){
			lstLugarCompra = new ArrayList<Lugar>();
			lstLugarCompra = lstLugar;
		}
		return "/member/eventos/escolherLugar.xhtml?faces-redirect=true";
	}
	
	public String escolherSetor(){
		return "/member/eventos/escolherSetor.xhtml";
	}
	
	public String removeResgatar(){
		if(lstLugarCompra.size() > 0){
			lugarBC.delete(lstLugarCompra.get(0));
		}
		return "/member/index.xhtml";
	}

	public List<Lugar> getLstLugarCompra() {
		return lstLugarCompra;
	}

	public void setLstLugarCompra(List<Lugar> lstLugarCompra) {
		this.lstLugarCompra = lstLugarCompra;
	}

	public EventoBC getEventoBC() {
		return eventoBC;
	}

	public void setEventoBC(EventoBC eventoBC) {
		this.eventoBC = eventoBC;
	}
	
	public void inserirLugarCompra(){
		Lugar lugar = new Lugar();
		//lugar.setLugar("305O031");
		//lugar.setValor(BigDecimal.valueOf(20.00));
		//lugar.setSetor("BA INFERIOR");
		
		lugar.setUsuario(LoginController.getSessionUser());
		lstLugarCompra.add(lugar);
	}
	
	public String efetuarPagamento(){
		valorTotal = BigDecimal.ZERO;
		if(lstLugarCompra != null && lstLugarCompra.size() > 0){
			for(@SuppressWarnings("unused") Lugar l : lstLugarCompra){
				//valorTotal = valorTotal.add(l.getValor());
			}
		}
		//cartao = new Cartao();
		return "/member/eventos/pagamentoLugar.xhtml?faces-redirect=true";
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
/*
	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}
*/		
	
	
}
