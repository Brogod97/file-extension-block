package flow.fileextentionblock.controller;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.service.ExtensionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("fixed")
public class FixedExtensionController {

    private final ExtensionService extensionService;

    public FixedExtensionController(ExtensionService extensionService) {
        this.extensionService = extensionService;
    }

    /**
     * 고정 확장자 checked 토글
     */
    @PostMapping("/update")
    public boolean updateExtensionChecked(@RequestParam(value = "name") String name,
                                          @RequestParam(value = "checked") String checked) {

        Extension extension = new Extension();
        extension.setName(name);
        extension.setChecked(checked);

        int result = extensionService.updateFixedExtension(extension);

        return result > 0 ? true : false;
    }

    /**
     * 고정 확장자 초기화
     */
    @PostMapping("/reset")
    public boolean resetFixedExtension() {
        int result = extensionService.resetFixedExtension();

        return result > 0 ? true : false;
    }
}
