package ru.itis.trofimoff.todoapp.repositories.group;

import org.springframework.stereotype.Repository;
import ru.itis.trofimoff.todoapp.models.Group;
import ru.itis.trofimoff.todoapp.repositories.utils.RowMapper;
import ru.itis.trofimoff.todoapp.repositories.utils.SqlJDBCTemplate;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    //language=SQL
    private String SQL_SELECT_ALL_GROUPS = "SELECT groups.id, groups.name FROM groups";
    private DataSource dataSource;
    private SqlJDBCTemplate sqlJDBCTemplate;

    private RowMapper<Group> groupRowMapper = row -> Group.builder()
            .name(row.getString("name"))
            .id(row.getInt("id"))
            .build();

    public GroupRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.sqlJDBCTemplate = new SqlJDBCTemplate(dataSource);
    }

    @Override
    public void save(Group entity) {}

    @Override
    public void update(Group entity) {}

    @Override
    public void delete(Group entity) {}

    @Override
    public List<Group> findAll() {
        return this.sqlJDBCTemplate.query(SQL_SELECT_ALL_GROUPS, groupRowMapper);
    }
}
