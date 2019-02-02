import com.sdg.dpd.apollo.route.engine.beans.SQLRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

/**
 * @author changzhichao
 * @date 2019/02/01
 */
public class StpeTask implements Runnable {

    private String url;
    private SQLRequest sqlRequest;

    public StpeTask(String url, SQLRequest sqlRequest) {
        this.url = url;
        this.sqlRequest = sqlRequest;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.postForEntity(url, sqlRequest, String.class);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println(result);
        System.out.println("Execution costs " + timeElapsed / 1000000 + " milliseconds");
    }
}
