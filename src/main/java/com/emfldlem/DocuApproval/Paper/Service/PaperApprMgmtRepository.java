package com.emfldlem.DocuApproval.Paper.Service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.emfldlem.DocuApproval.Paper.Entity.PaperApprMgmtEntity;

public interface PaperApprMgmtRepository extends CrudRepository<PaperApprMgmtEntity, String> {

    List<PaperApprMgmtEntity> findAllByPaperNo(String paperNo);
    PaperApprMgmtEntity findAllByPaperNoAndApprStatAndMbrNo(String paperNo,String apprStat, String mbrNo);
    PaperApprMgmtEntity findAllByPaperApprNoAndPaperNo(String paperApprNo,String paperNo);

    @Query(value = "SELECT count(*) FROM paper_appr_mgmt pam\n" +
                    "WHERE pam.paper_no = :paperNo\n" +
                    "AND pam.appr_order > :apprOrder", nativeQuery = true)
    int countRestAppr(@Param("paperNo") String paperNo, @Param("apprOrder") String apprOrder);


    @Query(value = "UPDATE sale_dtl_mgmt set del_yn ='Y' where sale_no = :saleNo", nativeQuery = true)
    void delbeforsaleDtlList(@Param("saleNo") String saleNo);

    @Query(value = "UPDATE paper_appr_mgmt SET\n" +
                    "appr_stat = '10'\n" +
                    "WHERE paper_no = :paperNo\n" +
                    "AND appr_order = :apprOrder", nativeQuery = true)
    int updateNextApprStat(@Param("paperNo") String paperNo, @Param("apprOrder") String apprOrder);


}
