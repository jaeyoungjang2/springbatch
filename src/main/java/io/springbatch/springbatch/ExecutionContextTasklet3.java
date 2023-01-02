package io.springbatch.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet3 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step3 was executed");

        // step이 총 4개가 있는데 step3 에서 예외가 발생하면, step4는 예외 때문에 그 다음 단계로 안 넘어가는 것을 확인할 수 있을 것이다.
        // job을 재 실행 했을 경우, 우리가 이미 실패한 그 지점에 data를 다시 가지고 올 수 있는지,
        // 가지고 와서 재시작 했을 경우에는 성공적으로 3번째 단계가 수행될 경우 실패했던 데이터를 다시 활용할 수 있는지 확인해볼 것이다.
        Object name = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("name");

        if (name == null) {
            chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("name", "user1");
            // 강제로 예외 발생
            throw new RuntimeException("step3 was failed");
        }

        return RepeatStatus.FINISHED;
    }
}
