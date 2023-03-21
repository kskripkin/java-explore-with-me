package ru.practicum.admin.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.model.users.User;
import ru.practicum.model.compilations.Compilation;

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
