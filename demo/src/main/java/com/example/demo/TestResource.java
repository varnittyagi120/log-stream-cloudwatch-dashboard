package com.example.demo;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class TestResource {

    @GetMapping(value="/testendpoint/{flag}")
    public String testFunc(@PathVariable String flag) throws Exception {
        try {
            if(flag.equalsIgnoreCase("true")){
                log.info("test api success fully executed");
                return "Success";
            } else {
                throw  new Exception();
            }
        } catch (Exception e) {
            log.info("got exception while executing test api");
            throw  e;
        }
    }
}
