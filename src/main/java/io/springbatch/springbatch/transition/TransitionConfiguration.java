package io.springbatch.springbatch.transition;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TransitionConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job transitionJob() {
        return jobBuilderFactory.get("transitionJob")
                .start(transitionStep1())
                    .on("FAILED")
                    .to(transitionStep2())
                    .on("FAILED")
                    .stop()
                .from(transitionStep1())
                    .on("*")
                    .to(transitionStep3())
                    .next(transitionStep4())
                .from(transitionStep2())
                    .on("*")
                    .to(transitionStep5())
                    // end 하는 순간 SimpleFlow 객체가 생성이 됨.
                    .end()
                .build();
    }

    @Bean
    public Step transitionStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("transtition step1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step transitionStep2() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("transtition step1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step transitionStep3() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("transtition step1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step transitionStep4() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("transtition step1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step transitionStep5() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("transtition step1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
