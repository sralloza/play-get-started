package controllers;

import java.util.List;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.FactsService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class FactsController extends Controller {
  private final FactsService facts;

  @Inject
  public FactsController(FactsService facts) {
    this.facts = facts;
  }

  public CompletionStage<Result> getFact() {
    return facts.getFact().thenApply(x -> ok(Json.toJson(x)));
  }

  public Result getFacts(Integer n) {
    List<String> factList = facts.getFacts(n);
    return ok(Json.toJson(factList));
  }

}
