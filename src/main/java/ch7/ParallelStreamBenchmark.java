package ch7;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 2, jvmArgs={"-Xms4G", "-Xms4G"})
public class ParallelStreamBenchmark {
    private static final long N = 10_000_000L;

    @Benchmark
    public long sequentialSum(){
        return Stream.iterate(1L , i -> i+1)
                .limit(N)
                .reduce(0L, Long::sum);
    }

    @TearDown
    public void tearDown(){
        System.gc();
    }
}
