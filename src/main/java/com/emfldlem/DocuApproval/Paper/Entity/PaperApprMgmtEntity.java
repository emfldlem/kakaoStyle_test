package com.emfldlem.DocuApproval.Paper.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Table(name = "paper_appr_mgmt")
@Entity
public class PaperApprMgmtEntity implements Serializable {

    @Id
    @GenericGenerator(name="gen_paper_appr_no", strategy = "com.emfldlem.DocuApproval.Paper.Entity.paperApprNoGenerator", parameters = @org.hibernate.annotations.Parameter(name ="property", value = "lang"))
    @GeneratedValue(generator = "gen_paper_appr_no")
    @Column(name="paper_appr_no")
    String paperApprNo;

    @Column(name="paper_no")
    String paperNo;

    @Column(name="mbr_no")
    String mbrNo;

    @Column(name="appr_order")
    String apprOrder;

    @Column(name="appr_stat")
    String apprStat;

    @Column(name="appr_comment")
    String apprComment;

    @Column(name="reg_id")
    String regId;
    @Column(name="reg_date")
    String regDate;

    @Column(name="upd_id")
    String updId;
    @Column(name="upd_date")
    String updDate;

}
