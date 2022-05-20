package com.mapsaProject.onlineShop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleLogerController {
  private static final Logger logger = LoggerFactory.getLogger(SampleLogerController.class);

  @RequestMapping("/")
  @ResponseBody
  public String testMethod() {
    logger.trace("This is a trace log example");
    logger.info("This is an info log example");
    logger.debug("This is a debug log example");
    logger.error("This is an error log example");
    logger.warn("This is a warn log example");
    return "Check the Console Logs";
  }
}