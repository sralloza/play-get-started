package controllers;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import clients.HTTPClient;
import models.IPAddress;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class IpController extends Controller {
    private final HTTPClient client;

    @Inject
    public IpController(HTTPClient client) {
      this.client = client;
    }

    public CompletionStage<Result> getIP() {
        return client.getIP().thenApply(x -> ok(new IPAddress(x).toString()));
    }

}
