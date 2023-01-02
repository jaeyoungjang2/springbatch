package io.springbatch.springbatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JobRepositoryListener implements JobExecutionListener {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        // job이 모두 수행이 된 뒤, jobExecution을 사용해서
        String jobName = jobExecution.getJobInstance().getJobName();
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user1")
                .addLong("seq", 2L)
                .addDouble("age", 16.5)
                .toJobParameters();

        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);
        if (lastJobExecution != null) {
            Collection<StepExecution> stepExecutions = lastJobExecution.getStepExecutions();
            for (StepExecution stepExecution : stepExecutions) {
                BatchStatus status = stepExecution.getStatus();
                System.out.println("status = " + status);
                ExitStatus exitStatus = stepExecution.getExitStatus();
                System.out.println("exitStatus = " + exitStatus);
                String stepName = stepExecution.getStepName();
                System.out.println("stepName = " + stepName);
            }

        }
    }
}
