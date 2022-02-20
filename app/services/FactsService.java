package services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import play.libs.ws.WSBodyReadables;
import play.libs.ws.WSBodyWritables;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;

public class FactsService implements WSBodyReadables, WSBodyWritables {
        private final WSClient ws;

        @Inject
        public FactsService(WSClient ws) {
          this.ws = ws;
        }

        public CompletionStage<String> getFact() {
          WSRequest request = ws.url("https://catfact.ninja/fact");
          CompletionStage<String> cs = request.get().thenApply(r -> r.asJson().path("fact").asText());
          return cs;
        }

        public List<String> getFacts(Integer n) {
            List<String> factList = new ArrayList<>();
            List<CompletableFuture<String>> promises = new ArrayList<>();
            for (int i=0;i<n;i++){
                CompletableFuture<String> fact = getFact().toCompletableFuture();
                promises.add(fact);
                fact.thenAcceptAsync(s -> factList.add(s));
            }
            for (CompletableFuture<String> promise : promises){
                promise.join();
            }
            return factList;
        }
}
