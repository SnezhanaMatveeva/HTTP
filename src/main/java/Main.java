import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.List;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = CloseableHttpClientBuilder.build();//создаем httpClient
        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);//создаем GET запрос
        CloseableHttpResponse response = httpClient.execute(request);//httpClient выполняет запрос и записывает ответ
        List<Cat> cats = mapper.readValue(response.getEntity().getContent(), new TypeReference<List<Cat>>() {
        });//преобразуем ответ сервера в список котов
        cats.stream()
                .filter(cat -> cat.getUpvotes() != null)//фильтр котов с оценокй
                .forEach(System.out::println);//вывод котов с оценкой на экран
    }
}
