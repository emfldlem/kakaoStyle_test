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

public class paperNoGenerator implements IdentifierGenerator {

    private final Logger log = LoggerFactory.getLogger(paperNoGenerator.class);


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        PaperMgmtEntity paperMgmtEntity = (PaperMgmtEntity) o;
        if(StringUtils.isEmpty(paperMgmtEntity.getPaperNo())){
            paperMgmtEntity.setPaperNo("");
        }
        String prefix = "P";
        Connection connection = sharedSessionContractImplementor.connection();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT LPAD(SUBSTRING(MAX(paper_no),2,9)+1,9,0)  AS CDNUM FROM paper_mgmt");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(null==rs.getString("CDNUM")) return prefix+"000000001";
                if(null!=rs.getString("CDNUM")){
                    if(paperMgmtEntity.getPaperNo().length() >=10){
                        return paperMgmtEntity.getPaperNo();
                    }
                    if(paperMgmtEntity.getPaperNo().length() < 10){
                        return prefix+rs.getString("CDNUM");
                    }
                }
            }

        }
        catch (SQLException e) {
            log.error("");
            log.error("     paperNoGenerator.generate ::: "+e.getMessage());
            log.error("");
            e.printStackTrace();
        }
        return null;
    }


}
