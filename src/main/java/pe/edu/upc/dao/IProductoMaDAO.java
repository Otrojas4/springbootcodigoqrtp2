package pe.edu.upc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.ProductoMad;

@Repository
public interface IProductoMaDAO extends JpaRepository<ProductoMad,Integer>{

}
