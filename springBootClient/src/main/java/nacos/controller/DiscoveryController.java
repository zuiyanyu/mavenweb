package nacos.controller;

//@Controller
//@ResponseJSONP
//@RequestMapping("nacos")
public class DiscoveryController {

   /* @NacosInjected
    private NamingService namingService;


    private final RestTemplate restTemplate;

    @Autowired
    public DiscoveryController(RestTemplate restTemplate) {
        System.out.println("init restTemplate =" +restTemplate);
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/consumer_echo/{str}", method = RequestMethod.GET)
    public String  consumer_echo(@PathVariable String str) {
        System.out.println("restTemplate =" +restTemplate);
        return restTemplate.getForObject("http://service-provider/provider_echo/" + str, String.class);
    }

    @RequestMapping(value = "/provider_echo/{string}", method = RequestMethod.GET)
    public String provider_echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping("/helloNacos")
    public String helloNacos(@PathVariable String str){
        return new Date() + "Hello Nacos " + str;
    }

    @RequestMapping(value = "/setService", method = GET)
    @ResponseBody
    public String set(@RequestParam String serviceName) {
        try {
            System.out.println("namingService = "+namingService);
            System.out.println("开始注册服务:"+serviceName);
            namingService.registerInstance(serviceName, "192.168.2.102", 8848); // 注册中心的地址
            return "OK";
        } catch (NacosException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
    @RequestMapping(value="/instance",method = GET)
    @ResponseBody  // @PathVariable
    public List<Instance> getInstance(@RequestParam("name") String serviceName ) throws NacosException {
        System.out.println("namingService = " +namingService);
        System.out.println("获取服务！"+ serviceName);
        return namingService.getAllInstances("nacos-provider-movie");
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
    }*/

}
