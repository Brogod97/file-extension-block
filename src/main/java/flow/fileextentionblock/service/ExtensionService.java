package flow.fileextentionblock.service;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.repository.ExtensionRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
public class ExtensionService {

    private ExtensionRepository repository;

    public ExtensionService(ExtensionRepository repository) {
        this.repository = repository;
    }

    /**
     * 고정 확장자 checked 토글
     * @param extension
     */
    public int updateFixedExtension(Extension extension) {

        if(extension.getChecked().equals("Y")) {
            extension.setChecked("N");
        }else {
            extension.setChecked("Y");
        }

        return repository.updateFixedExtension(extension);
    }

    /**
     * 고정 확장자 검색 (단일)
     */
    public Optional<Extension> findCheckedFixedExtension(Extension extension) {
        return repository.findCheckedFixedExtension(extension.getName());
    }

    /**
     * 고정 확장자 검색 (전체)
     */
    public List<Extension> findAllFixedExtension() {
        return repository.findAllFixedExtension();
    }

    /**
     * 고정 확장자 초기화
     */
    public int resetFixedExtension() {
        return repository.resetFixedExtension();
    }




    /**
     * 커스텀 확장자 추가
     */
    public int saveCustomExtension(Extension extension) {
        // 확장자명 중복 방지
        repository.findCustomExtension(extension.getName())
                .ifPresent(e -> {
                    throw new IllegalStateException("이미 존재하는 확장자입니다");
                });

        return repository.saveCustomExtension(extension);
    }

    /**
     * 커스텀 확장자 삭제
     * @param extension
     */
    public int deleteCustomExtension(Extension extension) {
        // 저장된 확장자가 있다면 수행
        if(repository.findCustomExtension(extension.getName()).isEmpty()) { // 일치하는 확장자가 없으면
            return 0;
        }

        // 있으면, 삭제 수행 후 결과 반환
        return repository.deleteCustomExtension(extension);
    }

    /**
     * 커스텀 확장자 초기화
     */
    public int resetCustomExtension() {
        return repository.resetCustomExtension();
    }


    /**
     * 커스텀 확장자 검색 (단일)
     */
    public Optional<Extension> findAllExtensionByName(Extension extension) {
        return repository.findCustomExtension(extension.getName());
    }

    /**
     * 커스텀 확장자 검색 (전체)
     */
    public List<Extension> findAllCustomExtension() {
        return repository.findAllCustomExtension();
    }

    /**
     * 확장자 제출 유효성 검사
     * @return boolean
     */
    public boolean checkValidExtension(String checkName) {
        boolean hasFixedExtension = repository.findCheckedFixedExtension(checkName).isPresent();
        boolean hasCustomExtension = repository.findCustomExtension(checkName).isPresent();

        if(hasFixedExtension || hasCustomExtension) { // 조회된 값이 있는 경우 -> 중복 O
            return false;
        }else { // 중복 X
            return true;
        }
    }
}
