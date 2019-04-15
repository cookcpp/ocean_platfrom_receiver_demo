package io.vertx.starter.controller;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wuqinghua
 * @since 0.0.1
 */
@Slf4j
@AllArgsConstructor
public class DemoController extends AbstractController {

    private final Router router;

    @Override
    public void init() {
        router.post("/demo").handler(context -> {
            log.info("## Client request:{}", context.getBodyAsJson());
            context.response()
                    .putHeader("content-type", "application/json")
                    .end(new JsonObject().put("hello", "world").toBuffer());
        });



    }

}
