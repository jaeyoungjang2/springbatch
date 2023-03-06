package io.springbatch.springbatch.simpleFlow2;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SimpleFlowConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleFlow2Job() {
        return jobBuilderFactory.get("simpleFlow2Job")
                .start(simpleFlow2Step1())
                .on("COMPLETED").to(simpleFlow2Step2())
                .from(simpleFlow2Step1())
                .on("FAILED").to(simpleFlow2Flow())
                .end()
                .build();
    }

    @Bean
    public Step simpleFlow2Step1() {
        return stepBuilderFactory.get("simpleFlow2Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step simpleFlow2Step2() {
        return stepBuilderFactory.get("simpleFlow2Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step simpleFlow2Step3() {
        return stepBuilderFactory.get("simpleFlow2Step1")
                .tasklet((stepContribution, chunkContext) -> {
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Flow simpleFlow2Flow() {
        FlowBuilder<Flow> builder = new FlowBuilder<>("simpleFlow2Flow");

        builder.start(simpleFlow2Step2())
                .on("*").to(simpleFlow2Step3())
                .end();

        return builder.build();
    }
}
