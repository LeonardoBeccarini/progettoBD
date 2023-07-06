package model.dao;

import exceptions.DAOException;
import model.domain.Fornitore;
import model.domain.Giacenza;
import model.domain.ReportGiacenze;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGiacenzeDAO implements GenericProcedureDAO<ReportGiacenze>{
    @Override
    public ReportGiacenze execute(Object ... params) throws DAOException {
        ReportGiacenze report = new ReportGiacenze();
        int index;
        int quantita = (int) params[0];
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call reportGiacenze(?)}");
            cs.setInt(1, quantita);
            boolean status = cs.execute();
            index = 0;
            if (status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    Giacenza giacenza = new Giacenza(rs.getString(1), rs.getString(2), rs.getInt(3), index++);
                    report.addGiacenza(giacenza);
                }
                index = 0;
                status = cs.getMoreResults();
                do {
                    if (status) {
                        rs = cs.getResultSet();
                        while (rs.next()) {
                            Fornitore fornitore = new Fornitore(rs.getString(1), rs.getString(2));
                            report.addFornitoreToReport(fornitore, index++);
                        }
                    }

                    status = cs.getMoreResults();
                } while (status || cs.getUpdateCount() != -1);
            }
        }catch(SQLException e){
            throw new DAOException("report error" + e.getMessage());
        }
        return report;
    }
}
