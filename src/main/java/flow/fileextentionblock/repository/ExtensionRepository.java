package flow.fileextentionblock.repository;

import flow.fileextentionblock.domain.Extension;
import flow.fileextentionblock.domain.ExtensionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExtensionRepository {

    private final JdbcTemplate jdbcTemplate;
    public ExtensionRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Extension save(Extension extension) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("extension").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", extension.getName());
        parameters.put("type", extension.getType());
        parameters.put("checked", "N");

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        extension.setId(key.longValue());

        return extension;
    }

    public void update(Extension extension, String toggleValue) {
        jdbcTemplate.update("update extension set checked = ? where name = ? and type = 'F'", toggleValue, extension.getName());
    }

    public void delete(String name) {
        int result = jdbcTemplate.update("delete from extension where name = ?", name);
    }

    public void reset(String type) {
        if(type.equals(ExtensionType.FIXED.getValue())) {
            jdbcTemplate.update("update extension set checked = 'N' where type = ?", type);
        } else if(type.equals(ExtensionType.CUSTOM.getValue())) {
            jdbcTemplate.update("delete from extension where type = ?", type);
        }
    }

    public Optional<Extension> findByName(String name) {
        return jdbcTemplate.query("select * from extension where name = ?", extensionRowMapper(), name)
                            .stream().findAny();
    }

    public List<Extension> findAllByType(String type) {
        return jdbcTemplate.query("select * from extension where type = ?", extensionRowMapper(), type);
    }

    private RowMapper<Extension> extensionRowMapper() {
        return (rs, rowNum) -> {
            Extension extension = new Extension();
            extension.setId(rs.getLong("id"));
            extension.setName(rs.getString("name"));
            extension.setType(rs.getString("type"));
            extension.setChecked(rs.getString("checked"));
            return extension;
        };
    }
}
