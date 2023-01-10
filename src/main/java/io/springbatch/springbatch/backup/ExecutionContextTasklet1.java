//package io.springbatch.springbatch;
//
//import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.scope.context.ChunkContext;
//import org.springframework.batch.core.step.tasklet.Tasklet;
//import org.springframework.batch.item.ExecutionContext;
//import org.springframework.batch.repeat.RepeatStatus;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ExecutionContextTasklet1 implements Tasklet {
//
//    @Override
//    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//        // step contribution 안에 있는 execution context를 이용해 보도록 하겠다.
//        System.out.println("step1 was executed");
//
//        ExecutionContext jobExecutionContext = stepContribution.getStepExecution().getJobExecution().getExecutionContext();
//        ExecutionContext stepExecutionContext = stepContribution.getStepExecution().getExecutionContext();
//
//        String jobName = chunkContext.getStepContext().getStepExecution().getJobExecution().getJobInstance().getJobName();
//        String stepName = chunkContext.getStepContext().getStepExecution().getStepName();
//
//        // jobName을 key로 값을 저장한 적이 없는 경우
//        if (jobExecutionContext.get("jobName") == null) {
//            jobExecutionContext.put("jobName", jobName);
//        }
//
//        if (stepExecutionContext.get("stepName") == null) {
//            stepExecutionContext.put("stepName", stepName);
//        }
//
//        System.out.println("jobName: " + jobExecutionContext.get("jobName"));
//        System.out.println("stepName: " + jobExecutionContext.get("stepName"));
//
//        return RepeatStatus.FINISHED;
//    }
//
//
//}
