package com.emfldlem.DocuApproval.Main.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.emfldlem.DocuApproval.Base.Controller.BaseController;
import com.emfldlem.DocuApproval.Mbr.Entity.MbrMgmtEntity;
import com.emfldlem.DocuApproval.Mbr.Service.MbrMgmtService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/popup")
public class PopupController extends BaseController {



    @Autowired
    MbrMgmtService mbrMgmtService;

    /**
     * 사용자 검색
     * @return
     */
    @RequestMapping("/getSchMbrList")
    public ModelAndView getSchMbrList(@RequestParam Map<String, String> param) {
        ModelAndView model = new ModelAndView("front/popup/schMbr");
        List<MbrMgmtEntity> mbrMgmtList = mbrMgmtService.getMbrMgmtList(param);

        model.addObject("mbrMgmtList", mbrMgmtList);

        return model;
    }

}
