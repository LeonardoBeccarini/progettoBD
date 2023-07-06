package model.dao;

import exceptions.DAOException;
import model.domain.Composizione;
import model.domain.Ordine;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class mostraComposizioneDAO implements GenericProcedureDAO<ArrayList<Composizione>>{

    @Override
    public ArrayList<Composizione> execute(Object... params) throws DAOException{
        ArrayList<Composizione> lista = new ArrayList<>();
        Composizione composizione;
        int codice = (int)params[0];
        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call mostraComposizione(?)}");
            cs.setInt(1, codice);
            boolean status = cs.execute();
            if(status){
                ResultSet rs = cs.getResultSet();
                while (rs.next()){
                    composizione = new Composizione(rs.getString(1), rs.getInt(2));
                    lista.add(composizione);
                }
            }
        }catch(SQLException e){
            throw new DAOException("errore sql" + e.getMessage());
        }
        return lista;
    }
}
