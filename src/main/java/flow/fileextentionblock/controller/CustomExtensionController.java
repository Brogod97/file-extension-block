package flow.fileextentionblock.controller;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.service.ExtensionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("custom")
public class CustomExtensionController {

    private final ExtensionService extensionService;

    public CustomExtensionController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }


    /**
     * 커스텀 확장자 추가
     */
    @PostMapping("/add")
    public boolean saveCustomExtension(@RequestParam String name) {
        Extension extension = new Extension();
        extension.setName(name);

        for (Extension fixedExtension : extensionService.findAllFixedExtension()) {
            // 새로 들어온 확장자명이 고정 확장자 중 하나일 경우
            if(fixedExtension.getName().equals(extension.getName())) {
                return false;
            }
        }

        int result = extensionService.saveCustomExtension(extension);

        return result > 0 ? true : false;
    }

    /**
     * 커스텀 확장자 삭제
     */
    @PostMapping("/delete")
    public boolean deleteCustomExtension(@RequestParam String name) {
        Extension extension = new Extension();
        extension.setName(name);

        int result = extensionService.deleteCustomExtension(extension);

        return result > 0 ? true : false;
    }

    /**
     * 커스텀 확장자 초기화
     */
    @PostMapping("/reset")
    public boolean resetCustomExtension() {
        int result = extensionService.resetCustomExtension();

        return result > 0 ? true : false;
    }
}

