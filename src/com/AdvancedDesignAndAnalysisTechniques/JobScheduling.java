package com.AdvancedDesignAndAnalysisTechniques;

import org.junit.Test;
import org.testng.Assert;

import java.util.*;

/**
 * Greedy algorithms
 *
 * Created by xisisu on 11/13/16.
 */
public class JobScheduling {
    public List<Job> jobScheduling(final List<Job> jobs) {
        // sort jobs by finish time
        Collections.sort(jobs, new Comparator<>() {
            @Override
            public int compare(Job j1, Job j2) {
                return j1.getEnd() > j2.getEnd() ? 1 :
                        j1.getEnd() < j2.getEnd() ? -1 : 0;
            }
        });

        List<Job> result = new ArrayList<>();
        int startTime = -1;
        for (final Job j : jobs) {
            if (j.getStart() >= startTime) {
                result.add(j);
                startTime = j.getEnd();
            }
        }

        return result;
    }

    private class Job {
        final private Integer _start;
        final private Integer _end;
        public Job(final Integer start, final Integer end) {
            _start = start;
            _end = end;
        }
        public Integer getStart() { return _start; }
        public Integer getEnd() { return _end; }

        @Override
        public String toString() {
            return "(" + _start + "," + _end + ")";
        }
    }

    @Test
    public void test() {
        // example from 16.1
        final List<Integer> start = Arrays.asList(1,3,0,5,3,5,6,8,8,2,12);
        final List<Integer> end   = Arrays.asList(4,5,6,7,8,9,10,11,12,13,14);
        final List<Job> jobs = new ArrayList<>();
        Assert.assertEquals(start.size(), end.size());
        for (int i = 0; i < start.size(); ++i) {
            jobs.add(new Job(start.get(i), end.get(i)));
        }
        Collections.shuffle(jobs);

        System.out.println(jobScheduling(jobs));
    }
}
