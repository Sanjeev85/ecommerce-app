package com.example.backend.controller

import com.example.backend.models.Category
import com.example.backend.services.CategoryService
import com.example.backend.xtraa.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = ["http://localhost:3000/"], allowedHeaders = ["*"])
class CategoryController {
    @Autowired
    private lateinit var categoryService: CategoryService

    private val logger = LoggerFactory.getLogger(CategoryService::class.java)

    @GetMapping("/")
    fun getCategories(): ResponseEntity<List<Category>> {
        val body = categoryService.listCategories();
        logger.info("$body")
        return ResponseEntity(body, HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createCategory(@RequestBody category: Category): ResponseEntity<ApiResponse> {
        if (categoryService.readCategory(category.categoryName) != null)
            return ResponseEntity(ApiResponse(false, "category already exist"), HttpStatus.CONFLICT)
        categoryService.createCategory(category)
        return ResponseEntity(ApiResponse(  true, "created Category"), HttpStatus.CREATED)
    }

    @PostMapping("/update/{categoryId}")
    fun updateCategory(
        @PathVariable("categoryId") categoryId: Int,
        @RequestBody category: Category
    ): ResponseEntity<ApiResponse> {
        if (categoryService.readCategory(categoryId) != null) {
            categoryService.updateCategory(categoryId, category)
            return ResponseEntity(ApiResponse(true, "updated the category"), HttpStatus.OK)
        }
        return ResponseEntity(ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND)
    }
}