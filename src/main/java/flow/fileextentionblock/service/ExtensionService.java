package flow.fileextentionblock.service;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.repository.ExtensionRepository;

import java.util.List;
import java.util.Optional;

public class ExtensionService {

    private ExtensionRepository repository;

    public ExtensionService(ExtensionRepository repository) {
        this.repository = repository;
    }

    public void updateExtensionChecked(Extension extension) {
        String toggleValue = "";

        if(extension.getChecked().equals("Y")) {
            toggleValue = "N";
        }else {
            toggleValue = "Y";
        }

        repository.update(extension, toggleValue);
    }

    /**
     * 확장자 추가
     */
    public Extension addExtension(Extension extension) {
        // 이미 추가된 확장자명 중복 방지
        repository.findByName(extension.getName())
                .ifPresent(e -> {
                    throw new IllegalStateException("이미 존재하는 확장자입니다");
                });

        return repository.save(extension);
    }

    public void deleteExtension(String name) {
        // 저장된 확장자가 있다면 수행
        repository.findByName(name)
                .ifPresent(e -> {
                    repository.delete(name);
                });
    }

    public void resetExtension(String type) {
        repository.reset(type);
    }

    public List<Extension> findAllExtensionByType(String type) {
        return repository.findAllByType(type);
    }

    public Optional<Extension> checkValidExtension(String name) {
        return repository.findByName(name);
    }
}
