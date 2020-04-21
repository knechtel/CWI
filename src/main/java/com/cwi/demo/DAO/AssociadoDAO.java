package com.cwi.demo.DAO;




import com.cwi.demo.bean.Associado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoDAO extends CrudRepository<Associado, Integer> {


}
