@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/api-keys")
    public List<String> keys() {
        return List.of();
    }

    @GetMapping("/quota-plans")
    public List<String> plans() {
        return List.of();
    }

    @GetMapping("/usage-logs")
    public List<String> logs() {
        return List.of();
    }

    @GetMapping("/enforcements")
    public List<String> enforce() {
        return List.of();
    }

    @GetMapping("/key-exemptions")
    public List<String> exemptions() {
        return List.of();
    }
}
