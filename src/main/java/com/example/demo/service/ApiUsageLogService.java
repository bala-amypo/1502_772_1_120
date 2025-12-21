public interface ApiUsageLogService {
    ApiUsageLog create(ApiUsageLog log);
    ApiUsageLog getById(Long id);
    List<ApiUsageLog> getAll();
    void delete(Long id);
}