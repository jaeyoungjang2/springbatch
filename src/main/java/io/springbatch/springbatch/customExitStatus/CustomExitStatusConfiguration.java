package io.springbatch.springbatch.customExitStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class CustomExitStatusConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job customExitStatusJob() {
        return jobBuilderFactory.get("customExitStatusJob")
                .start(customExitStatusStep1())
                    .on("FAILED")
                    .to(customExitStatusStep2())
                    .on("PASS")
                    .stop()
                .end()
                .build();
    }

    @Bean
    public Step customExitStatusStep1() {
        return stepBuilderFactory.get("customExitStatusStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("custom exit status step1 was executed");
                    stepContribution.getStepExecution().setExitStatus(ExitStatus.FAILED);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step customExitStatusStep2() {
        return stepBuilderFactory.get("customExitStatusStep2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("custom exit status step2 was executed");
                    return RepeatStatus.FINISHED;
                })
                .listener(new PasscheckingListener())
                .build();
    }
}
