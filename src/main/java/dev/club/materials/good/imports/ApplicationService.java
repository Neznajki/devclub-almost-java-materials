package dev.club.materials.good.imports;

public class ApplicationService {

    private final ApiClient apiClient;
    private final UserRepository userRepository;
    private final HashService hashService;
    private final FileService fileService;
    private final TaskExecutor taskExecutor;

    public ApplicationService(
        ApiClient apiClient, UserRepository userRepository,
        HashService hashService, FileService fileService,
        TaskExecutor taskExecutor
    ) {
        this.apiClient = apiClient;
        this.userRepository = userRepository;
        this.hashService = hashService;
        this.fileService = fileService;
        this.taskExecutor = taskExecutor;
    }

    public void process(String userId) throws Exception {

        System.out.println("Processing user " + userId);

        String apiData = apiClient.fetchUser(userId);

        var user = userRepository.findUser(userId);

        if (user != null) {
            String hash = hashService.hash(user.email());
            fileService.writeLog(hash);
        }

        fileService.writeOutput("Process finished");

        taskExecutor.runAsync(() -> System.out.println("Async task done"));
    }
}
