package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

  @RequestMapping("/reponse-view-v1")
  public ModelAndView responseViewV1() {
    ModelAndView mav = new ModelAndView("response/hello")
      .addObject("data", "hello!!");

      return mav;
  }

  @RequestMapping("/reponse-view-v2")
  public String resonseViewV2(Model model) {
    model.addAttribute("data", "hello!");

    return "response/hello";
  }

  @RequestMapping("/response/hello")
  public void responseViewV3(Model model) {
    model.addAttribute("data", "hello!!");
  }
}
