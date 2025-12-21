public interface KeyExemptionService {
    KeyExemption create(KeyExemption exemption);
    KeyExemption update(Long id, KeyExemption exemption);
    KeyExemption getById(Long id);
    List<KeyExemption> getAll();
    void delete(Long id);
}
