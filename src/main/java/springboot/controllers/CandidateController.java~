package springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/candidate")
public class CandidateController {

//    @Autowired
//    private CandidateRepository candidateRepository;
//
//    @Autowired
//    private CandidateService candidateService;

    @GetMapping
    public String candidate(Model model) {
        return "candidates/index";
    }

//    @GetMapping("/list")
//    public String showCandidateList(Model model) {
//        model.addAttribute("candidates", candidateRepository.findAll());
//        return "candidates/list-candidates";
//    }




}
