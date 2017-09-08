package io.dj.modules.job.task;

import io.dj.modules.job.service.DapOfficeTaskService;
import io.dj.modules.job.service.DapUserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: dj
 * Date: 2017/8/4
 **/
@Component("dapTask")
public class DapTask {

    @Autowired private DapOfficeTaskService dapOfficeTaskService;
    @Autowired private DapUserTaskService dapUserTaskService;

    public void setDapOffice(){
        dapOfficeTaskService.updateDapOffice();
    }

    public void setDapUser(){
        dapUserTaskService.updateDapUser();
    }
}
