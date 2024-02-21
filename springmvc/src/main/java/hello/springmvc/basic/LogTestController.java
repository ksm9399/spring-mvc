package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j  // private final Logger log = LoggerFactory.getLogger(getClass()); 와 동일
@RestController
public class LogTestController {

  // private final Logger log = LoggerFactory.getLogger(getClass());

  @GetMapping("/log-test")
  public String logTest() {
    String name = "Spring";

    System.out.println("name = " + name);

    // LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
    // {}수 만큼 매개 변수 넘겨주면 됨
    log.trace("reace log = {}", name);
    log.debug("debug log = {}", name);
    log.info("info log = {}", name);
    log.warn("warn log = {}", name);
    log.error("error log = {}", name);

    return "ok";
  }
}
