package model.dao;

import exceptions.DAOException;
import model.domain.Contatto;
import model.domain.Fornitore;
import model.domain.Rivendita;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mostraRivenditaDAO implements GenericProcedureDAO<Rivendita>{
    @Override
    public Rivendita execute(Object ... params) throws DAOException {
        String partitaIva = (String) params[0];
        Rivendita rivendita = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call mostraRivendita(?)}");
            cs.setString(1, partitaIva);
            boolean status = cs.execute();
            if (status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    rivendita =  new Rivendita(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)) ;
                }
            }
            status = cs.getMoreResults();
            do {
                if (status) {
                    ResultSet rs = cs.getResultSet();
                    while (rs.next()) {
                        Contatto contatto = new Contatto(rs.getString(1), rs.getString(2));
                        rivendita.addContatto(contatto);
                    }
                }
                status = cs.getMoreResults();
            } while (status || cs.getUpdateCount() != -1);


        } catch (SQLException e) {
            throw new DAOException("Errore " + e.getMessage());
        }
        return rivendita;
    }
}
