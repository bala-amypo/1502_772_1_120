
@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    private final ApiKeyRepository repository;

    public ApiKeyServiceImpl(ApiKeyRepository repository) {
        this.repository = repository;
    }

    public ApiKey create(ApiKey apiKey) {
        return repository.save(apiKey);
    }

    public ApiKey update(Long id, ApiKey apiKey) {
        ApiKey existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));
        apiKey.setId(existing.getId());
        return repository.save(apiKey);
    }

    public ApiKey getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApiKey not found"));
    }

    public List<ApiKey> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
