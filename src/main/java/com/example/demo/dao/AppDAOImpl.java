package com.example.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    @Autowired // optional
    public AppDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public ResultDTO getOneRow(int id) {
        System.out.println("getOneRow()");

        String sql = "SELECT " +
                "(SELECT name FROM table1 WHERE id = :id LIMIT 1) AS colFromTable1, " +
                "(SELECT active FROM table2 LIMIT 1) AS colFromTable21," +
                "(SELECT table2_col2 FROM table2 WHERE id = :id LIMIT 1) AS colFromTable22, " +
                "(SELECT hobby FROM table3 WHERE id = :id LIMIT 1) AS colFromTable3";

        System.out.println(sql);

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", id);


        Object[] result = (Object[]) query.getSingleResult();

        return new ResultDTO(
                result[0] != null ? result[0].toString() : null,
                result[1] != null ? result[1].toString() : null,
                result[2] != null ? result[2].toString() : null,
                result[3] != null ? result[3].toString() : null
        );
    }
}
