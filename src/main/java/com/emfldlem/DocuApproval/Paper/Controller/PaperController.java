package com.emfldlem.DocuApproval.Paper.Controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emfldlem.Common.Const;
import com.emfldlem.DocuApproval.Base.Controller.BaseController;
import com.emfldlem.DocuApproval.Paper.Entity.PaperApprMgmtEntity;
import com.emfldlem.DocuApproval.Paper.Entity.PaperMgmtEntity;
import com.emfldlem.DocuApproval.Paper.Service.PaperApprMgmtService;
import com.emfldlem.DocuApproval.Paper.Service.PaperMgmtService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/paper")
public class PaperController extends BaseController {

    @Autowired
    PaperMgmtService paperMgmtService;
    @Autowired
    PaperApprMgmtService paperApprMgmtService;


    /**
     * OUTBOX 리스트
     * @return MODAL
     * @throws Exception
     */
    @RequestMapping("/outBoxList")
    public ModelAndView outBoxList() throws Exception{
        ModelAndView model = new ModelAndView("front/Paper/PaperOutBoxList");
        Map<String, String> param = new HashMap<>();
        param.put("reg_id", "sang"); //추후 변경
        Pageable pageable = PageRequest.of(0, 10);

        param.put("paper_stat", Const.PAPER_STAT_10);
        Page<PaperMgmtEntity> paperList = paperMgmtService.getPaperListWithPaperStat(param, pageable);

        model.addObject("paperMgmtList", paperList.getContent());
        model.addObject("totalElements", paperList.getTotalElements());
        model.addObject("totalPages", paperList.getTotalPages());
        model.addObject("currentPage", paperList.getNumber());

        return model;
    }

    /**
     * OUTBOX 리스트 페이징
     * @param pageable
     * @return MODAL
     * @throws Exception
     */
    @RequestMapping("/getPaperOutBoxList")
    public ModelAndView getPaperOutBoxList(Pageable pageable) throws Exception{
        ModelAndView model = new ModelAndView("front/Paper/include/IncPaperOutBoxList");
        Map<String, String> param = new HashMap<>();
        param.put("reg_id", "sang"); //추후 변경
        param.put("paper_stat", Const.PAPER_STAT_10);

        Page<PaperMgmtEntity> paperList = paperMgmtService.getPaperListWithPaperStat(param, pageable);
        model.addObject("paperMgmtList", paperList.getContent());

        return model;
    }

    @RequestMapping("/getOutBoxPaperDetail")
    public ModelAndView getOutBoxPaperDetail(@RequestParam String paper_no) throws Exception{
        ModelAndView model = new ModelAndView("front/Paper/PaperOutBoxDetail");
        Map<String, String> param = new HashMap<>();
        param.put("reg_id", "sang"); //추후 변경

        param.put("paper_no", paper_no);

        PaperMgmtEntity paper = paperMgmtService.getPaper(param);
        List<PaperApprMgmtEntity> paperApprList = paperApprMgmtService.getPaperApprList(param);

        model.addObject("paper", paper);
        model.addObject("paperApprList", paperApprList);

        return model;
    }

    /**
     * INBOX 리스트
     * @return MODAL
     * @throws Exception
     */
    @RequestMapping("/inBoxList")
    public ModelAndView inBoxList() throws Exception{
        ModelAndView model = new ModelAndView("front/Paper/PaperInBoxList");
        Map<String, String> param = new HashMap<>();
        param.put("reg_id", "sang"); //추후 변경
        param.put("mbr_no", "M202110110004"); //추후 변경

        Pageable pageable = PageRequest.of(0, 10);

        Page<PaperMgmtEntity> paperList = paperMgmtService.getPaperInBoxList(param, pageable);

        model.addObject("paperMgmtList", paperList.getContent());
        model.addObject("totalElements", paperList.getTotalElements());
        model.addObject("totalPages", paperList.getTotalPages());
        model.addObject("currentPage", paperList.getNumber());

        return model;
    }

    /**
     * INBOX 리스트 페이징
     * @param pageable
     * @return MODAL
     * @throws Exception
     */
    @RequestMapping("/getPaperInBoxList")
    public ModelAndView getPaperInBoxList(Pageable pageable) throws Exception{
        ModelAndView model = new ModelAndView("front/Paper/include/IncPaperInBoxList");
        Map<String, String> param = new HashMap<>();
        param.put("reg_id", "sang"); //추후 변경
        param.put("mbr_no", "M202110110004"); //추후 변경

        Page<PaperMgmtEntity> paperList = paperMgmtService.getPaperInBoxList(param, pageable);
        model.addObject("paperMgmtList", paperList.getContent());

        return model;
    }

    @RequestMapping("/getInBoxPaperDetail")
    public ModelAndView getInBoxPaperDetail(@RequestParam String paper_no) throws Exception{
        ModelAndView model = new ModelAndView("front/Paper/PaperInBoxDetail");
        Map<String, String> param = new HashMap<>();
        param.put("reg_id", "sang"); //추후 변경
        param.put("mbr_no", "M202110110004"); //추후 변경
        param.put("paper_no", paper_no);

        PaperMgmtEntity paper = paperMgmtService.getPaper(param);
        PaperApprMgmtEntity paperAppr = paperApprMgmtService.getPaperAppr(param);

        model.addObject("paper", paper);
        model.addObject("paperAppr", paperAppr);

        return model;
    }


    @RequestMapping("/getPaper")
    public ModelAndView getPaper(@RequestParam String paper_no) throws Exception{
        ModelAndView model = new ModelAndView("front/Paper/PaperInBoxDetail");
        Map<String, String> param = new HashMap<>();
        param.put("reg_id", "sang"); //추후 변경

        param.put("paper_no", paper_no);

        PaperMgmtEntity paper = paperMgmtService.getPaper(param);
        List<PaperApprMgmtEntity> paperApprList = paperApprMgmtService.getPaperApprList(param);

        model.addObject("paper", paper);
        model.addObject("paperApprList", paperApprList);

        return model;
    }

    @RequestMapping("/create")
    public ModelAndView paperCreate() throws Exception{

        ModelAndView model = new ModelAndView("front/Paper/PaperCreate");

        return model;
    }

    /**
     * 전자문서 등록
     * @param paperMgmt
     * @return
     */
    @RequestMapping(value = "/savePaper", method = RequestMethod.POST)
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

    /**
     * 전자문서 승인/거절 처리
     * @param PaperApprMgmtEntity
     * @return
     */
    @RequestMapping(value = "/savePaperAppr", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> savePaperAppr(@RequestBody PaperApprMgmtEntity paperApprMgmt) {

        Map<String, String> resultMap = new HashMap<>();

        try {
            paperApprMgmtService.processApprStat(paperApprMgmt);
        } catch (Exception e) {
            resultMap.put("res_cd", "F");
            resultMap.put("res_msg", e.getMessage());
            log.error(e.getMessage());
        }
        resultMap.put("res_cd", "S");
        return resultMap;
    }

}
