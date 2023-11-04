//package flow.fileextentionblock.service;
//
//import flow.fileextentionblock.domain.Extension;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//class ExtensionServiceTest {
//
//    ExtensionService service = new ExtensionService(new MemoryExtensionRepository());
//
//    @Test
//    void addExtension() {
//        Extension extension = new Extension();
//        extension.setName("cmd");
//        extension.setType("F");
//
//        Extension extension2 = new Extension();
//        extension2.setName("com");
//        extension2.setType("F");
//
//        Extension result = service.addExtension(extension);
//
//        System.out.println("result = " + result);
//
//        service.deleteExtension(extension);
//
//        List<Extension> listOfFixed = service.findAllExtensionByType("F");
//
//        System.out.println("listOfFixed = " + listOfFixed);
//    }
//
//    @Test
//    void deleteExtension() {
//        Extension extension = new Extension();
//        extension.setName("cmd");
//        extension.setType("F");
//
//        Extension extension2 = new Extension();
//        extension2.setName("ccc");
//        extension2.setType("F");
//
//        Extension result = service.addExtension(extension);
//        Extension result2 = service.addExtension(extension2);
//
//        List<Extension> f1 = service.findAllExtensionByType("F");
//        System.out.println("f1 = " + f1);
//
//        service.deleteExtension(extension);
//
//        List<Extension> f2 = service.findAllExtensionByType("F");
//
//        System.out.println("f2 = " + f2);
//    }
//
//}