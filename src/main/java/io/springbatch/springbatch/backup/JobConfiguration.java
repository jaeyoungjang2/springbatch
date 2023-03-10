//package io.springbatch.springbatch;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Map;
//
//@Configuration
//@RequiredArgsConstructor
//public class JobConfiguration {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    @Bean
//    public Job job() {
//        return jobBuilderFactory.get("job")
//                .start(step1())
//                .next(step2())
//                .next(step3())
//                .build();
//    }
//
//    @Bean
//    public Step step1() {
//        return stepBuilderFactory.get("step1")
//                .tasklet(new Tasklet() {
//                    @Override
//                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//
//                        // jobparameter를 확인하는 방법 1
//                        JobParameters jobParameters = contribution.getStepExecution().getJobExecution().getJobParameters();
//                        jobParameters.getString("name");
//                        jobParameters.getLong("seq");
//                        jobParameters.getDate("date");
//                        jobParameters.getDouble("age");
//
//                        // jobparameter를 확인하는 방법 2
//                        // 동일한 값을 얻을 수 있지만 값을 확인만 하는 용도로 사용
//                        // 사용 방법이 조금 다르긴 하다.
//                        Map<String, Object> jobParameters1 = chunkContext.getStepContext().getJobParameters();
//
//                        System.out.println("step1 was executed");
//                        return RepeatStatus.FINISHED;
//                    }
//                }).build();
//    }
//
//    private Step step2() {
//        return stepBuilderFactory.get("step2")
//                .tasklet((stepContribution, chunkContext) -> {
//                    // tasklet에서 stepexecution을 참조할 경우에는 stepContribution에서 가져오면 된다.
//                    stepContribution.getStepExecution().getJobExecution().getJobInstance().getJobName();
//                    System.out.println("step2 has executed");
//                    throw new RuntimeException("step2 has failed");
////                    return RepeatStatus.FINISHED;
//                })
//                .build();
//    }
//
//    public Step step3() {
//        return stepBuilderFactory.get("step3")
//                .tasklet(new ChunkTasklet())
//                .build();
//    }
//
//
//}
