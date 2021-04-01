package nacos.controller;

import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@ResponseJSONP
@RequestMapping("nacos")
public class DiscoveryController {

    @NacosInjected
    private NamingService namingService;



    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping("/helloNacos")
    public String helloNacos(@PathVariable String str){
        return new Date() + "Hello Nacos " + str;
    }

    @RequestMapping("/instance")
    @ResponseBody
    public List<Instance> getInstance(@PathVariable  String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }
//
    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }

    @RequestMapping("/name")
    public String getName(){
        return "hello" ;
    }

}
