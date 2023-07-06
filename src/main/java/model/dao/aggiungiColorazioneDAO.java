package model.dao;

import exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class aggiungiColorazioneDAO implements GenericProcedureDAO<Boolean> {
    @Override
    public Boolean execute(Object... params) throws DAOException {
        String pianta = (String)params[0], colore = (String)params[1];
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungiColorazione(?,?)}");
            cs.setString(1, pianta);
            cs.setString(2, colore);
            cs.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("Errore aggiunta nuovo colore" + e.getMessage());
        }
        return true;
    }
}
