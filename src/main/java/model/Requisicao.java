package model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Requisicao {

    private HttpClient client;

    public Requisicao() {
        client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> response(String uri) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Erro na URI: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("Erro de I/O: " + e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupção inesperada: " + e.getMessage());
        }
    }
}
