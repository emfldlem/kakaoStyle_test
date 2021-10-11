package com.emfldlem.DocuApproval.Paper.Service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.emfldlem.Common.DateUtility;
import com.emfldlem.DocuApproval.Paper.Dao.PaperDao;
import com.emfldlem.DocuApproval.Paper.Entity.PaperApprMgmtEntity;
import com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity;

@Service
public class PaperMgmtService {

    @Autowired
    PaperMgmtRepository paperMgmtRepository;

    @Autowired
    PaperApprMgmtRepository paperApprMgmtRepository;

    @Autowired
    PaperDao paperDao;

    public void setDefaultParam(PaperMgmtEntity o1) {
        DateUtility dateUtility = new DateUtility();

        if(ObjectUtils.isEmpty(o1.getRegDtime())) {
            o1.setRegId("sang");
            o1.setRegDtime(dateUtility.today("yyyy-MM-dd hh:mm:ss"));
        }
        o1.setUpdId("sang");
        o1.setUpdDtime(dateUtility.today("yyyy-MM-dd hh:mm:ss"));

        /*if(StringUtils.isEmpty(o1.getDelYn())) {
            o1.setDelYn("N");
        }*/
    }

    public Page<PaperMgmtEntity> getPaperListWithPaperStat(Map<String, String> param, Pageable pageable) {
        return paperMgmtRepository.findAllByRegIdAndPaperStat(param.get("reg_id"), param.get("paper_stat"), pageable);
    }


    public Page<PaperMgmtEntity> getPaperInBoxList(Map<String, String> param, Pageable pageable) {
        return paperMgmtRepository.getPaperInBoxList(param.get("mbr_no"), pageable);
    }

    public PaperMgmtEntity getPaper(Map<String, String> param) throws Exception {
        String paperNo = param.get("paper_no");
        if(StringUtils.isEmpty(paperNo)) {
            throw new Exception();
        } else {
            return paperMgmtRepository.findByPaperNo(paperNo);
        }
    }

    public PaperMgmtEntity getPaperDao(Map<String, String> param) throws Exception {
        String paperNo = param.get("paper_no");
        if(StringUtils.isEmpty(paperNo)) {
            throw new Exception();
        } else {
            return paperDao.getPaperDetail(param);
        }
    }


    public void savePaperMgmt(PaperMgmtEntity paperMgmtEntity) throws Exception {
        setDefaultParam(paperMgmtEntity);
        paperMgmtEntity.setPaperStat("10"); //문서 대기 상태
        paperMgmtEntity = paperMgmtRepository.save(paperMgmtEntity);
        List<PaperApprMgmtEntity> paperApprMgmtList =paperMgmtEntity.getPaperApprMgmtEntityList();

        if(!ObjectUtils.isEmpty(paperApprMgmtList)) {
            for(PaperApprMgmtEntity paperApprMgmt : paperApprMgmtList) {
                paperApprMgmt.setPaperNo(paperMgmtEntity.getPaperNo());
                paperApprMgmt.setPaperApprNo(null);
                paperApprMgmtRepository.save(paperApprMgmt);
            }
        } else {
            throw new Exception();
        }
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
