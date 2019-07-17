package line.tip.webserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AflLineController {
    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello world";
    }
}
