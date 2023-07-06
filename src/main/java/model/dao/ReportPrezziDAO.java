package model.dao;

import exceptions.DAOException;
import model.domain.Listino;
import model.domain.ReportPrezzi;

import java.sql.*;

public class ReportPrezziDAO implements GenericProcedureDAO<ReportPrezzi> {
    public ReportPrezzi execute(Object ... params) throws DAOException {
        ReportPrezzi reportPrezzi = new ReportPrezzi();
        String pianta = (String) params[0];
        Date dataFine = (Date) params[1];
        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call reportPrezzi(?,?)}");
            cs.setString(1,pianta);
            cs.setDate(2, dataFine);
            boolean status = cs.execute();
            if(status){
                ResultSet rs = cs.getResultSet();
                while(rs.next()){
                    Listino listino = new Listino(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getFloat(4));
                    reportPrezzi.addPrezzo(listino);
                }
            }

        }catch(SQLException e){
            throw new DAOException("errore report prezzi" + e.getMessage());
        }
        return reportPrezzi;
    }
}
