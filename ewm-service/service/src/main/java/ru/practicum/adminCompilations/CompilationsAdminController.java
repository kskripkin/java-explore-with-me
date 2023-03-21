package ru.practicum.adminCompilations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.adminUsers.model.User;
import ru.practicum.compilations.model.Compilation;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/compilations")
public class CompilationsAdminController {

    private final CompilationAdminService compilationAdminService;

    @PostMapping
    public User addCompilation(@RequestBody Compilation compilation) {
        log.info("POST /admin/compilations {}", compilation);
        return compilationAdminService.addCompilation(compilation);
    }

    @DeleteMapping("/{compId}")
    public void deleteCompilation(@PathVariable Integer compId) {
        log.info("DELETE /admin/compilations/{}", compId);
        compilationAdminService.deleteCompilation(compId);
    }

    @PatchMapping
    public User editCompilation(@RequestBody Compilation compilation) {
        log.info("PATCH /admin/compilations {}", compilation);
        return compilationAdminService.editCompilation(compilation);
    }
}
