package io.springbatch.springbatch.flowjob;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FlowJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job flowJob() {
        // step1이
        // 성공 -> 3번 step으로 이동
        // 실패 -> 2번 step으로 이동
        return jobBuilderFactory.get("flowJOb")
                .start(flowJobStep1())
                .on("COMPLETED").to(flowJobStep3())
                .from(flowJobStep1())
                .on("FAILED").to(flowJobStep2())
                .end().
                build();
    }

    private Step flowJobStep1() {
        return stepBuilderFactory.get("flowJobStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println();
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    private Step flowJobStep2() {
        return stepBuilderFactory.get("flowJobStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println();
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
    private Step flowJobStep3() {
        return stepBuilderFactory.get("flowJobStep3")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        System.out.println();
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
