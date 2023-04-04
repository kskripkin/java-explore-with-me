package ru.practicum.admin.categories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.categories.Category;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/categories")
public class AdminCategoriesController {

    private final AdminCategoriesService adminCategoriesService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {
        log.info("POST /admin/categories {}", category);
        return adminCategoriesService.addCategory(category);
    }

    @DeleteMapping("/{catId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long catId) {
        log.info("DELETE /admin/categories/{}", catId);
        adminCategoriesService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    public Category editCategory(@PathVariable Long catId,
                                 @RequestBody Category category) {
        log.info("PATCH /admin/categories/{} {}", catId, category);
        return adminCategoriesService.editCategory(catId, category);
    }

}
