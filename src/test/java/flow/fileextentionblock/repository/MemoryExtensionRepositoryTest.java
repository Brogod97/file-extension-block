package flow.fileextentionblock.repository;

import flow.fileextentionblock.domain.Extension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryExtensionRepositoryTest {

//    MemoryExtensionRepository repository = new MemoryExtensionRepository();
//    @Test
//    void save(){
//        Extension extension = new Extension();
//        extension.setName("cmd");
//        extension.setType("F");
//
//        repository.save(extension);
//
//        Extension result = repository.findByName("cmd").get();
//
//        assertThat(extension).isEqualTo(result);
//
//        System.out.println(repository.findAll());
//    }
//
//    @Test
//    void delete() {
//        Extension extension = new Extension();
//        extension.setName("cmd");
//        extension.setType("F");
//
//        repository.save(extension);
//
//        System.out.println(repository.findAll());
//
//        repository.delete(extension.getId());
//
//        System.out.println(repository.findAll());
//
//        assertThat(repository.findAll()).isNullOrEmpty();
//    }
}