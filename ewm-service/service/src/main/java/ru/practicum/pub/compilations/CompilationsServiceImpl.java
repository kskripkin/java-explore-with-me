package ru.practicum.pub.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.compilations.Compilation;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompilationsServiceImpl implements  CompilationsService {

    private final CompilationRepository compilationRepository;
    @Override
    public List<Compilation> getCompilations(boolean pinned, Integer from, Integer size) {
        return compilationRepository.getCompilations(pinned, PageRequest.of((from / size), size));
    }

    @Override
    public Compilation getCompilation(Integer compId) {
        return compilationRepository.getCompilations(compId);
    }
}
