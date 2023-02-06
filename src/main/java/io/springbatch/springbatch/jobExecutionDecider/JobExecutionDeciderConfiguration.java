package io.springbatch.springbatch.jobExecutionDecider;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class JobExecutionDeciderConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobExecutionDecider() {
        return jobBuilderFactory.get("jobExecutionDeciderjob")
                .incrementer(new RunIdIncrementer())
                .start(jobExecutionDeciderStep1())
                .next(decider())
                .from(decider()).on("ODD").to(jobExecutionDeciderOddStep())
                .from(decider()).on("EVEN").to(jobExecutionDeciderEvenStep())
                .end()
                .build();
    }

    @Bean
    public JobExecutionDecider decider() {
        return new CustomDecider();
    }

    @Bean
    public Step jobExecutionDeciderStep1() {
        return stepBuilderFactory.get("deciderStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("decider step1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step jobExecutionDeciderEvenStep() {
        return stepBuilderFactory.get("evenStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("even step was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step jobExecutionDeciderOddStep() {
        return stepBuilderFactory.get("oddStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("odd step was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
