package info.dt.qlcv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import info.dt.qlcv.dao.TypeTopicDAO;
import info.dt.qlcv.entity.TypeTopic;

@Controller
@RequestMapping(value = "/topic")
public class TypeTopicController {

    @Autowired
    private TypeTopicDAO typeTopicDAO;


    @RequestMapping(value = "/addTypeTopic", method = RequestMethod.POST)
    public ResponseEntity<?> insertTypeTopic(@RequestParam("idTypeTopic") int idTypeTopic, @RequestParam("nameTypeTopic") String nameTypeTopic){

        TypeTopic typeTopic = new TypeTopic(nameTypeTopic,0);
        typeTopic.setIdTypeTopic(idTypeTopic);
        TypeTopic typeTopicResult = typeTopicDAO.insertTypeTopic(typeTopic);
        if(typeTopicResult != null)
            return new ResponseEntity<>(typeTopicResult, HttpStatus.OK);
        else
            return new ResponseEntity<>(new TypeTopic(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteTypeTopic", method = RequestMethod.POST)
    public ResponseEntity<?> deleteTypeTopic(@RequestParam("idTypeTopic") int idTypeTopic){

        boolean result = typeTopicDAO.deleteTypeTopic(idTypeTopic);
        if(result)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getTypeTopic/{idTypeTopic}", method = RequestMethod.GET)
    public ResponseEntity<?> getTypeTopic(@PathVariable("idTypeTopic") int idTypeTopic){

        TypeTopic result = typeTopicDAO.getTypeTopicById(idTypeTopic);
        if(result!=null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

}
