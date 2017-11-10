package sample;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
//@SpringBootApplication
public class SampleController {

	@Autowired
	Producer producer;
	//@Autowired
	//Consumer consumer;
	
	public static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	
	@Value("${greeter.message}")
    private String greeterMessageFormat;
	
    @RequestMapping("/sample")
    public Map<String,String> sample(@RequestParam(value="name", defaultValue="World") String name) {
    	
    String prefix = System.getenv().getOrDefault("GREETING_PREFIX", "Hi");
   
    producer.produceMsg(prefix);
    
    Consumer consumer;
    logger.info("Prefix :{} ", prefix);
    if (prefix == null) {
             prefix = "Hello!";
    }
    Map<String,String> result = new HashMap<>();
    //result.put("message", String.format("%s, %s", prefix,name));
    result.put("message", String.format(greeterMessageFormat, prefix,name));
    return result;
    }
    
    
 // -------------------Handle POST request-------------------------------------------

    
 	@RequestMapping(value = "/samplePostKube", method = RequestMethod.POST)
 	//public ResponseEntity<?> createUser(@RequestBody IoTInput iotdata, UriComponentsBuilder ucBuilder) {
 	public Map<String,String> createSamplePostKubernetes(@RequestBody IoTInput iotdata) {
 		logger.info("Received IoT Data : {}", iotdata);
 		logger.info("Temparature :"+iotdata.getTemp());
 		logger.info("Humidity :"+iotdata.getHumid());
 		logger.info("Longitue :"+iotdata.getLongitude());
 		logger.info("Latitude :"+iotdata.getLatitude());
 		
 		Map<String, String> DBparams = new HashMap<String, String>();
 		DBparams.put("transportNo", "WB06M5694");
 		DBparams.put("temp", iotdata.getTemp());
 		DBparams.put("humid",iotdata.getHumid() );
 		DBparams.put("longitude", iotdata.getLongitude());
 		DBparams.put("latitude", iotdata.getLatitude());
 		
 		final String postUrl = "http://168.1.141.9:32033/create";
 		RestTemplate restTemplate = new RestTemplate();
 		ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, DBparams, String.class);
 	    System.out.println("Response for Post Request: " + postResponse.getBody());
 		
 		/*if (userService.isUserExist(user)) {
 			logger.error("Unable to create. A User with name {} already exist", user.getName());
 			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
 			user.getName() + " already exist."),HttpStatus.CONFLICT);
 		}
 		userService.saveUser(user);
*/
 		/*HttpHeaders headers = new HttpHeaders();
 		headers.setLocation(ucBuilder.path("/api/sample/{temp}").buildAndExpand(iotdata.getTemp()).toUri());
 		return new ResponseEntity<String>(headers, HttpStatus.CREATED);*/
 		
 		Map<String,String> result = new HashMap<>();
 	    result.put("message", postResponse.getBody());
 	    return result;
 	}
    
 	
 	@RequestMapping(value = "/samplePostPrem", method = RequestMethod.POST)
 	//public ResponseEntity<?> createUser(@RequestBody IoTInput iotdata, UriComponentsBuilder ucBuilder) {
 	public Map<String,String> createSamplePostOnPrem(@RequestBody IoTInput iotdata) {
 		logger.info("Received IoT Data : {}", iotdata);
 		logger.info("Temparature :"+iotdata.getTemp());
 		logger.info("Humidity :"+iotdata.getHumid());
 		logger.info("Longitue :"+iotdata.getLongitude());
 		logger.info("Latitude :"+iotdata.getLatitude());
 				
 		Map<String,String> result = new HashMap<>();
 	    result.put("message", "Request Processed ");
 	    return result;
 	}
 	
 	
   /* static public void main(String[] args) throws Exception
    {
        SpringApplication.run(SampleController.class, args);
    }*/
}