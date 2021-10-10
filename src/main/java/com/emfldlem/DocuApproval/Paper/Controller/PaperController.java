package com.emfldlem.DocuApproval.Paper.Controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emfldlem.Common.DateUtility;
import com.emfldlem.DocuApproval.Base.Controller.BaseController;
import com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity;
import com.emfldlem.DocuApproval.Paper.Service.PaperMgmtService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/paper")
public class PaperController extends BaseController {

    @Autowired
    PaperMgmtService paperMgmtService;

    @RequestMapping("/")
    public ModelAndView initMain() throws Exception{
        DateUtility dateUtility = new DateUtility();
        ModelAndView model = new ModelAndView("front/Main");

        String year = dateUtility.today("yyyy");
        HashMap<String, Object> params = new HashMap<>();
        params.put("year", year);


        int[] testArry = {1,2,4,5,6,7};

        model.addObject("testArry", testArry);

        return model;
    }

    @RequestMapping("/create")
    public ModelAndView paperCreate() throws Exception{

        ModelAndView model = new ModelAndView("front/PaperCreate");

        return model;
    }

    /**
     * 전자문서 등록
     * @param paperMgmt
     * @return
     */
    @RequestMapping(value = "/savePaperMgmt", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> savePaperMgmt(@RequestBody PaperMgmtEntity paperMgmt) {

        Map<String, String> resultMap = new HashMap<>();

        try {
            paperMgmtService.savePaperMgmt(paperMgmt);

        } catch (Exception e) {
            resultMap.put("res_cd", "F");
            resultMap.put("res_msg", e.getMessage());
            log.error(e.getMessage());
        }
        resultMap.put("res_cd", "S");
        return resultMap;
    }

}
