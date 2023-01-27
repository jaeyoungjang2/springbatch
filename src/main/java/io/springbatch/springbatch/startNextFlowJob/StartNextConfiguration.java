package io.springbatch.springbatch.startNextFlowJob;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
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

@Configuration
@RequiredArgsConstructor
public class StartNextConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job startNextJob() {
        return jobBuilderFactory.get("startNextJob")
                .start(startNextFlow1())
                .next(startnextStep3())
                .next(startNextFlow2())
                .next(startnextStep6())
                .end()
                .build();
    }

    @Bean
    public Flow startNextFlow1() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("startNextFlow1");
        flowBuilder.start(startnextStep1())
                .next(startnextStep2())
                .end();

        return flowBuilder.build();
    }
    @Bean
    public Flow startNextFlow2() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("startNextFlow2");
        flowBuilder.start(startnextStep4())
                .next(startnextStep5())
                .end();

        return flowBuilder.build();
    }

    @Bean
    public Step startnextStep1() {
        return stepBuilderFactory.get("startNextStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep1 was executed");
                        // step의 ExitStatus를 강제적으로 failed로 변경
                        stepContribution.setExitStatus(ExitStatus.FAILED);
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    @Bean
    public Step startnextStep2() {
        return stepBuilderFactory.get("startNextStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep2 was executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    @Bean
    public Step startnextStep3() {
        return stepBuilderFactory.get("startNextStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep3 was executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    @Bean
    public Step startnextStep4() {
        return stepBuilderFactory.get("startNextStep4")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep4 was executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    @Bean
    public Step startnextStep5() {
        return stepBuilderFactory.get("startNextStep5")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep5 was executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    @Bean
    public Step startnextStep6() {
        return stepBuilderFactory.get("startNextStep6")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("startNextStep6 was executed");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
