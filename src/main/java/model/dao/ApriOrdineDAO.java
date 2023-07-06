package model.dao;

import exceptions.DAOException;
import model.domain.Ordine;
import model.domain.Stato;

import java.sql.*;
import java.time.LocalDate;

public class ApriOrdineDAO implements GenericProcedureDAO<Ordine>{
    @Override
    public Ordine execute(Object ... params) throws DAOException {
        String rivendita = (String) params[0];
        String indirizzo= (String) params[1];
        String contatto = (String) params[2];
        String specie= (String) params[3];
        int quantita = (int) params[4];
        int codiceOrdine;
        Date data;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call apriOrdine(?,?,?,?,?,?)}");
            cs.setString(1, rivendita);
            cs.setString(2, indirizzo);
            cs.setString(3, contatto);
            cs.setString(4, specie);
            cs.setInt(5, quantita);
            cs.executeQuery();
            codiceOrdine = cs.getInt(6);

            data = Date.valueOf(LocalDate.now());

        } catch(SQLException e) {
            throw new DAOException("Registra ordine error: " + e.getMessage());
        }
        return new Ordine(codiceOrdine, indirizzo, data, Stato.APERTO, contatto, rivendita);
    }
}
