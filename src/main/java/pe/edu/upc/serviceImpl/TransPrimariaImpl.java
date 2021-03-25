package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.ITransPrimaria;
import pe.edu.upc.entity.TransPrimaria;
import pe.edu.upc.service.ITransPrimariaService;

@Service
public class TransPrimariaImpl implements ITransPrimariaService {
 
	@Autowired
	private ITransPrimaria tpDAO;
	
	@Override
	@Transactional
	public boolean insertar(TransPrimaria transprimaria) {
		TransPrimaria objTransPrimaria = tpDAO.save(transprimaria);
		if (objTransPrimaria == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void eliminar(int idPri) {
	tpDAO.delete(idPri);
		
	}

	@Override

	public List<TransPrimaria> listar() {
		
		return tpDAO.findAll();
	}
	
	@Override
	
	public TransPrimaria findOne(int idPri) {
		
		return tpDAO.findOne(idPri);
	}

}
