package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.ITransSecundaria;
import pe.edu.upc.entity.TransSecundaria;

import pe.edu.upc.service.ITransSecundariaService;

@Service
public class TransSecundariaImpl implements ITransSecundariaService {

	@Autowired
	private ITransSecundaria tsDAO;
	
	
	
	@Override
	@Transactional
	public boolean insertar(TransSecundaria transsecundaria) {
		TransSecundaria objTransSecundaria = tsDAO.save(transsecundaria);
		if (objTransSecundaria == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void eliminar(int idSec) {
		tsDAO.delete(idSec);
		
	}

	@Override
	public List<TransSecundaria> listar() {
		
		return tsDAO.findAll();
	}

	@Override
	public TransSecundaria findOne(int idSec) {

		return tsDAO.findOne(idSec);
	}

}
