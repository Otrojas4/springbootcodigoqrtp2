package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.TransSecundaria;


public interface ITransSecundariaService {
	public boolean insertar(TransSecundaria transsecundaria );

	public void eliminar(int idSec);
	
	List<TransSecundaria>listar();
	public TransSecundaria findOne(int idSec);
}
