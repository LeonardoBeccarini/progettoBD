package model.dao;

import exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AggiungiContattoDAO implements GenericProcedureDAO<Boolean>{
    public Boolean execute(Object ... params) throws DAOException {
        String partitaIVA, recapito, mezzo;

        partitaIVA = (String) params[0];
        recapito= (String) params[1];
        mezzo = (String) params[2];

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungiContatto(?,?,?)}");
            cs.setString(1, partitaIVA);
            cs.setString(2, recapito);
            cs.setString(3, mezzo);
             cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore aggiunta nuovo contatto"+ e.getMessage());
        }
        return true;
    }
}
