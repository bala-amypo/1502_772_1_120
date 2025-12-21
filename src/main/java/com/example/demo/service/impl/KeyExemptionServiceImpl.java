@Service
public class KeyExemptionServiceImpl implements KeyExemptionService {

    private final KeyExemptionRepository repository;

    public KeyExemptionServiceImpl(KeyExemptionRepository repository) {
        this.repository = repository;
    }

    public KeyExemption create(KeyExemption exemption) {
        return repository.save(exemption);
    }

    public KeyExemption update(Long id, KeyExemption exemption) {
        KeyExemption existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));
        exemption.setId(existing.getId());
        return repository.save(exemption);
    }

    public KeyExemption getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyExemption not found"));
    }

    public List<KeyExemption> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
