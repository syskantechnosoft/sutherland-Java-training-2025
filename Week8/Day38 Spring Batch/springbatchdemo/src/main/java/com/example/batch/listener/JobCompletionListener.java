package com.example.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

//import lombok.extern.slf4j.Slf4j;

@Component
//@Slf4j
public class JobCompletionListener implements JobExecutionListener {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("✅ Job '{}' starting…", jobExecution.getJobInstance().getJobName());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		int totalRead = 0;
		int totalWrite = 0;
		int totalReadSkips = 0;
		int totalProcessSkips = 0;
		int totalWriteSkips = 0;

		for (StepExecution se : jobExecution.getStepExecutions()) {
			totalRead += se.getReadCount();
			totalWrite += se.getWriteCount();
			totalReadSkips += se.getReadSkipCount();
			totalProcessSkips += se.getProcessSkipCount();
			totalWriteSkips += se.getWriteSkipCount();
			log.info("Step '{}' -> read: {}, written: {}, skips: r={}, p={}, w={}", se.getStepName(), se.getReadCount(),
					se.getWriteCount(), se.getReadSkipCount(), se.getProcessSkipCount(), se.getWriteSkipCount());
		}

		int totalSkips = totalReadSkips + totalProcessSkips + totalWriteSkips;

		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("🎉 Job '{}' COMPLETED. Read: {}, Written: {}, Skipped: {} (r={}, p={}, w={})",
					jobExecution.getJobInstance().getJobName(), totalRead, totalWrite, totalSkips, totalReadSkips,
					totalProcessSkips, totalWriteSkips);
		} else {
			log.warn("⚠️ Job '{}' finished with status: {}", jobExecution.getJobInstance().getJobName(),
					jobExecution.getStatus());
			if (!jobExecution.getAllFailureExceptions().isEmpty()) {
				jobExecution.getAllFailureExceptions().forEach(ex -> log.error("Failure exception:", ex));
			}
		}
	}
}
