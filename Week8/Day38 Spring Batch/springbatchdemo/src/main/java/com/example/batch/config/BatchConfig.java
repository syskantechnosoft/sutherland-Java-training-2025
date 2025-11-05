package com.example.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.batch.listener.JobCompletionListener;
import com.example.batch.model.User;
import com.example.batch.processor.UserProcessor;
import com.example.batch.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfig {

	private final UserRepository repository;

	@Bean
	public FlatFileItemReader<User> reader() {
		FlatFileItemReader<User> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("users.csv"));
		reader.setLinesToSkip(1); // skip header

		DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter("\t"); // TAB delimiter
		tokenizer.setNames("id", "name", "email");

		BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(User.class);

		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);

		reader.setLineMapper(lineMapper);
		return reader;
	}

	@Bean
	public UserProcessor processor() {
		return new UserProcessor();
	}

	@Bean
	public RepositoryItemWriter<User> writer() {
		RepositoryItemWriter<User> writer = new RepositoryItemWriter<>();
		writer.setRepository(repository);
		writer.setMethodName("save");
		return writer;
	}

	@Bean
	public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("import-step", jobRepository).<User, User>chunk(50, transactionManager).reader(reader())
				.processor(processor()).writer(writer()).faultTolerant().skip(RuntimeException.class).skipLimit(10)
				.retry(RuntimeException.class).retryLimit(2).build();
	}

	@Bean
	public Job job(JobRepository jobRepository, Step step, JobCompletionListener listener) {
		return new JobBuilder("userImportJob", jobRepository).incrementer(new RunIdIncrementer()).listener(listener)
				.start(step).build();
	}
}
