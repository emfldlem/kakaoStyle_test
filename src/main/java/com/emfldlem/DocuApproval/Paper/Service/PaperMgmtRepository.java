package com.emfldlem.DocuApproval.Paper.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity;

public interface PaperMgmtRepository extends JpaRepository<PaperMgmtEntity, String> {

    Page<PaperMgmtEntity> findAllByRegIdAndPaperStat(String regId, String paperStat, Pageable pageable);
    Page<PaperMgmtEntity> findAllByRegId(String regId, Pageable pageable);
    PaperMgmtEntity findByPaperNo(String paperNo);

    @Query(value = "SELECT pm.* FROM paper_mgmt pm, paper_appr_mgmt pam WHERE pm.paper_no = pam.paper_no\n" +
                   "AND pam.mbr_no = :mbrNo AND pam.appr_stat = '10'", nativeQuery = true)
    Page<PaperMgmtEntity> getPaperInBoxList(@Param("mbrNo") String mbrNo, Pageable pageable);

    //List<PaperMgmtEntity> findByRegId(String regId, Pageable pageable);

    //List<PaperMgmtEntity> findByRegIdAndPaperStat(String regId,String paperStat);


    /*List<SaleMgmtEntity> findByDelYnAndSaleSeAndCmpnyCdAndSaleDateStartsWith(String delYn,String saleSe, String cmpnyCd, String saleDate);
    List<SaleMgmtEntity> findByDelYnAndSaleNoIn(String delYn, int[] saleNo);
    SaleMgmtEntity findBySaleNo(String saleNo);
    int countBySaleDateAndCmpnyCdAndDelYn(String saleDate,String cmpnyCd, String delYn);
    void deleteBySaleNo(String saleNo);
    List<SaleMgmtEntity> findByDelYnAndSaleSeAndCmpnyCdAndSaleDateBetween(String delYn,String saleSe, String cmpnyCd, String startSaleDate, String endSaleDate);*/


}
