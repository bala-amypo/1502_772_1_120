@Service
public class ApiUsageLogServiceImpl implements ApiUsageLogService {

    private final ApiUsageLogRepository repository;

    public ApiUsageLogServiceImpl(ApiUsageLogRepository repository) {
        this.repository = repository;
    }

    public ApiUsageLog create(ApiUsageLog log) {
        return repository.save(log);
    }

    public ApiUsageLog getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApiUsageLog not found"));
    }

    public List<ApiUsageLog> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
