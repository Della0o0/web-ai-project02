package top.annieholo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.annieholo.pojo.*;
import top.annieholo.service.EmpService;
import top.annieholo.service.ReportService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    /**
     * 获取员工统计-职位
     * @return
     */
    @GetMapping("/empJobData")
    public Result empJobData(){
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    // @GetMapping("/empJobData")
    // public Result empJobData(){
    //     BarChartData barChartData = reportService.getEmpJobDataBar();
    //     log.info("获取员工统计-职位: {}", barChartData);
    //     return Result.success(barChartData);
    // }



    @GetMapping("/empGenderData")
    public Result empGenderData(){
        List<GenderCount> genderCountList = reportService.getEmpGenderData();

        return Result.success(genderCountList);
    }



}
