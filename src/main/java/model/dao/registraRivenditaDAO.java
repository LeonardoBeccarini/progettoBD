package model.dao;

import exceptions.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class registraRivenditaDAO implements GenericProcedureDAO<Boolean>{

    @Override
    public Boolean execute(Object... params) throws DAOException {
        String partitaIVA = (String) params[0], indFatturazione = (String) params[1],
                indFisico = (String) params[2], nomeReferente = (String) params[3], cognomeReferente=(String) params[4],
                recapito = (String) params[5], mezzo = (String) params[6];
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registraRivendita(?,?,?,?,?,?,?)}");
            cs.setString(1, partitaIVA);
            cs.setString(2, indFisico);
            cs.setString(3, indFatturazione);
            cs.setString(4, nomeReferente);
            cs.setString(5, cognomeReferente);
            cs.setString(6, recapito);
            cs.setString(7, mezzo);
            cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore"+e.getMessage());
        }
        return true;
    }



}
