/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.job.lite.integrate.fixture.dataflow;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.exception.JobSystemException;
import com.dangdang.ddframe.job.api.type.dataflow.api.DataflowJob;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

public class StreamingDataflowElasticJobForExecuteThrowsException implements DataflowJob<String> {
    
    @Getter
    private static volatile boolean completed;
    
    @Override
    public List<String> fetchData(final ShardingContext shardingContext) {
        if (completed) {
            return null;
        }
        return Collections.singletonList("data");
    }
    
    @Override
    public void processData(final ShardingContext shardingContext, final List<String> data) {
        completed = true;
        throw new JobSystemException("I want an error.");
    }
    
    public static void reset() {
        completed = false;
    }
}
