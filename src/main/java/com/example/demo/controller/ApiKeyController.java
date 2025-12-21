@RestController
@RequestMapping("/api/api-keys")
public class ApiKeyController {

    private final ApiKeyService service;

    public ApiKeyController(ApiKeyService service) {
        this.service = service;
    }

    @PostMapping
    public ApiKey create(@RequestBody ApiKey apiKey) {
        return service.create(apiKey);
    }

    @PutMapping("/{id}")
    public ApiKey update(@PathVariable Long id, @RequestBody ApiKey apiKey) {
        return service.update(id, apiKey);
    }

    @GetMapping("/{id}")
    public ApiKey get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ApiKey> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
