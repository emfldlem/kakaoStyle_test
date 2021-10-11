package com.emfldlem.DocuApproval.Mbr.Service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.emfldlem.Common.DateUtility;
import com.emfldlem.DocuApproval.Mbr.Entity.MbrMgmtEntity;

@Service
public class MbrMgmtService  {

    @Autowired
    MbrMgmtRepository mbrMgmtRepository;

    public void setDefaultParam(MbrMgmtEntity o1) {
        DateUtility dateUtility = new DateUtility();

        if(ObjectUtils.isEmpty(o1.getRegDtime())) {
            o1.setRegId("sang");
            o1.setRegDtime(dateUtility.today("yyyy-MM-dd hh:mm:ss"));
        }
        o1.setUpdId("sang");
        o1.setUpdDtime(dateUtility.today("yyyy-MM-dd hh:mm:ss"));
    }

    public List<MbrMgmtEntity> getMbrMgmtList(Map<String, String> param) {
        return mbrMgmtRepository.findByWthdrwlYnAndMbrNmContains("Y", param.get("mbr_nm"));
    }

    public List<MbrMgmtEntity> getMbrMgmtListWithMbrNm(Map<String, String> param) {
        return mbrMgmtRepository.findByWthdrwlYnAndMbrNmContains("Y", param.get("mbr_nm"));
    }

    public MbrMgmtEntity saveMbrMgmt(MbrMgmtEntity mbrMgmtEntity) {
        setDefaultParam(mbrMgmtEntity);
        return mbrMgmtRepository.save(mbrMgmtEntity);
    }


}
