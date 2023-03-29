package ru.practicum.admin.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.CompilationsMapper;
import ru.practicum.mapper.CustomerMapperImpl;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.compilations.NewCompilationDto;
import ru.practicum.model.compilations.UpdateCompilationRequest;
import ru.practicum.pub.compilations.CompilationRepository;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class CompilationAdminServiceImpl implements CompilationAdminService {

    private final CompilationRepository compilationRepository;
    private final CompilationsMapper compilationsMapper;

    private final CustomerMapperImpl customerMapper;

    @Transactional
    @Override
    public CompilationDto addCompilation(NewCompilationDto newCompilationDto) {
        Compilation compilation = compilationRepository.save(compilationsMapper.newCompilationDtoToCompilation(newCompilationDto));
        for (Long eventId : newCompilationDto.getEvents()) {
            compilationRepository.saveEventsToCompilation(compilation.getId(), eventId);
        }

        return compilationsMapper.compilationsToCompilationDto(compilation);
    }

    @Transactional
    @Override
    public void deleteCompilation(Long compId) {
        compilationRepository.deleteIdEventsToCompilation(compId);
        compilationRepository.deleteCompilation(compId);
    }

    @Transactional
    @Override
    public CompilationDto editCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        Compilation compilationSource = compilationRepository.getById(compId);
        Compilation compilationUpdate = customerMapper.updateEventFromUpdateCompilationRequest(updateCompilationRequest, compilationSource);
        compilationRepository.deleteIdEventsToCompilation(compId);
        for (Long eventId : updateCompilationRequest.getEvents()) {
            compilationRepository.saveEventsToCompilation(compId, eventId);
        }
        return compilationsMapper.compilationsToCompilationDto(compilationRepository.save(compilationUpdate));
    }
}
