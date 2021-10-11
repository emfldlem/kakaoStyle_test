package com.emfldlem.DocuApproval.Paper.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Table(name = "paper_mgmt")
@Entity
public class PaperMgmtEntity implements Serializable {

    @Id
    @GenericGenerator(name="gen_paper_no", strategy = "com.emfldlem.DocuApproval.Paper.Entity.paperNoGenerator", parameters = @org.hibernate.annotations.Parameter(name ="property", value = "lang"))
    @GeneratedValue(generator = "gen_paper_no")
    @Column(name="paper_no")
    String paperNo;

    @Column(name="paper_title")
    String paperTitle;

    @Column(name="paper_se")
    String paperSe;

    @Column(name="paper_desc")
    String paperDesc;

    @Column(name="paper_stat")
    String paperStat;

    @Column(name="reg_id")
    String regId;

    @Formula("(select c.mbr_nm from mbr_mgmt c where c.mbr_id = reg_id)")
    String regNm;

    @Column(name="reg_dtime")
    String regDtime;

    @Column(name="upd_id")
    String updId;

    @Column(name="upd_dtime")
    String updDtime;

    @Transient
    List<PaperApprMgmtEntity> paperApprMgmtEntityList;

}
