import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.sdg.dpd.apollo.route.engine.beans.SQLRequest;
import com.sdg.dpd.apollo.route.engine.enums.SQLMode;

import java.util.concurrent.*;

/**
 * @author changzhichao
 * @date 2019/02/01
 */
public class FeignHttpClientTest {
    public static void main(String[] args) throws Exception {

        String url = "http://10.174.20.19:45468/req/v1/interactive/tenants/20/sql";
        SQLRequest sqlRequest = new SQLRequest("select count(*) from phoenix_dev.gp_phoenix_desc0002", SQLMode.AUTO);

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("route-engine-test").build();
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(10, namedThreadFactory);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new StpeTask(url, sqlRequest));
        executorService.submit(new StpeTask(url, sqlRequest));
        executorService.shutdown();
    }
}
