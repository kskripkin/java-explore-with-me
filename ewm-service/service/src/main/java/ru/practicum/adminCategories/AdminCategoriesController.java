package ru.practicum.adminCategories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.Category;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/categories")
public class AdminCategoriesController {

    private final AdminCategoriesService adminCategoriesService;

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        log.info("POST /admin/categories {}", category);
        return adminCategoriesService.addCategory(category);
    }

    @DeleteMapping("/{catId}")
    public Category deleteCategory(@PathVariable Integer catId) {
        log.info("DELETE /admin/categories/{}", catId);
        return adminCategoriesService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    public Category editCategory(@PathVariable Integer catId) {
        log.info("PATCH /admin/categories/{}", catId);
        return adminCategoriesService.editCategory(catId);
    }

}
