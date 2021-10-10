package com.emfldlem.DocuApproval.Main.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emfldlem.DocuApproval.Base.Controller.BaseController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/print")
public class PrintController extends BaseController {


   /*
    @RequestMapping("/estmt")
    public ModelAndView estmtPrint(@RequestParam String estmtNo) {
        ModelAndView model = new ModelAndView("common/printTemplate/estmtPrint");


        EstmtMgmtEntity estmtMgmtEntity = estmtMgmtService.getEstmt(estmtNo);
        List<EstmtDtlMgmtEntity> estmtDtlMgmtEntityList = estmtDtlMgmtService.getEstmtDtlListWithEstmtNo(estmtNo);

        CompanyMgmtEntity companyMgmtEntity = companyMgmtService.getCompanyMgmt(estmtMgmtEntity.getCmpnyCd());

        model.addObject("estmtMgmtEntity", estmtMgmtEntity);
        model.addObject("estmtDtlMgmtEntityList", estmtDtlMgmtEntityList);

        model.addObject("companyMgmtEntity", companyMgmtEntity);

        return model;
    }

    @RequestMapping("/order")
    public ModelAndView orderPrint(@RequestParam String orderNo) {
        ModelAndView model = new ModelAndView("common/printTemplate/orderPrint");


        OrderMgmtEntity orderMgmtEntity = orderMgmtService.getOrder(orderNo);
        List<OrderDtlMgmtEntity> orderDtlMgmtEntityList = orderDtlMgmtService.getOrderDtlListWithOrderNo(orderNo);

        CompanyMgmtEntity companyMgmtEntity = companyMgmtService.getCompanyMgmt(orderMgmtEntity.getCmpnyCd());

        model.addObject("orderMgmtEntity", orderMgmtEntity);
        model.addObject("orderDtlMgmtEntityList", orderDtlMgmtEntityList);

        model.addObject("companyMgmtEntity", companyMgmtEntity);

        return model;
    }

    @RequestMapping("/saleHis")
    public ModelAndView saleHis(@RequestParam String schStrtDate, @RequestParam String schEndDate, @RequestParam String cmpnyCd, @RequestParam String saleSe){
        HashMap<String, Object> params = new HashMap<>();
        params.put("schEndDate",schEndDate);
        params.put("schStrtDate",schStrtDate);
        params.put("cmpnyCd",cmpnyCd);
        params.put("saleSe",saleSe);

        ModelAndView model = new ModelAndView("common/printTemplate/SaleHisPrint");

        HashMap<String, Object> saleSttleMap = settleService.getSaleSttle(params);
        saleSttleMap.put("saleSe", params.get("saleSe"));

        if(saleSttleMap.size() == 1) {
            if(!ObjectUtils.isEmpty(saleSttleMap.get("cmpnyCd"))) {
                model.addObject("saleSttleMap", saleSttleMap);
            }
        } else {
            model.addObject("saleSttleMap", saleSttleMap);
        }

        CompanyMgmtEntity companyMgmtEntity = companyMgmtService.getCompanyMgmt(cmpnyCd);
        model.addObject("companyMgmtEntity", companyMgmtEntity);

        List<SaleMgmtEntity> saleHisList = settleService.getSaleHisPrintList(params);

        model.addObject("saleHisList", saleHisList);

        model.addObject("schStrtDate", (String) params.get("schStrtDate"));
        model.addObject("schEndDate", (String) params.get("schEndDate"));

        return model;
    }

    @RequestMapping("/statement")
    public ModelAndView statement(@RequestParam String saleNo){
        ModelAndView model = new ModelAndView("common/printTemplate/Statement");
        HashMap<String, Object> params = new HashMap<>();

        SaleMgmtEntity saleMgmtEntity = saleMgmtService.getSale(saleNo);
        List<SaleDtlMgmtEntity> saleDtlMgmtEntityList = saleDtlMgmtService.getSaleDtlListWithSaleNo(saleNo);
        CompanyMgmtEntity companyMgmtEntity = companyMgmtService.getCompanyMgmt(saleMgmtEntity.getCmpnyCd());

        model.addObject("saleMgmtEntity", saleMgmtEntity);
        model.addObject("saleDtlMgmtEntityList", saleDtlMgmtEntityList);
        model.addObject("companyMgmtEntity", companyMgmtEntity);

        HashMap<String, Object> param = new HashMap<>();
        param.put("schStrtDate", saleMgmtEntity.getSaleDate());
        param.put("schEndDate", saleMgmtEntity.getSaleDate());
        param.put("cmpnyCd", saleMgmtEntity.getCmpnyCd());
        param.put("saleSe", saleMgmtEntity.getSaleSe());

        HashMap<String, Object> resultMap = settleService.getSaleSttle(param);
        int totalBfBalance = ((BigDecimal) resultMap.get("totalBfBalance")).intValue();
        model.addObject("totalBfBalance", totalBfBalance);


        return model;
    }
*/

}
