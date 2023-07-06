package model.dao;

import exceptions.DAOException;
import model.domain.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportOrdineDAO implements GenericProcedureDAO<ReportOrdine> {
    @Override
    public ReportOrdine execute(Object ... params) throws DAOException {
        ReportOrdine report = new ReportOrdine();
        Ordine ordine;
        int codiceOrdine = (int) params[0];

        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call reportOrdine(?)}");
            cs.setInt(1, codiceOrdine);
            boolean status = cs.execute();

            if (status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    ordine = new Ordine(rs.getInt(1), rs.getString(2), rs.getDate(3), Stato.fromString(rs.getString(4)), rs.getString(5), rs.getString(6));
                    report.addOrdine(ordine);
                }
                status = cs.getMoreResults();
                if (status) {
                    rs = cs.getResultSet();
                    while (rs.next()) {
                        Composizione composizione = new Composizione(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getFloat(5));
                        report.addSpecieToOrderReport(composizione);
                    }
                }
                status = cs.getMoreResults();
                if(status) {
                    rs = cs.getResultSet();
                    while (rs.next()) {
                        report.addQuantitaPrezzoTotali(rs.getInt(1), rs.getFloat(2));
                    }
                }
            }
        }catch(SQLException e){
            throw new DAOException("report ordine error" + e.getMessage());
        }
        return report;
    }
}
