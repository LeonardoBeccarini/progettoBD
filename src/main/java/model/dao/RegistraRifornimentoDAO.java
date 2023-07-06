package model.dao;

import exceptions.DAOException;
import model.domain.Ordine;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistraRifornimentoDAO  implements GenericProcedureDAO<Boolean>{
    public Boolean execute(Object ... params) throws DAOException {
        int quantita;
        String codiceFornitore;
        String codicePianta;
        codiceFornitore = (String) params[0];
        codicePianta = (String) params[1];
        quantita = (int) params[2];
        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registraRifornimento(?,?,?)}");
            cs.setString(1, codiceFornitore);
            cs.setString(2, codicePianta);
            cs.setInt(3, quantita);
            cs.executeQuery();
        }catch(SQLException e){
            throw new DAOException("Errore registra rifornimento"+ e.getMessage());
        }
        return true;
    }
}
