package com.example.backend.controller

import com.example.backend.dto.product.ProductDto
import com.example.backend.services.CategoryService
import com.example.backend.services.ProductService
import com.example.backend.xtraa.ApiResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = ["http://localhost:3000/"], allowedHeaders = ["*"])
class ProductController {
    @Autowired
    private lateinit var productService: ProductService
    @Autowired
    private lateinit var categoryService: CategoryService

    private val logger = LoggerFactory.getLogger(ProductController::class.java)

//    val logger = Logger.
    @GetMapping("/")
    fun getProducts(): ResponseEntity<List<ProductDto>> {
        val body = productService.listProducts();
        return ResponseEntity(body, HttpStatus.OK)
    }

    @PostMapping("/add")
    fun addProduct(@RequestBody productDto: ProductDto): ResponseEntity<ApiResponse> {
        val optionalCategory = categoryService.readCategory(productDto.categoryId)
        logger.info("$optionalCategory")
        if (optionalCategory == null)
            return ResponseEntity<ApiResponse>(ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT)
        val category = optionalCategory.get()
        productService.addProduct(productDto, category)
        logger.info("product Msg $productDto")
        return ResponseEntity(ApiResponse(true, "Product has been added"), HttpStatus.CREATED)
    }

    @PostMapping("/update/{productId}")
    fun updateProduct(
        @PathVariable("productId") productId: Int,
        @RequestBody productDto: ProductDto
    ): ResponseEntity<ApiResponse> {
        val optionalCategory = categoryService.readCategory(productId)
        if (optionalCategory.isPresent)
            return ResponseEntity<ApiResponse>(ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT)
        val category = optionalCategory.get()
        productService.updateProduct(productId, productDto, category)
        return ResponseEntity(ApiResponse(true, "Product has been updated"), HttpStatus.OK)
    }

}
























