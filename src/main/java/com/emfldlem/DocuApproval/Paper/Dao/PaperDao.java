package com.emfldlem.DocuApproval.Paper.Dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity;

@Repository
public class PaperDao {
    protected static final String NAMESPACE = "com.emfldlem.DocuApproval.Paper.Service.";

    @Autowired
    private SqlSession sqlSession;

    public PaperMgmtEntity getPaperDetail(Map<String, String> params) {
        return sqlSession.selectOne(NAMESPACE+"getPaperDetail", params);
    }


}
