package main.java.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Negocio {

	private List<Cliente> listaClientes;
	private List<Pedido> listaPedido;
	private List<Catalogo> listaCatalogo;
	
	
	public Negocio () {
		this.listaCatalogo=new ArrayList<>();
		this.listaClientes= new ArrayList<>();
		this.listaPedido=new ArrayList<>();
	}
	
	public boolean addCliente () {
		boolean resultado= false;
			for(Cliente c : listaClientes) {
				if(!listaClientes.contains(c)&& c.esMayorEdad()) {
					listaClientes.add(c);
					resultado=true;
				}
			}
			
		return resultado;
	}
	
	public boolean eliminarCliente(String nombre) {
		boolean resultado=false;
			for(Cliente c: listaClientes) {
				if(c.getNombre().equals(nombre)) {
					listaClientes.remove(c);
					resultado=true;
				}
			}
		
		return resultado;
	}
	
	public String listarClientePorImporteDeVentas () {
		StringBuilder st = new StringBuilder();
		listaPedido.sort(new OrdenarPedidoPorImporteVenta());
		for(Pedido p : listaPedido) {
			st.append(p+System.lineSeparator());
		}
		
		return st.toString();
	}
	
	public String listaClientePorEdad () {
		StringBuilder st = new StringBuilder();
		listaClientes.sort(new ComparatorClientePorEdad());
		for (Cliente c: listaClientes) {
			st.append(c+System.lineSeparator());
		}
	
		return st.toString();
	}
	
	public String mostrarProductosPorCliente() {
		StringBuilder st = new StringBuilder();
		for(Pedido p :listaPedido) {
			st.append(p.getCliente());
			st.append(p.getNumeroProductos()+System.lineSeparator());
		}
		
		return st.toString();
	}
	
	public String mostrarPedidosYLineasPorID () {
		StringBuilder st = new StringBuilder();
		listaPedido.sort(new ComparatorPedidoID());
		for(Pedido p : listaPedido) {
			p.getListaLineas().sort(new ComparadorIDAscendente());
			for(Linea l : p.getListaLineas()) {
				st.append(l);
			}
		}
		
		
		return st.toString();
	}
	
	public String mostrarPedidosYLineasPorPrecio() {
		StringBuilder st = new StringBuilder();
		listaPedido.sort(new ComparatorPedidoPorPrecio());
		for(Pedido p : listaPedido) {
			p.getListaLineas().sort(new CompatorLineaPorPrecio());
			for(Linea l: p.getListaLineas()) {
				st.append(l);
			}
		}		
		return st.toString();
	}
	
	public Pedido mostrarPedidoConMayorCantidadDeProductos () {
		Pedido p1= new Pedido();
		for(Pedido p: listaPedido)
			if ( p1.getNumeroProductos()<p.getNumeroProductos()) {
				p1=p;
			}
		
		return p1;
	}
	
	public Pedido pedidoPrecioUnitarioMasAlto() {
		Pedido p1=new Pedido();
		Linea l1 = new Linea("7895", null, 1, 0.0);
		p1.addLinea(l1);
		
		for(Pedido p: listaPedido) {
			if(p1.precioUnitario()<=p.precioUnitario()) {
				p1=p;
			}
		}
		
		return p1;
	}
	public Pedido pedidoPrecioUnitarioMasBajo() {
		Pedido p1=new Pedido();
		Double contador= 9999.9999;
		for(Pedido p: listaPedido) {
			if(p.precioUnitario()<=contador) {
				contador=p.precioUnitario();
				p1=p;
			}
		}
		
		return p1;
	}
	//mal
	public Pedido pedidoPrecioMasCaro() {
		Pedido p1= new Pedido();
		for (Pedido p: listaPedido) {
			if (p1.getCostePedido()<p.getCostePedido()) {
				p1=p;
			}
		}
		
		return p1;
	}
	//mal
	public Pedido pedidoPrecioMasBarato() {
		Pedido p1= new Pedido();
		Double contador= 9999.9999;
		for (Pedido p:listaPedido) {
			if(p.getCostePedido()<contador) {
				contador=p.getCostePedido();
				p1=p;
			}
		}
		
		
		return p1;
	}
	
	public Cliente clienteConMayorNumProductos() {
		Pedido p1= new Pedido();
		for (Pedido p : listaPedido) {
			if (p.getNumeroProductos()>p1.getNumeroProductos()) {
				p1=p;
			}
		}
		
		
		return p1.getCliente();
	}
	
	public Cliente clienteVip() {
		Pedido p1= new Pedido();
		Double acumulador=0.0;
		Map<Cliente, Double> listaClientesVip = new HashMap<>();
		listaPedido.sort(new ComparatorCostePedido());
		for (Pedido p : listaPedido) {
			if (!listaClientesVip.containsKey(p.getCliente())) {
				listaClientesVip.put(p.getCliente(), p.getCostePedido());
			}else {
				listaClientesVip.put(p.getCliente(), listaClientesVip.get(p.getCliente())+p.getCostePedido());
			}
				
			}
		
		List<Pedido> listaPedidoAMostrar = new ArrayList<>();
		
		
		
		
		
		return p1.getCliente();
	}
	
	
}
