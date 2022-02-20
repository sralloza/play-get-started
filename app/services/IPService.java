package services;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSBodyWritables;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

public class IPService implements WSBodyReadables, WSBodyWritables {
  private final WSClient ws;

  @Inject
  public IPService(WSClient ws) {
    this.ws = ws;
  }

  public CompletionStage<String> getIP() {
    WSRequest request = ws.url("https://httpbin.org/ip");
    CompletionStage<String> jsonPromise = request.get().thenApply(r -> r.asJson().path("origin").asText());
    return jsonPromise;
  }

}
