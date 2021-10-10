package com.emfldlem.DocuApproval.Paper.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.emfldlem.Common.DateUtility;
import com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity;

@Service
public class PaperMgmtService {

    @Autowired
    PaperMgmtRepository paperMgmtRepository;

    public void setDefaultParam(PaperMgmtEntity o1) {
        DateUtility dateUtility = new DateUtility();

        if(ObjectUtils.isEmpty(o1.getRegDate())) {
            o1.setRegId("sang");
            o1.setRegDate(dateUtility.today("yyyy-MM-dd"));
        }
        o1.setUpdId("sang");
        o1.setUpdDate(dateUtility.today("yyyy-MM-dd"));

        /*if(StringUtils.isEmpty(o1.getDelYn())) {
            o1.setDelYn("N");
        }*/
    }

    public PaperMgmtEntity savePaperMgmt(PaperMgmtEntity paperMgmtEntity) {
        setDefaultParam(paperMgmtEntity);

        return paperMgmtRepository.save(paperMgmtEntity);
    }


    /*public List<SaleMgmtEntity> getSaleListWithSaleSeAndCmpnyCdAndSaleDate(String saleSe, String cmpnyCd, String saleDate) {
        saleDate = saleDate.replace("-","");
       return saleMgmtRepository.findByDelYnAndSaleSeAndCmpnyCdAndSaleDateStartsWith("N", saleSe, cmpnyCd, saleDate);
        //return saleMgmtRepository.findByDelYnAndSaleSeAndCmpnyCdAndSaleDateBetween("N", saleSe, cmpnyCd, "20200401","20200401");
    }*/

    /*public List<SaleMgmtEntity> getSaleListwithSaleNo(String[] noList) {

        int[] intNoList = Arrays.asList(noList).stream().mapToInt(Integer::parseInt).toArray();

        return saleMgmtRepository.findByDelYnAndSaleNoIn("N", intNoList);
    }*/

    /*public SaleMgmtEntity getSale(String saleNo) {
        return saleMgmtRepository.findBySaleNo(saleNo);
    }*/


   /* public int getCountSale(String saleDate, String CmpnyCd) {
        saleDate = saleDate.replace("-","");
        return saleMgmtRepository.countBySaleDateAndCmpnyCdAndDelYn(saleDate,CmpnyCd,"N");
    }*/

    /*@Transactional
    public void deleteSale(String saleNo) {
        saleMgmtRepository.deleteBySaleNo(saleNo);
    }*/

}
