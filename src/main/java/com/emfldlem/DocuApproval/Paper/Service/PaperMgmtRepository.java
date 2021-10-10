package com.emfldlem.DocuApproval.Paper.Service;

import org.springframework.data.repository.CrudRepository;

import com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity;

public interface PaperMgmtRepository extends CrudRepository<PaperMgmtEntity, String> {

    /*List<SaleMgmtEntity> findByDelYnAndSaleSeAndCmpnyCdAndSaleDateStartsWith(String delYn,String saleSe, String cmpnyCd, String saleDate);
    List<SaleMgmtEntity> findByDelYnAndSaleNoIn(String delYn, int[] saleNo);
    SaleMgmtEntity findBySaleNo(String saleNo);
    int countBySaleDateAndCmpnyCdAndDelYn(String saleDate,String cmpnyCd, String delYn);
    void deleteBySaleNo(String saleNo);
    List<SaleMgmtEntity> findByDelYnAndSaleSeAndCmpnyCdAndSaleDateBetween(String delYn,String saleSe, String cmpnyCd, String startSaleDate, String endSaleDate);*/


}
