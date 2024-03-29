package model.dao;

import exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AggiungiSpecieDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        int quantita, codiceOrdine;
        String codicePianta;
        codiceOrdine = (int) params[0];
        codicePianta = (String) params[1];
        quantita = (int) params[2];
        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungiSpecieOrdine(?,?,?)}");
            cs.setInt(1, codiceOrdine);
            cs.setString(2, codicePianta);
            cs.setInt(3, quantita);
            cs.executeQuery();
        }catch(SQLException e){
            throw new DAOException("Errore aggiunta specie"+ e.getMessage());
        }
        return true;
    }
}
