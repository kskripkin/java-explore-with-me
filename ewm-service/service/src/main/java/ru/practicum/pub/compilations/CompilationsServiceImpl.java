package ru.practicum.pub.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.CompilationsMapper;
import ru.practicum.model.compilations.CompilationDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompilationsServiceImpl implements  CompilationsService {

    private final CompilationRepository compilationRepository;
    private final CompilationsMapper compilationsMapper;
    @Override
    public List<CompilationDto> getCompilations(boolean pinned, Integer from, Integer size) {
        return compilationRepository.getCompilations(pinned, PageRequest.of((from / size), size))
                .stream()
                .map(x -> compilationsMapper.compilationsToCompilationDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilation(long compId) {
        return compilationsMapper.compilationsToCompilationDto(
                compilationRepository.getCompilations(compId)
        );
    }
}
