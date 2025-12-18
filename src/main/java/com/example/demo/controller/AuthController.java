@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto dto) {
        return new AuthResponseDto("dummy-jwt-token");
    }

    @PostMapping("/register")
    public void register(@RequestBody AuthRequestDto dto) {}
}
