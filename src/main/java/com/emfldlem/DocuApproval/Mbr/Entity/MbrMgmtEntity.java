package com.emfldlem.DocuApproval.Mbr.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import lombok.Data;

@Data
@Table(name = "mbr_mgmt")
@Entity
public class MbrMgmtEntity implements Serializable {

    @Id
    @Column(name="mbr_no")
    String mbrNo;

    @Column(name="mbr_id")
    String mbrId;

    @Column(name="mbr_nm")
    String mbrNm;

    @Column(name="mbr_se")
    String mbrSe;

    @Column(name="email")
    String email;

    @Column(name="dept_no")
    String deptNo;

    @Formula("(select c.dept_nm from dept_mgmt c where c.dept_no = dept_no)")
    String deptNm;

    @Column(name="wthdrwl_yn")
    String wthdrwlYn;

    @Column(name="reg_id")
    String regId;
    @Column(name="reg_dtime")
    String regDtime;

    @Column(name="upd_id")
    String updId;
    @Column(name="upd_dtime")
    String updDtime;

}
