package io.springbatch.springbatch.flowJobArchitecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FlowJobArcitectureConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job  architecutreJob() {
        return jobBuilderFactory.get("architectureJob1")
                .start(architectureFlow())
                .next(architectureStep3())
                .end()
                .build();
    }

    @Bean
    public Flow architectureFlow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flowJobArchitecture");


        return flowBuilder.start(architectureStep1())
                .next(architectureStep2())
                // simpleFlow 객체 생성
                .build();
    }

    private Step architectureStep1() {
        return stepBuilderFactory.get("architecutreStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("architectureStep1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    private Step architectureStep2() {
        return stepBuilderFactory.get("architecutreStep2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("architectureStep2 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    private Step architectureStep3() {
        return stepBuilderFactory.get("architecutreStep3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("architectureStep3 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }



}
