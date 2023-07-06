package model.dao;

import exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class aggiungiIndirizzoDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object... params) throws DAOException {
        String via = (String)params[0], citta = (String)params[2], fornitore = (String) params[3];
        int nCivico = (int)params[1];
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungiIndirizzo(?,?,?,?)}");
            cs.setString(1, via);
            cs.setInt(2, nCivico);
            cs.setString(3, citta);
            cs.setString(4, fornitore);
            cs.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("Errore aggiunta nuovo colore" + e.getMessage());
        }
        return true;
    }
}
