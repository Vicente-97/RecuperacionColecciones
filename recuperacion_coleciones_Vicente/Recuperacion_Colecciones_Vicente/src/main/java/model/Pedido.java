package main.java.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pedido {

	private static Integer id=0;
	private String codigo;
	private Status status;
	private Cliente cliente;
	
	private List<Linea> listaLineas;
	
	public Pedido() {
		
	}
	
	public Pedido (String codigo, Status status, Cliente cliente) {
		this.codigo=codigo;
		this.status=status;
		cliente= new Cliente();
		this.id++;
		this.listaLineas= new ArrayList<>();
		
		
	}
	
	public void addLinea (Linea linea1) {
		listaLineas.add(linea1);
	}
	
	public void borrarLinea(Integer id) {
		Iterator<Linea> iteradorLinea=listaLineas.iterator();
		if(status!=status.ENVIADO) {
			while(iteradorLinea.hasNext()) {
				Linea linea=iteradorLinea.next();
				linea.getId().equals(id);
				listaLineas.remove(linea);
			}
			
			/*se hace de forma mas facil
			 * if(status!=status.ENVIADO){
			  for (Linea i: listaLineas) {
				  	i.getId().equals(id){
				  		listaLineas.remove(i);
				}
			}
			}*/
		}
		
		
	}
	
	public void vaciarPedido() {
		listaLineas.clear();
	}
	
	public Double getCostePedido() {
		Double resultado=0.0;
			for (Linea l : listaLineas){
				resultado+=l.getImporte();
			}
		
		return resultado;
	}
	
	public Integer getNumeroProductos() {
		Integer resultado=0;
			for(Linea l : listaLineas) {
				resultado+=l.getCantidad();
			}
		
		return resultado;
	}
	
	public String mostrarLineasPorIDAscendente() {
		StringBuilder st = new StringBuilder();
		listaLineas.sort(new ComparadorIDAscendente());
		for(Linea l : listaLineas) {
			st.append(l+System.lineSeparator());
		}
	
	return st.toString();
	}
	
	public String mostrarLineasPorIDDescendiente() {
		StringBuilder st= new StringBuilder();
		listaLineas.sort(new ComparadorIDDescendiente());
		for(Linea l :listaLineas) {
			st.append(l+System.lineSeparator());
		}
		
		return st.toString();
	}
	
	public String obtenerProductos () {
		StringBuilder st = new StringBuilder();
		for (Linea l: listaLineas) {
			st.append(l+System.lineSeparator());
		}
		return st.toString();
	}

	public static Integer getId() {
		return id;
	}

	public static void setId(Integer id) {
		Pedido.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Linea> getListaLineas() {
		return listaLineas;
	}

	public void setListaLineas(List<Linea> listaLineas) {
		this.listaLineas = listaLineas;
	}
	
	public Double precioUnitario() {	
		
		return this.getNumeroProductos()!=null?
				this.getCostePedido()/this.getNumeroProductos():
					0;
	}
}
