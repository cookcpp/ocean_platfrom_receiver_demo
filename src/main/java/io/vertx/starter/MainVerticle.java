package io.vertx.starter;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.JksOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wuqinghua
 */
@Slf4j
public class MainVerticle extends AbstractVerticle {

  final int port = 8080;

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
    router.get("/").handler(context -> {
      context.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    });
    router.post("/demo").handler(context -> {
      log.info("## Client request:{}", context.getBodyAsJson());
      context.response()
        .putHeader("content-type", "application/json")
        .end(new JsonObject().put("hello", "world").toBuffer());
    });


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
