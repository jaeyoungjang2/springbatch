package io.springbatch.springbatch.simpleFlow;

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
public class SimpleFlowConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    // flowjob 구성
    @Bean
    public Job simpleFlowJob() {
        // end를 통해 simpleFlow 객체를 생성
        return jobBuilderFactory.get("simpleFlowJob")
                .start(simpleFlow1())
                .next(simpleFlowStep3())
                .end()
                .build();
    }

    @Bean
    public Flow simpleFlow1() {
        // end를 통해 simpleFlow 객체를 생성
        // flowjob안에 simpleFlow 객체가 있고, simpleFlow 객체안에 또 다른 simpleFlow 객체가 있다.
        FlowBuilder<Flow> builder = new FlowBuilder<>("simpleFlow1");
        builder.start(simpleFlowStep1())
                .next(simpleFlowStep2())
                .end();

        return builder.build();
    }

    @Bean
    public Step simpleFlowStep1() {
        return stepBuilderFactory.get("simpleFlowStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("simpleFlowStep1 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step simpleFlowStep2() {
        return stepBuilderFactory.get("simpleFlowStep2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("simpleFlowStep2 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
    @Bean
    public Step simpleFlowStep3() {
        return stepBuilderFactory.get("simpleFlowStep3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("simpleFlowStep3 was executed");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
