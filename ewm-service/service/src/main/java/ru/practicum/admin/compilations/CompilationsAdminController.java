package ru.practicum.admin.compilations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.compilations.NewCompilationDto;
import ru.practicum.model.compilations.UpdateCompilationRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/compilations")
public class CompilationsAdminController {

    private final CompilationAdminService compilationAdminService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompilationDto addCompilation(@RequestBody NewCompilationDto newCompilationDto) {
        log.info("POST /admin/compilations {}", newCompilationDto);
        return compilationAdminService.addCompilation(newCompilationDto);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable Long compId) {
        log.info("DELETE /admin/compilations/{}", compId);
        compilationAdminService.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto editCompilation(@PathVariable Long compId,
                                          @RequestBody UpdateCompilationRequest updateCompilationRequest) {
        log.info("PATCH /admin/compilations {}", updateCompilationRequest);
        return compilationAdminService.editCompilation(compId, updateCompilationRequest);
    }
}
