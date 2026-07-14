package top.annieholo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.annieholo.mapper.EmpMapper;
import top.annieholo.pojo.JobCount;
import top.annieholo.pojo.JobOption;
import top.annieholo.service.ReportService;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {


    @Autowired
    EmpMapper empMapper;

    /**
     * 统计员工数量-职位
     *
     * @return
     */
    @Override
    public JobOption getEmpJobData() {
        List<JobCount> list = empMapper.countEmpJobData();
        // log.info("统计员工数量-职位：{}", list);
        List<String> jobList = list.stream().map(JobCount::getJobName).toList();
        List<Integer> dataList = list.stream().map(JobCount::getEmpCount).toList();
        log.info("统计员工数量-职位jobList：{}", jobList);
        log.info("统计员工数量-职位dataList：{}", dataList);
        return new JobOption(jobList, dataList);
    }
}
