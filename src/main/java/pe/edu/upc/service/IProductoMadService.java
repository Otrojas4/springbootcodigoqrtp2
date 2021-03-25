package pe.edu.upc.service;

import java.util.List;


import pe.edu.upc.entity.ProductoMad;

public interface IProductoMadService{
	public boolean insertar(ProductoMad ProductoMad);

	public boolean modificar(ProductoMad ProductoMad);

	public void eliminar(int id);
	
	List<ProductoMad>listar();
	public ProductoMad findOne(int id);
	public ProductoMad listarId(int id);
	
}
