package ru.practicum.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.Category;
import ru.practicum.compilations.model.Compilation;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/categories")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        log.info("POST /admin/categories {}", category);
        return adminService.addCategory(category);
    }

    @DeleteMapping("/{catId}")
    public Category deleteCategory(@PathVariable Integer catId) {
        log.info("DELETE /admin/categories/{}", catId);
        return adminService.deleteCategory(catId);
    }

    @PatchMapping("/{catId}")
    public Category editCategory(@PathVariable Integer catId) {
        log.info("PATCH /admin/categories/{}", catId);
        return adminService.editCategory(catId);
    }

}
