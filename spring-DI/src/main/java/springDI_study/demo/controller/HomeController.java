package springDI_study.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") //이건 그냥 localhost:8080
    public String home() {
        return "home";
    }

    /**
     * welcome page가 아닌 여기부터 오는 이유?
     * 먼저 스프링 컨테이너부터에서 관련 컨트롤러부터
     * 뒤져보고 난 후에 없으면 그때 w.p 로 가기때문
     */
}
