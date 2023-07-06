package model.dao;

import exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ModificaPrezzoDAO implements GenericProcedureDAO<Boolean> {
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        float nuovoPrezzo;
        String pianta;
        Boolean outcome;
        pianta = (String) params[0];
        nuovoPrezzo = (float)params[1];
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call modificaPrezzo(?,?,?)}");
            cs.setString(1, pianta);
            cs.setObject(2, nuovoPrezzo);
            cs.executeQuery();
            outcome = cs.getBoolean(3);

        } catch(SQLException e){
            throw new DAOException("modifica prezzo error"+ e.getMessage());
        }
        return outcome;
    }
}
