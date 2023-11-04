package flow.fileextentionblock.controller;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.service.ExtensionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class ExtensionController {

    private final ExtensionService extensionService;

    public ExtensionController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    @PostMapping("/add")
    @ResponseBody
    public Extension addCustomExtension(@ModelAttribute Extension extension) {
        return extensionService.addExtension(extension);
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateExtensionChecked(@ModelAttribute Extension extension) {
        extensionService.updateExtensionChecked(extension);

        return "수정 완료";
    }

    @PostMapping("/delete")
    @ResponseBody
    public String deleteCustomExtension(@RequestParam String name) {
        extensionService.deleteExtension(name);

        return "삭제 완료";
    }

    @PostMapping("/reset")
    @ResponseBody
    public String resetExtension(@RequestParam("type") String type) {
        extensionService.resetExtension(type);

        return "요청 수행";
    }

    @PostMapping("/checkValidExtension")
    @ResponseBody
    public boolean checkValidExtension(@RequestParam String name) {
        Optional<Extension> extension = extensionService.checkValidExtension(name);

        if(extension.isPresent()) {
            String checked = extension.get().getChecked();
            String type = extension.get().getType();

            if(type.equals("F")) {
                return checked.equals("Y") ? false : true;
            }

            if(type.equals("C")){
                return false;
            }
        }

        return true;
    }
}
