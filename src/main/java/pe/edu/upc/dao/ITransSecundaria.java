package pe.edu.upc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.TransSecundaria;



@Repository
public interface ITransSecundaria  extends JpaRepository<TransSecundaria, Integer>{

}
