package model.dao;

import exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class modificaStatoOrdineDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        int codiceOrdine;
        String statoOrdine;

        codiceOrdine = (int) params[0];
        statoOrdine = params[1].toString();
        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call modificaStatoOrdine(?, ?)}");
            cs.setInt(1, codiceOrdine);
            cs.setString(2, statoOrdine);
            cs.executeQuery();
        }catch(SQLException e){
            throw new DAOException("Errore finalizzazione ordine: "+ e.getMessage());
        }
        return true;
    }
}
