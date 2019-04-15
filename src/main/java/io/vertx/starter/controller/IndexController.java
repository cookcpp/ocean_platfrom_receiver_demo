package io.vertx.starter.controller;

import io.vertx.ext.web.Router;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wuqinghua
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class IndexController extends AbstractController {

    private final Router router;

    @Override
    public void init() {
        router.get("/").handler(context -> {
            context.response()
                    .putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x!");
        });
    }

}
