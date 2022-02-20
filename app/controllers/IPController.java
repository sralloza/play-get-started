package controllers;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import models.IPAddress;
import play.mvc.Controller;
import play.mvc.Result;
import services.IPService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class IPController extends Controller {
    private final IPService client;

    @Inject
    public IPController(IPService client) {
      this.client = client;
    }

    public CompletionStage<Result> getIP() {
        return client.getIP().thenApply(x -> ok(new IPAddress(x).toString()));
    }

}
