package tech.nasr.codebase;

import tech.nasr.codebase.filter.SourceFileFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class DocumentScanner {

    private final SourceFileFilter filter;

    public DocumentScanner(SourceFileFilter filter) {
        this.filter = filter;
    }

    public List<Path> scan(Path projectPath) throws IOException {

        return Files.walk(projectPath)
                .filter(Files::isRegularFile)
                .filter(filter::shouldIndex)
                .toList();

    }

}
