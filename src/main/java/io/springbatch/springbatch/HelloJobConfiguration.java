package io.springbatch.springbatch;

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
public class HelloJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep1())
                .next(helloStep2())
                .build();
    }

    @Bean
    public Step helloStep2() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        // return null <- tasklet의 기능이 한번만 실행되도록 한다.
                        // return null;

                        System.out.println(" ====================== ");
                        System.out.println(" >> Hello Spring Batch!!");
                        System.out.println(" ====================== ");

                        // return null과 동일하게 tasklet의 기능이 한번만 실행되도록 하는 것이다.
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step helloStep1() {
        return stepBuilderFactory.get("helloStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        // return null <- tasklet의 기능이 한번만 실행되도록 한다.
                        // return null;

                        System.out.println(" ====================== ");
                        System.out.println(" >> step2 was excuted!!");
                        System.out.println(" ====================== ");

                        // return null과 동일하게 tasklet의 기능이 한번만 실행되도록 하는 것이다.
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
