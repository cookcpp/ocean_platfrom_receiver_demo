package io.vertx.starter;

import io.vertx.core.Vertx;

/**
 * @author wuqinghua
 */


public class ApplicationBootstrap {

  private static final Vertx vertx = Vertx.vertx();


  public static void main(String[] args) {
    vertx.deployVerticle(MainVerticle.class.getName());
  }


}
