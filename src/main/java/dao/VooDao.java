package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.repository.CrudRepository;

import app.Voo;

@Repository
public interface VooDao extends JpaRepository<Voo, Integer> {

	//Query createQuery(String string);

}
