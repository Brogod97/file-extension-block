package flow.fileextentionblock.repository;

import flow.fileextentionblock.domain.Extension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ExtensionRepository {

    private final JdbcTemplate jdbcTemplate;
    public ExtensionRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 고정 확장자 checked 토글
     * @param extension
     * @return int
     */
    public int updateFixedExtension(Extension extension) {
        return jdbcTemplate.update("update fixed set checked = ? where name = ?;", extension.getChecked(), extension.getName());
    }

    /**
     * 고정 확장자 초기화
     * @return int
     */
    public int resetFixedExtension() {
        return jdbcTemplate.update("update fixed set checked = 'N'");
    }

    /**
     * 체크된 고정 확장자 검색 (단일)
     * @param name
     * @return Optional<Extension>
     */
    public Optional<Extension> findCheckedFixedExtension(String name) {
        return jdbcTemplate.query("select * from fixed where name = ? and checked = 'Y'", fixedExtensionRowMapper(), name)
                .stream().findAny();
    }

    /**
     * 고정 확장자 검색 (단일)
     * @param name
     * @return Optional<Extension>
     */
    public Optional<Extension> findFixedExtension(String name) {
        return jdbcTemplate.query("select * from fixed where name = ?", fixedExtensionRowMapper(), name)
                .stream().findAny();
    }

    /**
     * 고정 확장자 (전체)
     * @return List<Extension>
     */
    public List<Extension> findAllFixedExtension() {
        return jdbcTemplate.query("select * from fixed", fixedExtensionRowMapper());
    }

    /**
     * 커스텀 확장자 추가
     * @param extension
     * @return int
     */
    public int saveCustomExtension(Extension extension) {
        return jdbcTemplate.update("insert into custom(name) values(?)", extension.getName());
    }

    /**
     * 커스텀 확장자 삭제
     * @param extension
     * @return int
     */
    public int deleteCustomExtension(Extension extension) {
        return jdbcTemplate.update("delete from custom where name = ?", extension.getName());
    }

    /**
     * 커스텀 확장자 초기화
     * @return int
     */
    public int resetCustomExtension() {
        return jdbcTemplate.update("delete from custom");
    }

    /**
     * 커스텀 확장자 검색 (단일)
     * @param name
     * @return Optional<Extension>
     */
    public Optional<Extension> findCustomExtension(String name) {
        return jdbcTemplate.query("select * from custom where name = ?", customExtensionRowMapper(), name)
                            .stream().findAny();
    }

    /**
     * 커스텀 확장자 검색 (전체)
     * @return List<Extension>
     */
    public List<Extension> findAllCustomExtension() {
        return jdbcTemplate.query("select * from custom", customExtensionRowMapper());
    }

    // 고정 확장자 로우 매퍼
    private RowMapper<Extension> fixedExtensionRowMapper() {
        return (rs, rowNum) -> {
            Extension extension = new Extension();
            extension.setName(rs.getString("name"));
            extension.setChecked(rs.getString("checked"));
            return extension;
        };
    }

    // 커스텀 확장자 로우 매퍼
    private RowMapper<Extension> customExtensionRowMapper() {
        return (rs, rowNum) -> {
            Extension extension = new Extension();
            extension.setName(rs.getString("name"));
            return extension;
        };
    }
}
