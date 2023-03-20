package ru.practicum.admin;

import ru.practicum.compilations.model.Category;

public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    @Override
    public Category addCategory(Category category) {
        return adminRepository.addCategory(category);
    }

    @Override
    public Category deleteCategory(Integer catId) {
        return adminRepository.deleteCategory(catId);
    }

    @Override
    public Category editCategory(Integer catId) {
        return adminRepository.editCategory(catId);
    }
}
