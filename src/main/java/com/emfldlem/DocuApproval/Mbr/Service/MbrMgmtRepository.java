package com.emfldlem.DocuApproval.Mbr.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emfldlem.DocuApproval.Mbr.Entity.MbrMgmtEntity;

public interface MbrMgmtRepository extends JpaRepository<MbrMgmtEntity, String> {
    List<MbrMgmtEntity> findByWthdrwlYnAndMbrNmContains(String wthdrwlYn, String mbrNm);

    List<MbrMgmtEntity> findByWthdrwlYn(String wthdrwlYn);
}
