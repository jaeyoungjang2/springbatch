package io.springbatch.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class StepBuilderConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepBuilderBatchJob() {
        return this.jobBuilderFactory.get("StepbuilderBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(stepBuilderStep1())
                .next(stepBuilderStep2())
                .next(stepBuilderStep4())
                .next(stepBuilderStep5())
//                .next(step3())
                .build();
    }

    @Bean
    public Step stepBuilderStep1() {
        return stepBuilderFactory.get("Stepbuilder step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("step1 has executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step stepBuilderStep2() {
        return stepBuilderFactory.get("step2")
                .<String, String>chunk(3)
                .reader(() -> null)
                .writer(list -> {})
                .build();
    }
//    multithread 관련 작업, 나중에 알아볼 예정
     @Bean
     public Step stepBuilderStep3() {
         return stepBuilderFactory.get("step3")
                 .partitioner(stepBuilderStep1())
                 .gridSize(2)
                 .build();
     }

    // job이 step 안에 포함되어 있는 형상
    // step이 job을 실행시키는 구조임
    @Bean
    public Step stepBuilderStep4() {
        return stepBuilderFactory.get("step4")
                .job(job())
                .build();
    }

    @Bean
    public Step stepBuilderStep5() {
        return stepBuilderFactory.get("step5")
                .flow(flow())
                .build();
    }
    @Bean
    public Job job() {
        return this.jobBuilderFactory.get("job")
                .start(stepBuilderStep1())
                .next(stepBuilderStep2())
//                .next(step3())
                .build();
    }
    @Bean
    public Flow flow() {
        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
        flowBuilder.start(stepBuilderStep2()).end();
        return flowBuilder.build();
    }
}
