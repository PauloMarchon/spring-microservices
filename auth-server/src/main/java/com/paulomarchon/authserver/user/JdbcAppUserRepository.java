package com.paulomarchon.authserver.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAppUserRepository implements AppUserRepository{
    private final JdbcTemplate jdbcTemplate;
    private final AppUserRowMapper appUserRowMapper;

    public JdbcAppUserRepository(JdbcTemplate jdbcTemplate, AppUserRowMapper appUserRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.appUserRowMapper = appUserRowMapper;
    }

    @Override
    public List<AppUser> selectAllAppUsers() {
        var sql = """
                SELECT id, username, email, password, role, created_at ,is_enable
                FROM appuser
                LIMIT 100
                """;
        return jdbcTemplate.query(sql, this.appUserRowMapper);
    }

    @Override
    public Optional<AppUser> selectAppUserById(String id) {
        var sql = """
                SELECT id, username, email, password, role, created_at ,is_enable
                FROM appuser
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql, this.appUserRowMapper, id)
                .stream()
                .findAny();
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) {
        var sql = """
                SELECT id, username, email, password, role, created_at ,is_enable
                FROM appuser
                WHERE username = ?
                """;
        return jdbcTemplate.query(sql, this.appUserRowMapper, username)
                .stream()
                .findAny();
    }

    @Override
    public Optional<AppUser> selectAppUserByEmail(String email) {
        var sql = """
                SELECT id, username, email, password, role, created_at ,is_enable
                FROM appuser
                WHERE email = ?
                """;
        return jdbcTemplate.query(sql, this.appUserRowMapper, email)
                .stream()
                .findAny();
    }

    @Override
    public void registerAppUser(AppUser appUser) {
        var sql = """
                INSERT INTO appuser(id, username, email, password, role, created_at ,is_enable)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        int result = this.jdbcTemplate.update(
                sql,
                appUser.getId(),
                appUser.getUsername(),
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getRole().name(),
                appUser.getCreatedAt(),
                appUser.isEnable()
        );
        System.out.println("insert appuser result: " + result);
    }

    @Override
    public void deleteAppUserById(String appUserId) {
        var sql = """
                DELETE
                FROM appuser
                WHERE id = ?
                """;
        int result = this.jdbcTemplate.update(sql, appUserId);
        System.out.println("delete appuser result: " + result);
    }

    @Override
    public void updateAppUser(AppUser update) {
    //TODO
    }

    @Override
    public void activateAppUser(String appUserId) {
        var sql = """
                UPDATE appuser
                SET is_enable = ?
                WHERE id = ?
                """;
        int result = this.jdbcTemplate.update(sql, true, appUserId);
        System.out.println("update is_enable result: " + result);
    }

    @Override
    public boolean existsAppUserById(String id) {
        var sql = """
                SELECT count(id)
                FROM appuser
                WHERE id = ?
                """;
        Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public boolean existsAppUserWithUsername(String username) {
        var sql = """
                SELECT count(id)
                FROM appuser
                WHERE username = ?
                """;
        Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    @Override
    public boolean existsAppUserWithEmail(String email) {
        var sql = """
                SELECT count(id)
                FROM appuser
                WHERE email = ?
                """;
        Integer count = this.jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}
