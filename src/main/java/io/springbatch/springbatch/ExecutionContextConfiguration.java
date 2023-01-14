package io.springbatch.springbatch;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class ExecutionContextConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final JobExecutionListener jobRepositoryListener;

    @Bean
    public Job batchJob1() {
        return jobBuilderFactory.get("batchJob1")
                .start(step1())
                .next(step2())
                .validator(new DefaultJobParametersValidator(new String[]{"name","date"}, new String[]{"count"}))
                .listener(jobRepositoryListener)
                .build();
    }
//    @Bean
//    public Job batchJob2() {
//        return jobBuilderFactory.get("batchJob1")
//                .start(flow())
//                .next(step5())
//                .end()
//                .build();
//    }

//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(executionContextTasklet1)
//                .build();
//    }
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step1 was executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("step2 was executed");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

//    @Bean
//    public Flow flow() {
//        // step과 비슷한 level을 flow라고 봐도 무방할 것 같다.
//        FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("flow");
//        // flow는 end를 구성해야한다.
//        flowBuilder.start(step3())
//                .next(step4())
//                .end();
//
//        return flowBuilder.build();
//    }
//    @Bean
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("step3 was executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step4() {
//        return stepBuilderFactory.get("step4")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("step3 was executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    @Bean
//    public Step step5() {
//        return stepBuilderFactory.get("step3")
//                .tasklet((stepContribution, chunkContext) -> {
//                    System.out.println("step5 was executed");
//                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
}
