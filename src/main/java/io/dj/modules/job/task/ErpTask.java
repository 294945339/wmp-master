package io.dj.modules.job.task;

import io.dj.modules.job.service.ErpGoodsCatalogService;
import io.dj.modules.job.service.ErpSizeSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * email 294945339@qq.com
 * Date: 2017/8/11
 * Description:
 **/
@Component("erpTask")
public class ErpTask {

    @Autowired
    private ErpGoodsCatalogService erpGoodsCatalogService;
    @Autowired
    private ErpSizeSheetService erpSizeSheetService;

    public void setErpGoodsCatalog(){
        erpGoodsCatalogService.updateErpGoodsCatalog();
    }
    public void setErpSizeSheet(){
        erpSizeSheetService.updateErpSizeSheet();
    }

}