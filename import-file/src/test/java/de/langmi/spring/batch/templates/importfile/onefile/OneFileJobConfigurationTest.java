/**
 * Copyright 2011 Michael R. Lange <michael.r.lange@langmi.de>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.langmi.spring.batch.templates.importfile.onefile;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JobConfigurationTest.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de> 
 */
@ContextConfiguration(locations = {
    "classpath*:spring/batch/job/one-file-job.xml",
    "classpath*:spring/batch/setup/**/*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class OneFileJobConfigurationTest {

    /** JobLauncherTestUtils Bean. */
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private DataSource dataSource;
    /** CREATE statement for BUSINESS_OBJECTS table. */
    private static final String CREATE_TABLE_SQL = "CREATE TABLE BUSINESS_OBJECTS (ID INTEGER IDENTITY, ATTRIBUTE VARCHAR (100))";
    private static final String COUNT_SQL = "SELECT COUNT(*) FROM BUSINESS_OBJECTS";
    private static final int EXPECTED_COUNT = 20;

    /** Launch Test. */
    @Test
    public void launchJob() throws Exception {
        // provide jdbc template for setup and later assertions
        SimpleJdbcTemplate sjt = new SimpleJdbcTemplate(dataSource);
        // setup business data table
        sjt.getJdbcOperations().execute(CREATE_TABLE_SQL);
        // Job parameters, commit.rate is set with properties file and Spring placeholder 
        Map<String, JobParameter> jobParametersMap = new HashMap<String, JobParameter>();
        jobParametersMap.put("time", new JobParameter(System.currentTimeMillis()));
        jobParametersMap.put("input.file", new JobParameter("file:src/test/resources/input/onefile/input.txt"));

        // launch the job
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParameters(jobParametersMap));

        // assert job run status
        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

        // assert read/written items
        for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
            assertEquals(EXPECTED_COUNT, stepExecution.getReadCount());
            assertEquals(EXPECTED_COUNT, stepExecution.getWriteCount());
        }

        // assert items are written successfully to database
        assertEquals(EXPECTED_COUNT, sjt.getJdbcOperations().queryForInt(COUNT_SQL));
    }
}
