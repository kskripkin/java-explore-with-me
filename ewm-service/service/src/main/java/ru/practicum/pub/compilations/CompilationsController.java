package ru.practicum.pub.compilations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/compilations")
public class CompilationsController {

    private final CompilationsService compilationsService;

    @GetMapping
    public List<CompilationDto> getCompilations(@RequestParam(name = "pinned", required = false, defaultValue = "false") boolean pinned,
                                                @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                                @RequestParam(value= "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("GET /compilations?pinned={}&from={}&size={}", pinned, from, size);
        return compilationsService.getCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilation(@PathVariable long compId) {
        log.info("GET /compilations/{}", compId);
        return compilationsService.getCompilation(compId);
    }
}
