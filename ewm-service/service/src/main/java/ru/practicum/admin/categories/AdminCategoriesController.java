package ru.practicum.admin.categories;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.categories.Category;

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
    public void deleteCategory(@PathVariable Long catId) {
        log.info("DELETE /admin/categories/{}", catId);
        adminCategoriesService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    public Category editCategory(@PathVariable Integer catId,
                                 @RequestBody Category category) {
        log.info("PATCH /admin/categories/{} {}", catId, category);
        return adminCategoriesService.editCategory(catId, category);
    }

}
