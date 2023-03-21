package ru.practicum.adminCompilations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.adminUsers.model.User;
import ru.practicum.compilations.model.Compilation;

@Service
@RequiredArgsConstructor
public class CompilationAdminServiceImpl implements CompilationAdminService {

    private final CompilationAdminRepository compilationAdminRepository;

    @Override
    public User addCompilation(Compilation compilation) {
        return compilationAdminRepository.save(compilation);
    }

    @Override
    public void deleteCompilation(Integer compId) {
        compilationAdminRepository.delete(compId);
    }

    @Override
    public User editCompilation(Compilation compilation) {
        return compilationAdminRepository.save(compilation);
    }
}
