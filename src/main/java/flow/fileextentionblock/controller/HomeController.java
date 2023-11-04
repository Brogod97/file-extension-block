package flow.fileextentionblock.controller;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.service.ExtensionService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    private ExtensionService extensionService;

    public HomeController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    @GetMapping("/")
    public String home(Model model) {

        // 최초 진입 시 DB에 저장된 확장자명 조회
        List<Extension> fixedList = extensionService.findAllExtensionByType("F");
        List<Extension> customList = extensionService.findAllExtensionByType("C");

        model.addAttribute("fixedList", fixedList);
        model.addAttribute("customList", customList);

        return "home";
    }
}