package ru.practicum.admin.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.CompilationsMapper;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.compilations.NewCompilationDto;
import ru.practicum.model.compilations.UpdateCompilationRequest;
import ru.practicum.pub.compilations.CompilationRepository;


@Service
@RequiredArgsConstructor
public class CompilationAdminServiceImpl implements CompilationAdminService {

    private final CompilationRepository compilationRepository;
    private final CompilationsMapper compilationsMapper;

    @Override
    public CompilationDto addCompilation(NewCompilationDto newCompilationDto) {
        Compilation compilation = compilationRepository.save(compilationsMapper.newCompilationDtoToCompilation(newCompilationDto));
        for (Long eventId : newCompilationDto.getEvents()) {
            compilationRepository.saveEventsToCompilation(compilation.getId(), eventId);
        }

        return compilationsMapper.compilationsToCompilationDto(compilation);
    }

    @Override
    public void deleteCompilation(Long compId) {
        compilationRepository.deleteIdEventsToCompilation(compId);
        compilationRepository.deleteById(compId);
    }

    @Override
    public CompilationDto editCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        deleteCompilation(compId);
        return addCompilation(compilationsMapper.updateCompilationRequestToNewCompilationDto(updateCompilationRequest));
    }
}
