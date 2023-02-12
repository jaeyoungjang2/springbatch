package io.springbatch.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

// bean으로 등록하려면 @Component annotation을 달어주면 된다.
public class ChunkTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        // business logic 추가
        System.out.println("step2 was executed");
        // tasklet 반복하지 않고 종료
        return RepeatStatus.FINISHED;
    }
}
