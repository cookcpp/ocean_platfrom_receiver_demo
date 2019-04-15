package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.starter.controller.AbstractController;
import io.vertx.starter.controller.DemoController;
import io.vertx.starter.controller.IndexController;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuqinghua
 */
@Slf4j
public class MainVerticle extends AbstractVerticle {

  final int port = 8080;

  private List<AbstractController> controllerList = new ArrayList<>();

  @Override
  public void start(Future<Void> startFuture) throws Exception {
    HttpServerOptions options = new HttpServerOptions()
      .setPort(443)
      .setSsl(true)
      .setKeyCertOptions(
        new JksOptions()
          .setPath(System.getProperty("user.dir") + "/ssl/oceankey.jks")
          .setPassword("tpson123"));

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    controllerList.add(new IndexController(router));
    controllerList.add(new DemoController(router));

    controllerList.forEach(AbstractController::init);

    vertx.createHttpServer(options)
      .requestHandler(router)
      .listen(port, http -> {
        if (http.succeeded()) {
          startFuture.complete();
          log.info("HTTP server started on http://localhost:" + port);
        } else {
          startFuture.fail(http.cause());
        }
      });
  }

}
