package model.dao;

import exceptions.DAOException;
import model.domain.Ordine;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class mostraOrdiniDAO implements GenericProcedureDAO<ArrayList<Ordine>>{

    @Override
    public ArrayList<Ordine> execute(Object... params) throws DAOException {
        ArrayList<Ordine> lista = new ArrayList<>();
        Ordine ordine;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call mostraOrdini()}");
            boolean status = cs.execute();
            if(status){
                ResultSet rs = cs.getResultSet();
                while (rs.next()){
                    ordine = new Ordine(rs.getInt(1), rs.getString(2), rs.getDate(3),
                            rs.getString(4), rs.getString(5));
                    lista.add(ordine);
                }
            }
        }catch(SQLException e){
            throw new DAOException("errore1" + e.getMessage());
        }
        return lista;
    }
}
