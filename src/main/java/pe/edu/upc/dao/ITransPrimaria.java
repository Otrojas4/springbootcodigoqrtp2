package pe.edu.upc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.TransPrimaria;


@Repository
public interface ITransPrimaria extends JpaRepository<TransPrimaria, Integer> {

}
