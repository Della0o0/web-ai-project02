package top.annieholo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.annieholo.pojo.JobOption;
import top.annieholo.pojo.Result;
import top.annieholo.service.EmpService;
import top.annieholo.service.ReportService;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/empJobData")
    public Result empJobData(){
        JobOption jobOption = reportService.getEmpJobData();

        return Result.success(jobOption);
    }
}
