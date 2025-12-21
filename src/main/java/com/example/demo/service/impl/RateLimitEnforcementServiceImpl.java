@Service
public class RateLimitEnforcementServiceImpl implements RateLimitEnforcementService {

    private final RateLimitEnforcementRepository repository;

    public RateLimitEnforcementServiceImpl(RateLimitEnforcementRepository repository) {
        this.repository = repository;
    }

    public RateLimitEnforcement create(RateLimitEnforcement enforcement) {
        return repository.save(enforcement);
    }

    public RateLimitEnforcement getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RateLimitEnforcement not found"));
    }

    public List<RateLimitEnforcement> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
