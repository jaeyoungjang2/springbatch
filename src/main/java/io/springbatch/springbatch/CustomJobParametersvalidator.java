package io.springbatch.springbatch;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class CustomJobParametersvalidator implements JobParametersValidator {

    
    @Override
    public void validate(JobParameters jobParameters) throws JobParametersInvalidException {
        // jobparameters 에는 job을 수행할 때 job에 전달한 key와 value 값들이 저장되어 있음.
        // parameter에 key값인 name에 해당하는 value가 존재하지 않는 경우 문제 발생
        if (jobParameters.getString("name") == null) {
            throw new JobParametersInvalidException("name parameters is not found");
        }
    }
}
