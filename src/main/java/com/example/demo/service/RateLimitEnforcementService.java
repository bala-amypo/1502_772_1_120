public interface RateLimitEnforcementService {
    RateLimitEnforcement create(RateLimitEnforcement enforcement);
    RateLimitEnforcement getById(Long id);
    List<RateLimitEnforcement> getAll();
    void delete(Long id);
}
