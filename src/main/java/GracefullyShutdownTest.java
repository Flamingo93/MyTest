import java.util.concurrent.*;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sdg.dpd.apollo.route.engine.beans.SQLRequest;
import com.sdg.dpd.apollo.route.engine.enums.SQLMode;

/**
 * @author changzhichao
 * @date 2019/02/01
 */
public class GracefullyShutdownTest {
    public static void main(String[] args) throws Exception {
        String url = "http://10.174.20.19:48230/req/v1/interactive/tenants/20/sql";
        SQLRequest sqlRequest = new SQLRequest("select count(*) from phoenix_dev.gp_phoenix_desc0002", SQLMode.AUTO);

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("route-engine-test").build();
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(10, namedThreadFactory);

        StpeTask stpeTask = new StpeTask(url, sqlRequest);
        stpe.execute(stpeTask);
        stpe.scheduleAtFixedRate(stpeTask, 0, 100, TimeUnit.MILLISECONDS);

//        MultiTask multiTask = new MultiTask(url, sqlRequest, 10);
//        stpe.scheduleAtFixedRate(multiTask, 0, 1, TimeUnit.SECONDS);

        stpe.shutdown();
    }
}
