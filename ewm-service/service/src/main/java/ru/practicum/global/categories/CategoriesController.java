package ru.practicum.global.categories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.compilations.Compilation;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @GetMapping
    public List<Compilation> getCategories(@RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                           @RequestParam(value= "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("GET /categories?from={}&size={}", from, size);
        return categoriesService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    public List<Compilation> getCategoriesOne(@PathVariable Integer catId) {
        log.info("GET /categories/{}", catId);
        return categoriesService.getCategoriesOne(catId);
    }


}
