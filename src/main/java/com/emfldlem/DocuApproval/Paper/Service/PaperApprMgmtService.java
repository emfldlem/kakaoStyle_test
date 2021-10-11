package com.emfldlem.DocuApproval.Paper.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.emfldlem.Common.Const;
import com.emfldlem.Common.DateUtility;
import com.emfldlem.DocuApproval.Paper.Entity.PaperApprMgmtEntity;
import com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity;

@Service
public class PaperApprMgmtService {

    @Autowired
    PaperApprMgmtRepository paperApprMgmtRepository;

    @Autowired
    PaperMgmtRepository paperMgmtRepository;

    public void setDefaultParam(PaperApprMgmtEntity o1) {
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

    public PaperApprMgmtEntity savePaperApprMgmt(PaperApprMgmtEntity PaperApprMgmtEntity) {
        setDefaultParam(PaperApprMgmtEntity);

        return paperApprMgmtRepository.save(PaperApprMgmtEntity);
    }

    public List<PaperApprMgmtEntity> getPaperApprList(Map<String, String> param) throws Exception{
        String paperNo = param.get("paper_no");
        if(StringUtils.isEmpty(paperNo)) {
            throw new Exception();
        } else {
            return paperApprMgmtRepository.findAllByPaperNo(paperNo);
        }
    }

    public PaperApprMgmtEntity getPaperAppr(Map<String, String> param) throws Exception{
        String paperNo = param.get("paper_no");
        if(StringUtils.isEmpty(paperNo)) {
            throw new Exception();
        } else {
            return paperApprMgmtRepository.findAllByPaperNoAndApprStatAndMbrNo(paperNo, Const.APPR_STAT_10, param.get("mbr_no"));
        }
    }
    public PaperApprMgmtEntity getPaperApprWithPaperApprNoWithPaperNo(Map<String, String> param){
        return paperApprMgmtRepository.findAllByPaperApprNoAndPaperNo(param.get("paper_appr_no"), param.get("paper_no"));
    }

    public void processApprStat(PaperApprMgmtEntity paperApprMgmt)  {
        DateUtility dateUtility = new DateUtility();
        Map<String, String> param = new HashMap<>();
        param.put("paper_appr_no", paperApprMgmt.getPaperApprNo());
        param.put("paper_no", paperApprMgmt.getPaperNo());

        String apprStat = paperApprMgmt.getApprStat();

        PaperApprMgmtEntity origin = getPaperApprWithPaperApprNoWithPaperNo(param);
        origin.setApprStat(apprStat);
        origin.setApprComment(paperApprMgmt.getApprComment());
        savePaperApprMgmt(origin);

        PaperMgmtEntity paperMgmt = paperMgmtRepository.findByPaperNo(origin.getPaperNo());

        //만약 거절이라면 문서 자체를 거절로 변경후 종료
        if(Const.APPR_STAT_30.equals(apprStat)) {
            paperMgmt.setPaperStat(Const.PAPER_STAT_30);
            paperMgmt.setUpdId("system"); //추후 변경
            paperMgmt.setUpdDtime(dateUtility.today("yyyy-MM-dd hh:mm:ss"));
            paperMgmtRepository.save(paperMgmt);
        } else {
            //마지막 결재자인지 확인
            int restApprCount = paperApprMgmtRepository.countRestAppr(origin.getPaperNo(), origin.getApprOrder());

            //마지막 결제자가 아니고 승인이라면 뒤의 순서에 상태 값을 10으로 변경
            if(restApprCount > 0 && Const.APPR_STAT_10.equals(apprStat)) {
                paperApprMgmtRepository.updateNextApprStat(origin.getPaperNo(), String.valueOf(Integer.parseInt(origin.getApprOrder())+1));
            } else { //마지막 결제자 라면
                paperMgmt.setPaperStat(Const.PAPER_STAT_20);
                paperMgmt.setUpdId("system"); //추후 변경
                paperMgmt.setUpdDtime(dateUtility.today("yyyy-MM-dd hh:mm:ss"));
                paperMgmtRepository.save(paperMgmt);
            }
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
