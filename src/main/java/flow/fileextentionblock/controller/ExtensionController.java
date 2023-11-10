package flow.fileextentionblock.controller;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.domain.ExtensionType;
import flow.fileextentionblock.service.ExtensionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ExtensionController {

    private final ExtensionService extensionService;

    public ExtensionController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    /**
     * 커스텀 확장자 추가
     */
    @PostMapping("/add")
    public Extension addCustomExtension(@ModelAttribute Extension extension) {
        return extensionService.addExtension(extension);
    }

    /**
     * 고정 확장자 checked 업데이트
     */
    @PostMapping("/update")
    public String updateExtensionChecked(@ModelAttribute Extension extension) {
        extensionService.updateExtensionChecked(extension);

        return "수정 완료";
    }

    /**
     * 커스텀 확장자 삭제
     */
    @PostMapping("/delete")
    public String deleteCustomExtension(@RequestParam String name) {
        extensionService.deleteExtension(name);

        return "삭제 완료";
    }

    /**
     * [고정 / 커스텀] 확장자 초기화
     */
    @PostMapping("/reset")
    public String resetExtension(@RequestParam("type") String type) {
        extensionService.resetExtension(type);

        return "요청 수행";
    }

    /**
     * 확장자명 유효성 검사
     */
    @PostMapping("/checkValidExtension")
    public boolean checkValidExtension(@RequestParam String name) {
        Optional<Extension> extension = extensionService.checkValidExtension(name);

        if(extension.isPresent()) {
            String checked = extension.get().getChecked();
            String type = extension.get().getType();

            if(type.equals(ExtensionType.FIXED)) {
                return checked.equals("Y") ? false : true;
            }

            if(type.equals(ExtensionType.CUSTOM)){
                return false;
            }
        }

        return true;
    }
}
