package com.emfldlem.DocuApproval.Main.Controller;


import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emfldlem.Common.DateUtility;
import com.emfldlem.DocuApproval.Base.Controller.BaseController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "")
public class MainController extends BaseController {

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

}
