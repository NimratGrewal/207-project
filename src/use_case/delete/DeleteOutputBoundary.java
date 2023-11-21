package use_case.delete;
import java.util.UUID;

public interface DeleteOutputBoundary {
    void prepareSuccessView(DeleteOutputData deleteOutputData);
}
