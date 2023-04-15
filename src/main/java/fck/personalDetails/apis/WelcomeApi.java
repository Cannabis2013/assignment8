package fck.personalDetails.apis;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/")
public class WelcomeApi {
    @GetMapping("/")
    public RedirectView welcome()
    {
        return new RedirectView("/swagger-ui/index.html");
    }
}