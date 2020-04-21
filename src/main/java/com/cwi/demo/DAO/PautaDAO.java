package com.cwi.demo.DAO;


import com.cwi.demo.bean.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaDAO extends CrudRepository<Pauta, Integer> {

}
