package io.dj.modules.job.task;

import io.dj.modules.job.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/14
 * Time: 10:09
 * Email: 294945339@qq.com
 */

@Component("crmTask")
public class CrmTask {

    @Autowired private CrmGoodsTypeService crmGoodsTypeService;
    @Autowired private CrmCompanyService crmCompanyService;
    @Autowired private CrmSpecificationSheetService crmSpecificationSheetService;
    @Autowired private CrmVersionLibraryService crmVersionLibraryService;
    @Autowired private CrmOrderService crmOrderService;
    @Autowired private CrmMeasureDispatchService crmMeasureDispatchService;

    public void setCrmGoodsType(){
        crmGoodsTypeService.updateCrmGoodsType();
    }

    public void setCrmCompany(){
        crmCompanyService.updateCrmCompany();
    }

    public void setCrmSpecificationSheet(){
        crmSpecificationSheetService.updateAll();
    }

    public void setCrmVersionLibrary(){
        crmVersionLibraryService.updateCrmVersionLibrary();
    }

    public void setCrmOrder(){
        crmOrderService.updateCrmOrder();
    }

    public void setcrmMeasureDispatch(){
        crmMeasureDispatchService.updateCrmMeasureDispatch();
    }
}
