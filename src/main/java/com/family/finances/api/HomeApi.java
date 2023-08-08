package com.family.finances.api;


import com.family.finances.core.Response;
import com.family.finances.core.ResponseGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeApi {

    @RequestMapping("/")
    public String test()
    {
        return "hello word";
    }

    @RequestMapping("/success")
    public Response<Object> success()
    {
        return ResponseGenerator.success();
    }

    @RequestMapping("/success-data")
    public Response<String> successData()
    {
        return ResponseGenerator.success("list");
    }

    @RequestMapping("/fail")
    public Response<String> fail()
    {
        return ResponseGenerator.fail("error");
    }

}
