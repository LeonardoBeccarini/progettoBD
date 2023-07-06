package model.dao;

import exceptions.DAOException;
import model.domain.SpeciePianta;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


public class RegistraSpecieDAO implements GenericProcedureDAO<SpeciePianta>{
    public SpeciePianta execute(Object... params) throws DAOException {
        String codicePianta = (String) params[0];
        String nomeLatino = (String) params[1];
        String nomeComune = (String) params[2];
        int giacenza = (int) params[3];
        boolean esotica = (boolean) params[4];
        boolean esterno = (boolean) params[5];
        float prezzo = (float) params[7];
        String colore = (String) params[6];
        String fornitore = (String) params[8];

        boolean fiorita = false;

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registraSpecie(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, codicePianta);
            cs.setString(2, nomeLatino);
            cs.setString(3, nomeComune);
            cs.setInt(4, giacenza);
            cs.setBoolean(5, esotica);
            cs.setBoolean(6, esterno);
            cs.setString(7, colore );
            cs.setFloat(8, prezzo);
            cs.setString(9, fornitore);
            cs.executeQuery();
        } catch (SQLException e) {
            throw new DAOException("registraSpecie error: " + e.getMessage());
        }
        if(colore != null) fiorita = true;
        return new SpeciePianta(codicePianta, nomeLatino, nomeComune, giacenza, prezzo, esotica, esterno, fiorita);
    }
}
