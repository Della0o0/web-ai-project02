package top.annieholo.service;

import top.annieholo.pojo.BarChartData;
import top.annieholo.pojo.GenderCount;
import top.annieholo.pojo.JobOption;

import java.util.List;

public interface ReportService {
    JobOption getEmpJobData();
    BarChartData getEmpJobDataBar();

    List<GenderCount> getEmpGenderData();
}
