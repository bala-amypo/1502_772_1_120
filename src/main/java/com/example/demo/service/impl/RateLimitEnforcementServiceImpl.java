@Override
public RateLimitEnforcement createEnforcement(RateLimitEnforcement enforcement) {

    if (enforcement == null) {
        throw new BadRequestException("Invalid enforcement");
    }

    try {
        for (var field : enforcement.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(enforcement);

            if (value instanceof Integer && (Integer) value < 0) {
                throw new BadRequestException("Negative value not allowed");
            }

            if (value instanceof Long && (Long) value < 0) {
                throw new BadRequestException("Negative value not allowed");
            }
        }
    } catch (IllegalAccessException e) {
        throw new BadRequestException("Invalid enforcement data");
    }

    return enforcementRepository.save(enforcement);
}
