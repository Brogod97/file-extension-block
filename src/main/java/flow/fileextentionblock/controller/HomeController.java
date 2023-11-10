package flow.fileextentionblock.controller;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.service.ExtensionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class HomeController {

    private ExtensionService extensionService;

    public HomeController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    @GetMapping("/")
    public String home(Model model) {

        // 최초 진입 시 DB에 저장된 확장자명 조회
        List<Extension> fixedList = extensionService.findAllFixedExtension();
        List<Extension> customList = extensionService.findAllCustomExtension();

        model.addAttribute("fixedList", fixedList);
        model.addAttribute("customList", customList);

        return "home";
    }

    /**
     * 확장자명 유효성 검사
     */
    @PostMapping("/checkValidExtension")
    @ResponseBody
    public boolean checkValidExtension(@RequestParam("name") String checkName) {
        // 중복 O -> false (유효하지 않음)
        // 중복 X -> true (유효)

        boolean result = false;

        if (extensionService.checkValidExtension(checkName)) { // true -> 중복 X
            result = true;
        }

        return result;
    }
}