package com.emfldlem.DocuApproval.Paper.Entity;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class paperApprNoGenerator implements IdentifierGenerator {

    private final Logger log = LoggerFactory.getLogger(paperApprNoGenerator.class);


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        PaperApprMgmtEntity o1 = (PaperApprMgmtEntity) o;
        if(StringUtils.isEmpty(o1.getPaperApprNo())){
            o1.setPaperApprNo("");
        }
        String prefix = "PA";
        Connection connection = sharedSessionContractImplementor.connection();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT LPAD(SUBSTRING(MAX(paper_appr_no),2,9)+1,9,0)  AS CDNUM FROM paper_appr_mgmt");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(null==rs.getString("CDNUM")) return prefix+"00000001";
                if(null!=rs.getString("CDNUM")){
                    if(o1.getPaperApprNo().length() >=10){
                        return o1.getPaperApprNo();
                    }
                    if(o1.getPaperApprNo().length() < 10){
                        return prefix+rs.getString("CDNUM");
                    }
                }
            }

        }
        catch (SQLException e) {
            log.error("");
            log.error("     paperApprNoGenerator.generate ::: "+e.getMessage());
            log.error("");
            e.printStackTrace();
        }
        return null;
    }


}
