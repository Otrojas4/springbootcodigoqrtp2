package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.TransPrimaria;


public interface ITransPrimariaService {
	public boolean insertar(TransPrimaria transprimaria);

	public void eliminar(int idPri);
	
	List<TransPrimaria>listar();
	public TransPrimaria findOne(int idPri);
}
