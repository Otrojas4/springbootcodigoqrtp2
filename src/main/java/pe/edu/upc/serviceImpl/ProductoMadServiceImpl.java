package pe.edu.upc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.dao.IProductoMaDAO;
import pe.edu.upc.entity.ProductoMad;
import pe.edu.upc.service.IProductoMadService;

@Service
public class ProductoMadServiceImpl implements IProductoMadService {

	@Autowired
	private IProductoMaDAO dMad;
	
	
	
	
	
	@Override
	@Transactional
	public boolean insertar(ProductoMad ProductoMad) {
		ProductoMad ObjMad = dMad.save(ProductoMad);
		
		if (ObjMad == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean modificar(ProductoMad ProductoMad) {
		boolean flag = false;
		try {
			dMad.save(ProductoMad);
			flag = true;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	return flag;
	}
	@Override
	public void eliminar(int id) {
		dMad.delete(id);
		
	}

	@Override
	public List<ProductoMad> listar() {
		// TODO Auto-generated method stub
		return dMad.findAll();
	}

	@Override
	public ProductoMad findOne(int id) {
		// TODO Auto-generated method stub
		return dMad.findOne(id);
	}

	@Override
	public ProductoMad listarId(int id) {
		return dMad.findOne(id);
	}

}
