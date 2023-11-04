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

    /*
    1. fixed 클릭 시 DB에 추가 (이미 입력된 경우 쓰루) - 이름, 타입 필요
    2. 커스텀 확장자명 입력 시 추가 (이미 입력된 경우 쓰루) - 이름 타입 필요
    3. 넘어온 파일의 확장자가 DB의 값과 일치하는지 체크 - 이름 필요

     */

    private final ExtensionService extensionService;

    public ExtensionController(ExtensionService extensionService) {
        this.extensionService = extensionService;
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

    /**
     * 초기화 기능 수행
     * @Param type ("F", "C") - Fixed, Custom
     */
    @PostMapping("/reset")
    @ResponseBody
    public String resetExtension(@RequestParam("type") String type) {
        extensionService.resetExtension(type);

        return "요청 수행";
    }
}
