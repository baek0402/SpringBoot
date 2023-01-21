package springDI_study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springDI_study.demo.domain.Member;
import springDI_study.demo.service.MemberService;

import java.util.List;


// @Component 방식
@Controller
public class MemberController {
    //private final MemberService memberService = new MemberService();
    /**
    1. 이렇게 new로 객체를 새로 생성하는것보다 스프링 컨테이너에 등록하고,
     얘를 가져다가 쓰면 좋은거니까, 생성자로 해주기!
     */


    // 생성자 주입
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    /**
     * @Autowired로 하면, 멤버서비스를 스프링 컨테이너에 있는 멤버서비스로 딱 알아서 연결해준대~!
     * 그러나 이렇게만 하면 memberService를 찾을수가 없다고 오류가 나.
     * 왜냐면 현재 MemberService는 그냥 순수 자바 코드라서,
     * 2. @Service 넣어주러가자~
     * 3. @Repository 도 넣어주기
     * 여기까지는 Componentscan 방식을 활용한것임
     */

    /**
     * 2 필드 주입
     * @Autowired
     * private MemberService memberService;
     *
     * 3 설정자 주입
     * private MemberService memberService;
     * @Autowired
     * public void setMemberService(MemberService memberService) {
     *     this.memberService = memberService;
     * }
     * 얘는 public 으로 되어있기 때문에 노출이 되어버림!
     * (최초 세팅후에는 딱히 건드릴 일이 없기 때문에 굳이?)
     */

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        //member리스트를 다 model에 넣어서 넘기는거야 view로
        return "members/memberList";
    }
}
