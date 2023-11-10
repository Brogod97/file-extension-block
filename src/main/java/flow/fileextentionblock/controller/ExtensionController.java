//package flow.fileextentionblock.controller;
//
//import flow.fileextentionblock.domain.Extension;
//import flow.fileextentionblock.service.ExtensionService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class ExtensionController {
//
//    private final ExtensionService extensionService;
//
//    public ExtensionController(ExtensionService extensionService) {
//        this.extensionService = extensionService;
//    }
//
//    /**
//     * 고정 확장자 checked 토글
//     */
//    @PostMapping("/update")
//    public boolean updateExtensionChecked(@ModelAttribute Extension extension) {
//        int result = extensionService.updateFixedExtension(extension);
//
//        return result > 0 ? true : false;
//    }
//
//    /**
//     * 고정 확장자 초기화
//     */
//    @PostMapping("/reset")
//    public boolean resetFixedExtension() {
//        int result = extensionService.resetFixedExtension();
//
//        return result > 0 ? true : false;
//    }
//
//    /**
//     * 커스텀 확장자 추가
//     */
//    @PostMapping("/add")
//    public boolean saveCustomExtension(@ModelAttribute Extension extension) {
//        int result = extensionService.saveCustomExtension(extension);
//
//        return result > 0 ? true : false;
//    }
//
//    /**
//     * 커스텀 확장자 삭제
//     */
//    @PostMapping("/delete")
//    public boolean deleteCustomExtension(@ModelAttribute Extension extension) {
//        int result = extensionService.deleteCustomExtension(extension);
//
//        return result > 0 ? true : false;
//    }
//
//    /**
//     * 커스텀 확장자 초기화
//     */
//    @PostMapping("/reset")
//    public boolean resetCustomExtension() {
//        int result = extensionService.resetCustomExtension();
//
//        return result > 0 ? true : false;
//    }
//
//    /**
//     * 확장자명 유효성 검사
//     */
//    @PostMapping("/checkValidExtension")
//    public boolean checkValidExtension(@ModelAttribute Extension extension) {
//        // 중복 O -> false (유효하지 않음)
//        // 중복 X -> true (유효)
//
//        boolean result = false;
//
//        if(extensionService.checkValidExtension(extension)) { // true -> 중복 X
//            result = true;
//        }
//
//        return result;
//    }
//}
