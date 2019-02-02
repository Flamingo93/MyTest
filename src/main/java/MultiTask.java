import com.sdg.dpd.apollo.route.engine.beans.SQLRequest;

import java.util.concurrent.*;

/**
 * @author changzhichao
 * @date 2019/02/01
 */
public class MultiTask implements Runnable {
    private String url;
    private SQLRequest sqlRequest;
    private int taskNum;

    public MultiTask(String url, SQLRequest sqlRequest, int taskNum) {
        this.url = url;
        this.sqlRequest = sqlRequest;
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long start = System.nanoTime();
        for (int i = 1; i < taskNum; i++) {
            executorService.submit(new StpeTask(url, sqlRequest));
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("ExecutorService submit all tasks cost " + timeElapsed / 1000000 + " milliseconds");
    }
}




